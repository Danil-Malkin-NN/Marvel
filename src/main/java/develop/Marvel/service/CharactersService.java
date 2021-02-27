package develop.Marvel.service;

import develop.Marvel.dto.CharacterDto;
import develop.Marvel.dto.ComicsDto;
import develop.Marvel.entities.Character;
import develop.Marvel.entities.Comics;
import develop.Marvel.exeptions.NoElementException;
import develop.Marvel.repository.CharactersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CharactersService {

    @Autowired
    CharactersRepository charactersRepository;

    @Autowired
    ComicsService comicsService;

    ModelMapper modelMapper = new ModelMapper();

    public List< CharacterDto > getDtoList() {
        List< CharacterDto > abstractDtos = new ArrayList<>();
        List< Character > characters = getCharacterList();

        for (Character c : characters) {
            abstractDtos.add(modelMapper.map(c, CharacterDto.class));
        }
        return abstractDtos;
    }

    public List< Character > getCharacterList() {
        return charactersRepository.findAll();
    }

    public Character getCharacterByName(String name) {
        return charactersRepository.findById(name).orElseThrow(
                () -> new NoElementException(String.format("Item with name %s not found", name)));
    }

    public CharacterDto getCharacterDtoByName(String name) {
        return modelMapper.map(getCharacterByName(name), CharacterDto.class);
    }

    public void addCharacter(Character character) {
        try {
            getCharacterByName(character.getName());
        } catch (NoElementException e) {
            saveCharacter(character);
        }
    }

    public void saveCharacter(Character character) {
        charactersRepository.save(character);
    }

    public Set< ComicsDto > getCharacterComics(String name) {
        Set<Comics> comics = getCharacterByName(name).getComicsSet();
        Set<ComicsDto> comicsDto = new HashSet<>();
        for(Comics c : comics){
            comicsDto.add(modelMapper.map(c, ComicsDto.class));
        }
        return comicsDto;
    }

    public void addComicsInCharacter(String name, String nameComics) {
        Character character = getCharacterByName(name);
        Comics comics = comicsService.getComicsByName(nameComics);
        character.addComics(comics);
        comics.addCharacter(character);
        saveCharacter(character);
        comicsService.saveComics(comics);

    }
}
