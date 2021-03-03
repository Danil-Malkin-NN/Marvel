package develop.Marvel.controller;

import develop.Marvel.dto.CharacterDtoImage;
import develop.Marvel.dto.ComicsDto;
import develop.Marvel.entities.Character;
import develop.Marvel.service.CharactersService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("v1/public/characters")
public class CharactersController {

    @Autowired
    CharactersService charactersService;

    @GetMapping()
    public String getCharactersList(Model model,
                                    @PageableDefault(sort = { "name" }, direction = Sort.Direction.DESC) Pageable pageable,
                                    @RequestParam(value = "filter", required = false, defaultValue = "") String filter) {
        Page< CharacterDtoImage > page;
        if (filter != null && !filter.isEmpty()) {
            page = charactersService.getDtoListByTag(pageable, filter);
        } else {
            page = charactersService.getDtoList(pageable);
        }

        model.addAttribute("filter", filter);
        model.addAttribute("url", "/v1/public/characters");
        model.addAttribute("page", page);
        return "characters";
    }

    @GetMapping("/{characterId}")
    public String getCharacters(@PathVariable("characterId") String name, Map< String, Object > model) {
        CharacterDtoImage characterDtoImage = charactersService.getCharacterDtoImageByName(name);
        model.put("name", characterDtoImage.getName());
        model.put("filename", characterDtoImage.getImage());
        model.put("description", characterDtoImage.getDescription());
        return "character";
    }

    @PostMapping()
    public String addCharacter(@RequestParam String name,
                               @RequestParam(required = false, defaultValue = "История этого героя не известна миру.") String description,
                               @RequestParam(value = "tag", required = false) String tag,
                               @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws
                                                                                                            IOException {
        Character character;
        if (tag != null) {
            character = new Character(name, tag, description);
        } else {
            character = new Character(name, description);
        }

        charactersService.addCharacter(character);

        if (multipartFile != null && !multipartFile.getOriginalFilename().isEmpty())
            charactersService.addImage(name, multipartFile);

        return "successfully";
    }

//    @PostMapping("/{characterId}/image")
//    public String addImage(@PathVariable("characterId") String name,
//                           @RequestParam("image") MultipartFile file) throws IOException {
//        charactersService.addImage(name, file);
//        return "successfully";
//    }

    @GetMapping("/{characterId}/comics")
    public String getCharacterComics(@PathVariable("characterId") String name, Model model) {
        Set<ComicsDto> set = charactersService.getCharacterComics(name);

        model.addAttribute("page", set);
        return "comics";
    }

    @PostMapping("/{characterId}")
    public String addCharacter(@PathVariable("characterId") String name, @RequestBody String nameComics) {
        charactersService.addComicsInCharacter(name, nameComics);
        return "successfully";
    }

    @GetMapping("/add")
    public String getAddPage() {
        return "addPage";
    }

}
