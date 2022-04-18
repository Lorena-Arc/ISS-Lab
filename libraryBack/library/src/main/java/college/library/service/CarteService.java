package college.library.service;

import college.library.dto.CarteRequestDto;
import college.library.mapper.CarteMapper;
import college.library.model.Carte;
import college.library.model.StatusCarte;
import college.library.persistance.CarteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CarteService {
    private final CarteRepository carteRepository;
    private final CarteMapper carteMapper;

    public List<Carte> getAllBooks(){
        return carteRepository.findAll();
    }

    public Carte addBook(CarteRequestDto carteRequest){
        Carte carte = carteMapper.carteRequestDtoToCarte(carteRequest, StatusCarte.DISPONIBIL);
        return carteRepository.save(carte);
    }

    public void deleteBook(String id){
        carteRepository.deleteById(id);
    }

    public Carte modifyBook(String bookId, CarteRequestDto carte){
        log.info(carte.toString());
        Carte carteSaved = carteRepository.findById(bookId).orElseThrow();
        carteSaved.setAutor(carte.getAutor());
        carteSaved.setTitlu(carte.getTitlu());
        return carteRepository.save(carteSaved);
    }
}
