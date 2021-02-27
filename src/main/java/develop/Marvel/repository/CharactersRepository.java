package develop.Marvel.repository;

import develop.Marvel.entities.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharactersRepository extends JpaRepository< Character, String > {

}
