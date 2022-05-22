package college.library.controller;

import college.library.dto.CarteRequestDto;
import college.library.model.Carte;
import college.library.security.JWTUtil;
import college.library.service.CarteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private final JWTUtil jwtUtil;

    @GetMapping("/librarian/books")
    public ResponseEntity<List<Carte>> getAllBooks() {
        return new ResponseEntity<>(carteService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Carte>> getBooks(
            @RequestParam(required = false) String availability
    ) {
        carteService.getBooks(availability);
        return new ResponseEntity<>(carteService.getBooks(availability), HttpStatus.OK);
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

    @PutMapping("/librarian/books/loans/{bookId}")
    public ResponseEntity<Carte> returnBook(@PathVariable String bookId) {
        return ResponseEntity.ok(carteService.returnBook(bookId));
    }

    @PutMapping("/subscriber/books/loans/{bookId}")
    public ResponseEntity<Carte> borrowBook(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String bookId) {
        String token = authorizationHeader.substring("Bearer ".length());
        String username = jwtUtil.getTokenSubject(token);
        return ResponseEntity.ok(carteService.borrowBook(username, bookId));
    }

    @GetMapping("/subscriber/books/loans")
    public ResponseEntity<List<Carte>> getLoansForUser(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        String username = jwtUtil.getTokenSubject(token);
        return ResponseEntity.ok(carteService.getLoansForUser(username));
    }
}
