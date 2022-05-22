package college.library.persistance;

import college.library.model.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarteRepository extends JpaRepository<Carte, String> {
    @Query(value = "SELECT * FROM BOOKS B WHERE B.STATUS=:availability", nativeQuery = true)
    List<Carte> filterBooks(@Param("availability") String availability);
}
