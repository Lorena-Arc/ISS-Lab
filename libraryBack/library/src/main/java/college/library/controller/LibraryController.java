package college.library.controller;

import college.library.dto.CarteRequestDto;
import college.library.model.Carte;
import college.library.service.CarteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
@Slf4j
public class LibraryController {
    private final CarteService carteService;

    @GetMapping("/librarian/books")
    public ResponseEntity<List<Carte>> getAllBooks() {
        return new ResponseEntity<>(carteService.getAllBooks(), HttpStatus.OK);
    }

    @PutMapping("/librarian/books/{bookId}")
    public ResponseEntity<Carte> updateBook(@PathVariable("bookId") String bookId,
                                            @RequestBody CarteRequestDto carte) {
        log.error("Am ajuns in put " + carte.toString() + " id: " + bookId);
        return new ResponseEntity<>(carteService.modifyBook(bookId, carte), HttpStatus.OK);
    }

    @PostMapping("/librarian/books")
    public ResponseEntity<Carte> addBook(@RequestBody CarteRequestDto carte) {
        log.error("Am ajuns in post " + carte.toString());
        return new ResponseEntity<>(carteService.addBook(carte), HttpStatus.CREATED);
    }

    @DeleteMapping("/librarian/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") String bookId) {
        log.error("Am ajuns in delete " + bookId);
        carteService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
