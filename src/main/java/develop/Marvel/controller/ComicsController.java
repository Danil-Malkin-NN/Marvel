package develop.Marvel.controller;

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
    public List< Comics > getComics(){
        return comicsService.getComicsList();
    }

    @GetMapping("/{comicId}")
    public Comics getComics(@PathVariable("comicId") String name){
        return comicsService.getComicsByName(name);
    }

    @GetMapping("/{comicId}/characters")
    public Set< Character > getComicsId(@PathVariable("comicId") String name){
        return comicsService.getComicsCharacters(name);
    }

    @PostMapping
    public void addComics(@RequestBody Comics comics){
        comicsService.addComics(comics);
    }


}
