package develop.Marvel.repository;

import develop.Marvel.entities.Comics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComicsRepository extends JpaRepository< Comics, String > {

    @Override
    Page< Comics > findAll(Pageable pageable);

    Page< Comics > findByTag(String tag, Pageable pageable);
}
