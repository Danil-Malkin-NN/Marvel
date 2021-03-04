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
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("v1/public/comics")
public class ComicsController {

    @Autowired
    ComicsService comicsService;

    @GetMapping()
    public Page< ComicsDto > getComics(@PageableDefault(sort = { "name" }, direction = Sort.Direction.DESC) Pageable pageable,
                                       @RequestParam(value = "filter", required = false, defaultValue = "") String filter, Model model){
        Page< ComicsDto > page;

        if (filter != null && !filter.isEmpty()) {
            page = comicsService.getDtoListByTag(pageable, filter);
        }else {
            page = comicsService.getDtoList(pageable);
        }
        return page;
    }

    @GetMapping("/{comicId}")
    public ComicsDto getComic(@PathVariable("comicId") String name, Model model){
        return comicsService.getComicsDtoByName(name);
    }

    @GetMapping("/{comicId}/characters")
    public Set< CharacterDtoImage> getComicsId(@PathVariable("comicId") String name, Model model){
        return comicsService.getComicsCharactersDto(name);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addComics(@RequestParam String name,
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
    }


}
