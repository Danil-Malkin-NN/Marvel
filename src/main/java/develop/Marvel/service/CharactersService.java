package develop.Marvel.service;

import develop.Marvel.dto.CharacterDtoImage;
import develop.Marvel.dto.ComicsDto;
import develop.Marvel.entities.Character;
import develop.Marvel.entities.Comics;
import develop.Marvel.exeptions.NoElementException;
import develop.Marvel.repository.CharactersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class CharactersService {

    @Autowired
    CharactersRepository charactersRepository;

    @Autowired
    ComicsService comicsService;

    @Value("${upload.path}")
    String uploadPath;

    @Value("${unknown}")
    String UNKNOWN;

    ModelMapper modelMapper = new ModelMapper();

    public Page< CharacterDtoImage > getDtoList(Pageable pageable) {
        Page< Character > characterPage = getCharacterList(pageable);
        List< Character > characterList = characterPage.toList();
        List<CharacterDtoImage> characterDtoImages = new ArrayList<>();
        for (Character c : characterList) {
            characterDtoImages.add(modelMapper.map(c, CharacterDtoImage.class));
        }

        return new PageImpl<CharacterDtoImage>(characterDtoImages, characterPage.getPageable(), characterPage.getTotalElements());
    }

    public Page< Character > getCharacterList(Pageable pageable) {
        return charactersRepository.findAll(pageable);
    }

    public Character getCharacterByName(String name) {
        return charactersRepository.findById(name).orElseThrow(
                () -> new NoElementException(String.format("Item with name %s not found", name)));
    }

    public CharacterDtoImage getCharacterDtoImageByName(String name) {
        return modelMapper.map(getCharacterByName(name), CharacterDtoImage.class);
    }

    public void addCharacter(Character character) {
        try {
            getCharacterByName(character.getName());
        } catch (NoElementException e) {
            if(character.getImage() == null || character.getImage().isEmpty())
                character.setImage(UNKNOWN);
            saveCharacter(character);
        }
    }

    public void saveCharacter(Character character) {
        charactersRepository.save(character);
    }

    public Set< ComicsDto > getCharacterComics(String name) {
        Set< Comics > comics = getCharacterByName(name).getComicsSet();
        Set< ComicsDto > comicsDto = new HashSet<>();
        for (Comics c : comics) {
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

    public void addImage(String name, MultipartFile file) throws IOException {
        Character character = getCharacterByName(name);

        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + resultFilename));
            character.setImage(resultFilename);
            saveCharacter(character);
        }
    }

    public Page< CharacterDtoImage> getDtoListByTag(Pageable pageable, String filter) {
        Page< Character > characterPage = charactersRepository.findByTag(filter, pageable);
        List< Character > characterList = characterPage.toList();
        List<CharacterDtoImage> characterDtoImages = new ArrayList<>();
        for (Character c : characterList) {
            characterDtoImages.add(modelMapper.map(c, CharacterDtoImage.class));
        }

        return new PageImpl<CharacterDtoImage>(characterDtoImages, characterPage.getPageable(), characterPage.getTotalElements());

    }
}
