package develop.Marvel.service;

import develop.Marvel.dto.CharacterDto;
import develop.Marvel.dto.CharacterDtoImage;
import develop.Marvel.dto.ComicsDto;
import develop.Marvel.entities.Character;
import develop.Marvel.entities.Comics;
import develop.Marvel.exeptions.NoElementException;
import develop.Marvel.repository.ComicsRepository;
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
public class ComicsService {

    @Autowired
    ComicsRepository comicsRepository;

    @Autowired
    CharactersService charactersService;

    @Value("${upload.path}")
    String uploadPath;

    @Value("${unknownComic}")
    String UNKNOWN;

    ModelMapper modelMapper = new ModelMapper();

    public Page< Comics > getComicsList(Pageable pageable) {
        return comicsRepository.findAll(pageable);
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
        } catch (NoElementException e) {
            if (comics.getImage() == null || comics.getImage().isEmpty())
                comics.setImage(UNKNOWN);
            saveComics(comics);
        }
    }

    public void saveComics(Comics comics) {
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

    public Set< CharacterDtoImage > getComicsCharactersDto(String name) {
        Set< Character > characters = getComicsCharacters(name);
        Set< CharacterDtoImage > characterDtoSet = new HashSet<>();
        for (Character c : characters) {
            characterDtoSet.add(modelMapper.map(c, CharacterDtoImage.class));
        }
        return characterDtoSet;
    }

    public Page< ComicsDto > getDtoListByTag(Pageable pageable, String filter) {
        Page< Comics > characterPage = comicsRepository.findByTag(filter, pageable);
        List< Comics > characterList = characterPage.toList();
        List< ComicsDto > characterDtoImages = new ArrayList<>();
        for (Comics c : characterList) {
            characterDtoImages.add(modelMapper.map(c, ComicsDto.class));
        }

        return new PageImpl< ComicsDto >(characterDtoImages, characterPage.getPageable(), characterPage.getTotalElements());

    }

    public Page< ComicsDto > getDtoList(Pageable pageable) {
        Page< Comics > characterPage = comicsRepository.findAll(pageable);
        List< Comics > characterList = characterPage.toList();
        List< ComicsDto > characterDtoImages = new ArrayList<>();
        for (Comics c : characterList) {
            characterDtoImages.add(modelMapper.map(c, ComicsDto.class));
        }

        return new PageImpl< ComicsDto >(characterDtoImages, characterPage.getPageable(), characterPage.getTotalElements());
    }

    public void addImage(String name, MultipartFile file) throws IOException {
        Comics comics = getComicsByName(name);

        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + resultFilename));
            comics.setImage(resultFilename);
            saveComics(comics);
        }

    }
}
