package college.library.service;

import college.library.dto.CarteRequestDto;
import college.library.mapper.CarteMapper;
import college.library.model.*;
import college.library.persistance.AbonatRepository;
import college.library.persistance.CarteRepository;
import college.library.persistance.ImprumutRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CarteService {
    private final CarteRepository carteRepository;
    private final ImprumutRepository imprumutRepository;
    private final CarteMapper carteMapper;
    private final AbonatRepository abonatRepository;

    public List<Carte> getAllBooks(){
        return carteRepository.findAll();
    }
//
//    public List<Carte> getAvailableBooks() {
//        return carteRepository.get
//    }

    public Carte addBook(CarteRequestDto carteRequest){
        Carte carte = carteMapper.carteRequestDtoToCarte(carteRequest, StatusCarte.DISPONIBIL);
        return carteRepository.save(carte);
    }

    public void deleteBook(String id){
        List<Imprumut> loans = imprumutRepository.findAll().stream().filter(loan ->
            loan.getCarte().getId().equals(id)
        ).collect(Collectors.toList());

        imprumutRepository.deleteAll(loans);

        carteRepository.deleteById(id);
    }

    public Carte modifyBook(String bookId, CarteRequestDto carte){
        log.info(carte.toString());
        Carte carteSaved = carteRepository.findById(bookId).orElseThrow();
        carteSaved.setAutor(carte.getAutor());
        carteSaved.setTitlu(carte.getTitlu());
        return carteRepository.save(carteSaved);
    }

    public List<Carte> getBooks(String availability) {
        if (availability == null || availability.equals("")) {
            return carteRepository.findAll();
        }

        return carteRepository.filterBooks(availability.toUpperCase());
    }

    public Carte returnBook(String bookId) {
        log.info("ALL:" + imprumutRepository.findAll());
        List<Imprumut> active = getAllActiveLoans();
        log.info(active.toString());
        Carte carte = carteRepository.findById(bookId).orElseThrow();
        log.info(carte.toString());
        Imprumut imprumut = findLoanByBookId(active, bookId);
        carte.setStatusCarte(StatusCarte.DISPONIBIL);
        carteRepository.save(carte);
        imprumut.setStatus(StatusImprumut.INACTIV);
        imprumutRepository.save(imprumut);
        return carteRepository.getById(bookId);
    }

    private List<Imprumut> getAllActiveLoans() {
        return imprumutRepository.findAllByStatus(StatusImprumut.ACTIV);
    }

    private Imprumut findLoanByBookId(List<Imprumut> active, String bookId) {
        return active.stream().filter(loan -> loan.getCarte().getId().equals(bookId)).findFirst().orElseThrow();
    }

    public Carte borrowBook(String username, String bookId) {
        Abonat abonat = abonatRepository.getByUsername(username);
        Carte carte = carteRepository.findById(bookId).orElseThrow();
        if (carte.getStatusCarte().equals(StatusCarte.INDISPONIBIL)) {
            //Inlocuieste cu conflict
            throw new RuntimeException("Book already taken");
        }

        carte.setStatusCarte(StatusCarte.INDISPONIBIL);
        carteRepository.save(carte);

        Imprumut imprumut = new Imprumut(carte, abonat, StatusImprumut.ACTIV);
        log.info(imprumutRepository.save(imprumut).toString());
        return carteRepository.getById(bookId);
    }

    public List<Carte> getLoansForUser(String username) {

        Abonat abonat = abonatRepository.getByUsername(username);
        log.info(abonat.getImprumuturiActive().toString());
        return abonat.getImprumuturiActive().stream().map(Imprumut::getCarte).collect(Collectors.toList());

    }
}
