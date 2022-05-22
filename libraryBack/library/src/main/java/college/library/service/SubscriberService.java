package college.library.service;

import college.library.model.Abonat;
import college.library.model.Bibliotecar;
import college.library.model.Role;
import college.library.persistance.AbonatRepository;
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

@AllArgsConstructor
@Service
public class SubscriberService implements UserDetailsService {

    private final AbonatRepository abonatRepository;

    //username;role
    @Override
    public UserDetails loadUserByUsername(String userDetails) throws UsernameNotFoundException {
        List<String> listUserDetails = Arrays.stream(userDetails.split(";")).collect(Collectors.toList());
        String username = listUserDetails.get(0);
        String role = listUserDetails.get(1).toLowerCase();

        if (!role.equals(Role.ABONAT.toString().toLowerCase())) {
            throw new UsernameNotFoundException("abonat repository not found");
        }
        Optional<Abonat> abonatOptional = abonatRepository.findByUsername(username);

        if(abonatOptional.isEmpty()){
            throw new UsernameNotFoundException("abonat repository not found");
        }

        Abonat abonat = abonatOptional.get();

        return new User(abonat.getUsername(),
                abonat.getParola(),
                Collections.singletonList(new SimpleGrantedAuthority(abonat.getRol().toString().toLowerCase()))
        );
    }
}
