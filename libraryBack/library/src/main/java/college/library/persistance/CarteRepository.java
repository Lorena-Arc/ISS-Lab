package college.library.persistance;

import college.library.model.Carte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteRepository extends JpaRepository<Carte, String> {
}
