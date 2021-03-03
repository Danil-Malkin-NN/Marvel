package develop.Marvel.controller;

import develop.Marvel.dto.CharacterDto;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("v1/public/characters")
public class CharactersController {

    @Autowired
    CharactersService charactersService;

    @GetMapping()
    public String getCharactersList(Model model, @PageableDefault(sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CharacterDtoImage> page =  charactersService.getDtoList(pageable);



        model.addAttribute("url", "/v1/public/characters");
        model.addAttribute("page", page);
        System.out.println("Total elements " + page.getTotalElements() + " Page total " + page.getTotalPages() + " Page size" + page.getSize());
        return "characters";
    }

    @GetMapping("/{characterId}")
    public String getCharacters(@PathVariable("characterId") String name, Map<String, Object> model) {
        CharacterDtoImage characterDtoImage = charactersService.getCharacterDtoImageByName(name);
        model.put("name", characterDtoImage.getName());
        model.put("filename", characterDtoImage.getImage());
        model.put("description", characterDtoImage.getDescription());
        return "character";
    }

    @PostMapping()
    public String addCharacter(@RequestBody Character character) {
        charactersService.addCharacter(character);
        return "successfully";
    }

    @PostMapping("/{characterId}/image")
    public String addImage(@PathVariable("characterId") String name,
                         @RequestParam("image") MultipartFile file) throws IOException {
        charactersService.addImage(name, file);
        return "successfully";
    }

    @GetMapping("/{characterId}/comics")
    public Set< ComicsDto > getCharacterComics(@PathVariable("characterId") String name) {
        return charactersService.getCharacterComics(name);
    }

    @PostMapping("/{characterId}")
    public void addCharacter(@PathVariable("characterId") String name, @RequestBody String nameComics) {
        charactersService.addComicsInCharacter(name, nameComics);
    }

}
