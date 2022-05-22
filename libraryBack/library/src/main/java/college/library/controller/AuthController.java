package college.library.controller;

import college.library.dto.LoginCredentials;
import college.library.security.JWTUtil;
import college.library.service.LibrarianService;
import college.library.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*", exposedHeaders={"acces_token"})
public class AuthController {

    @Autowired
    private LibrarianService librarianService;

    @Autowired
    private SubscriberService subscriberService;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;

//    @PostMapping("/register")
//    public Map<String, Object> registerHandler(@RequestBody User user){
//        user = userService.registerUser(user);
//        String token = jwtUtil.generateAccesToken((user.getUsername(), user.getRole().toString().toLowerCase());
//        return Collections.singletonMap("jwt-token", token);
//    }

    @PostMapping("/auth/login")
    public ResponseEntity<Void> librarianLoginHandler(@RequestBody LoginCredentials body){
        try {
            //username;role
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getUsername() + ";" + body.getRole(), body.getPassword());

            UsernamePasswordAuthenticationToken authOutputToken = (UsernamePasswordAuthenticationToken)authManager.authenticate(authInputToken);

            String token = jwtUtil.generateAccessToken(((UserDetails)authOutputToken.getPrincipal()).getUsername(),
                    new ArrayList<>(authOutputToken.getAuthorities()).get(0).toString());

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("acces_token", token);

            return ResponseEntity.ok()
                    .headers(responseHeaders).build();
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }

}