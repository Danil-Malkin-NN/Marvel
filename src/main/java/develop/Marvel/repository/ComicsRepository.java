package develop.Marvel.repository;

import develop.Marvel.entities.Comics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicsRepository extends JpaRepository< Comics, String > {

}
