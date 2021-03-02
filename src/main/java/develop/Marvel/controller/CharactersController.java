package develop.Marvel.controller;

import develop.Marvel.dto.CharacterDto;
import develop.Marvel.dto.CharacterDtoImage;
import develop.Marvel.dto.ComicsDto;
import develop.Marvel.entities.Character;
import develop.Marvel.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("v1/public/characters")
public class CharactersController {

    @Autowired
    CharactersService charactersService;

    @GetMapping()
    public List< CharacterDto > getCharactersList() {
        return charactersService.getDtoList();
    }

    @GetMapping("/{characterId}")
    public String getCharacters(@PathVariable("characterId") String name, ModelMap model) {
        CharacterDtoImage characterDtoImage = charactersService.getCharacterDtoImageByName(name);
        model.put("name", characterDtoImage.getName());
//        model.put("message.filename", characterDtoImage.getImage());
        return "characters";
    }

    @PostMapping()
    public void addCharacter(@RequestBody Character character) {
        charactersService.addCharacter(character);
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
