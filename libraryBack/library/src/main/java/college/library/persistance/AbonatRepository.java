package college.library.persistance;

import college.library.model.Abonat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbonatRepository extends JpaRepository<Abonat, String> {

    Optional<Abonat> findByUsername(String username);

    Abonat getByUsername(String username);
}
