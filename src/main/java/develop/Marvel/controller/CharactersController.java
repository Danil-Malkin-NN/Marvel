package develop.Marvel.controller;

import develop.Marvel.dto.CharacterDto;
import develop.Marvel.dto.ComicsDto;
import develop.Marvel.entities.Character;
import develop.Marvel.entities.Comics;
import develop.Marvel.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("v1/public/characters")
public class CharactersController {

    @Autowired
    CharactersService charactersService;

    @GetMapping()
    public List< CharacterDto > getCharactersList(){
       return charactersService.getDtoList();
    }

    @GetMapping("/{characterId}")
    public CharacterDto getCharacters(@PathVariable("characterId") String name){
        return charactersService.getCharacterDtoByName(name);
    }

    @PostMapping()
    public void addCharacter(@RequestBody Character character){
        charactersService.addCharacter(character);
    }

    @GetMapping("/{characterId}/comics")
    public Set< ComicsDto > getCharacterComics(@PathVariable("characterId") String name){
        return charactersService.getCharacterComics(name);
    }

    @PostMapping("/{characterId}")
    public void addCharacter(@PathVariable("characterId") String name, @RequestBody String nameComics){
        charactersService.addComicsInCharacter(name, nameComics);
    }

}
