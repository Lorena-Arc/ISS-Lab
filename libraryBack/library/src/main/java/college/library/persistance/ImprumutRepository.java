package college.library.persistance;

import college.library.model.Imprumut;
import college.library.model.StatusImprumut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImprumutRepository extends JpaRepository<Imprumut, String> {

    List<Imprumut> findAllByStatus(StatusImprumut statusImprumut);
}
