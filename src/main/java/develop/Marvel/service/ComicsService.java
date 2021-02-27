package develop.Marvel.service;

import develop.Marvel.dto.CharacterDto;
import develop.Marvel.dto.ComicsDto;
import develop.Marvel.entities.Character;
import develop.Marvel.entities.Comics;
import develop.Marvel.exeptions.NoElementException;
import develop.Marvel.repository.ComicsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ComicsService {

    @Autowired
    ComicsRepository comicsRepository;

    @Autowired
    CharactersService charactersService;

    ModelMapper modelMapper = new ModelMapper();

    public List< Comics > getComicsList() {
        return comicsRepository.findAll();
    }

    public Comics getComicsByName(String name) {
        return comicsRepository.findById(name).orElseThrow(
                () -> new NoElementException(String.format("Item with name %s not found", name)));
    }

    public ComicsDto getComicsDtoByName(String name) {
        return modelMapper.map(getComicsByName(name), ComicsDto.class);
    }

    public void addComics(Comics comics) {
        try {
            getComicsByName(comics.getName());
        }catch (NoElementException e){
            saveComics(comics);
        }
    }

    public void saveComics(Comics comics){
        comicsRepository.save(comics);
    }

    public Set< Character > getComicsCharacters(String name) {
        return getComicsByName(name).getCharacterSet();
    }

    public void addCharacterInComics(String name, String nameComics) {
        Character character = charactersService.getCharacterByName(name);
        Comics comics = getComicsByName(nameComics);
        character.addComics(comics);
        comics.addCharacter(character);
        charactersService.saveCharacter(character);
        saveComics(comics);

    }

    public List< ComicsDto> getComicsDtoList() {
        List< ComicsDto > comicsDto = new ArrayList<>();
        List< Comics > comics = getComicsList();
        for (Comics c : comics) {
            comicsDto.add(modelMapper.map(c, ComicsDto.class));
        }
        return comicsDto;

    }

    public Set<CharacterDto> getComicsCharactersDto(String name) {
        Set<Character> characters = getComicsCharacters(name);
        Set<CharacterDto> characterDtoSet = new HashSet<>();
        for(Character c : characters){
            characterDtoSet.add(modelMapper.map(c, CharacterDto.class));
        }
        return characterDtoSet;
    }
}
