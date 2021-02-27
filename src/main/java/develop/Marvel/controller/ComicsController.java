package develop.Marvel.controller;

import develop.Marvel.dto.CharacterDto;
import develop.Marvel.dto.ComicsDto;
import develop.Marvel.entities.Character;
import develop.Marvel.entities.Comics;
import develop.Marvel.service.ComicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("v1/public/comics")
public class ComicsController {

    @Autowired
    ComicsService comicsService;

    @GetMapping()
    public List< ComicsDto > getComics(){
        return comicsService.getComicsDtoList();
    }

    @GetMapping("/{comicId}")
    public ComicsDto getComics(@PathVariable("comicId") String name){
        return comicsService.getComicsDtoByName(name);
    }

    @GetMapping("/{comicId}/characters")
    public Set< CharacterDto > getComicsId(@PathVariable("comicId") String name){
        return comicsService.getComicsCharactersDto(name);
    }

    @PostMapping
    public void addComics(@RequestBody Comics comics){
        comicsService.addComics(comics);
    }


}
