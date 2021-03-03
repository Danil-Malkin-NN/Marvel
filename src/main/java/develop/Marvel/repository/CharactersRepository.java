package develop.Marvel.repository;

import develop.Marvel.entities.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharactersRepository extends JpaRepository< Character, String > {

    @Override
    Page< Character > findAll(Pageable pageable);

    Page<Character> findByTag(String tag, Pageable pageable);

}
