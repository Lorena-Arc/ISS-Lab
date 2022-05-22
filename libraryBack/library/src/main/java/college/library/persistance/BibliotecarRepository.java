package college.library.persistance;

import college.library.model.Bibliotecar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BibliotecarRepository extends JpaRepository<Bibliotecar, String> {

    Optional<Bibliotecar> findByUsername(String username);

    Bibliotecar getByUsername(String username);
}
