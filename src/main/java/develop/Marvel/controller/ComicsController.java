package develop.Marvel.controller;

import develop.Marvel.dto.CharacterDtoImage;
import develop.Marvel.dto.ComicsDto;
import develop.Marvel.entities.Comics;
import develop.Marvel.service.ComicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Controller
@RequestMapping("v1/public/comics")
public class ComicsController {

    @Autowired
    ComicsService comicsService;

    @GetMapping()
    public String getComics(@PageableDefault(sort = { "name" }, direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(value = "filter", required = false, defaultValue = "") String filter, Model model) {
        Page< ComicsDto > page;

        if (filter != null && !filter.isEmpty()) {
            page = comicsService.getDtoListByTag(pageable, filter);
        } else {
            page = comicsService.getDtoList(pageable);
        }
        model.addAttribute("filter", filter)
                .addAttribute("url", "/v1/public/comics")
                .addAttribute("page", page);
        return "comics";
    }

    @GetMapping("/{comicId}")
    public String getComic(@PathVariable("comicId") String name, Model model) {
        ComicsDto comicsDto = comicsService.getComicsDtoByName(name);
        model.addAttribute("name", comicsDto.getName())
                .addAttribute("filename", comicsDto.getImage())
                .addAttribute("description", comicsDto.getDescription());
        return "comic";
    }

    @GetMapping("/{comicId}/characters")
    public String getComicsId(@PathVariable("comicId") String name, Model model) {
        Set< CharacterDtoImage > characterDtoImage = comicsService.getComicsCharactersDto(name);

        model.addAttribute("page", characterDtoImage);
        return "charactersDto";
    }

    @PostMapping
    public String addComics(@RequestParam String name,
                            @RequestParam(required = false, defaultValue = "Это история сплошная загадка") String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws
                                                                                                         IOException {
        Comics comics;
        if (tag != null) {
            comics = new Comics(name, tag, description);
        } else {
            comics = new Comics(name, description);
        }
        comicsService.addComics(comics);
        if (multipartFile != null && !multipartFile.getOriginalFilename().isEmpty())
            comicsService.addImage(name, multipartFile);
        comicsService.addComics(comics);
        return "successfully";
    }

    @GetMapping("/add")
    public String addComics() {
        return "addComic";
    }

    @GetMapping("/addComic")
    public String addCharacter() {
        return "addCharacterInComic";
    }
}
