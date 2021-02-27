package develop.Marvel.controller;

import develop.Marvel.dto.CharacterDto;
import develop.Marvel.dto.ComicsDto;
import develop.Marvel.entities.Character;
import develop.Marvel.entities.Comics;
import develop.Marvel.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("v1/public/characters")
public class CharactersController {

    @Autowired
    CharactersService charactersService;

    @GetMapping()
    public List< CharacterDto > getCharactersList() {
        return charactersService.getDtoList();
    }

    @GetMapping("/{characterId}")
    public CharacterDto getCharacters(@PathVariable("characterId") String name) {
        return charactersService.getCharacterDtoByName(name);
    }

    @PostMapping()
    public void addCharacter(@RequestBody Character character) {
        charactersService.addCharacter(character);
    }

    @Value("${upload.path}")
    String uploadPath;

    @PostMapping("/file")
    public void addImage(@RequestParam("image") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        if(file != null){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

        }

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
