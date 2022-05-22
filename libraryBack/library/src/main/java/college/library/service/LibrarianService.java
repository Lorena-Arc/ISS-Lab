package college.library.service;

import college.library.model.Bibliotecar;
import college.library.model.Role;
import college.library.persistance.BibliotecarRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibrarianService implements UserDetailsService {

    private final BibliotecarRepository bibliotecarRepository;

    //username;role
    @Override
    public UserDetails loadUserByUsername(String userDetails) throws UsernameNotFoundException {
        List<String> listUserDetails = Arrays.stream(userDetails.split(";")).collect(Collectors.toList());
        String username = listUserDetails.get(0);
        String role = listUserDetails.get(1).toLowerCase();

        if (!role.equals(Role.BIBLIOTECAR.toString().toLowerCase())) {
            throw new UsernameNotFoundException("bibliotecar repository not found");
        }

        Optional<Bibliotecar> bibliotecarOptional = bibliotecarRepository.findByUsername(username);

        if(bibliotecarOptional.isEmpty()){
            throw new UsernameNotFoundException("bibliotecar repository not found");
        }

        Bibliotecar bibliotecar = bibliotecarOptional.get();

        return new User(bibliotecar.getUsername(),
                bibliotecar.getParola(),
                Collections.singletonList(new SimpleGrantedAuthority(bibliotecar.getRol().toString().toLowerCase()))
                );
    }
}
