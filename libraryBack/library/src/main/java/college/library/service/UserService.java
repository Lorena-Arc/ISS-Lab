package college.library.service;

import college.library.model.Abonat;
import college.library.model.Bibliotecar;
import college.library.model.Role;
import college.library.model.User;
import college.library.persistance.AbonatRepository;
import college.library.persistance.BibliotecarRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final AbonatRepository abonatRepository;
    private final BibliotecarRepository bibliotecarRepository;
    private final PasswordEncoder passwordEncoder;

    public User getByUsernameAndRole(String username, String role) {
        role = role.toLowerCase();

        switch (role) {
            case "abonat":
                Abonat abonat = abonatRepository.getByUsername(username);
                return User.builder()
                        .username(abonat.getUsername())
                        .id(abonat.getCodUnic())
                        .role(abonat.getRol())
                        .build();
            case "bibliotecar":
                Bibliotecar bibliotecar = bibliotecarRepository.getByUsername(username);
                return User.builder()
                        .username(bibliotecar.getUsername())
                        .id(bibliotecar.getId())
                        .role(bibliotecar.getRol())
                        .build();
        }

        return null;
    }

    public User registerUser(User user) {
        user.setParola(passwordEncoder.encode(user.getParola()));

        String role = user.getRole().toString().toLowerCase();
        switch (role) {
            case "abonat":
                Abonat abonat = new Abonat();
                abonat.setUsername(user.getUsername());
                abonat.setParola(user.getParola());
                abonat.setRol(Role.ABONAT);
                Abonat saved = abonatRepository.save(abonat);
                return User.builder()
                        .id(saved.getCodUnic())
                        .username(saved.getUsername())
                        .parola(saved.getParola())
                        .role(saved.getRol())
                        .build();
            case "bibliotecar":
                Bibliotecar bibliotecar = new Bibliotecar();
                bibliotecar.setUsername(user.getUsername());
                bibliotecar.setParola(user.getParola());
                bibliotecar.setRol(Role.ABONAT);
                Bibliotecar savedLibrarian = bibliotecarRepository.save(bibliotecar);
                return User.builder()
                        .id(savedLibrarian.getId())
                        .username(savedLibrarian.getUsername())
                        .parola(savedLibrarian.getParola())
                        .role(savedLibrarian.getRol())
                        .build();
        }

        return null;
    }
}
