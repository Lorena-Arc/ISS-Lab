package college.library.security;

import college.library.model.Role;
import college.library.model.User;
import college.library.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt_secret}")
    private String secret;

    @Autowired
    private UserService userService;

    public String generateAccessToken(String username, String role)  throws IllegalArgumentException, JWTCreationException {
        User user = userService.getByUsernameAndRole(username, role);

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 1000))
                .withClaim("role", user.getRole().toString())
                .withIssuedAt(new Date())
                .withIssuer("Lorena")
                .sign(Algorithm.HMAC256(secret));

    }

    public String generateRefreshToken(String username, String role)  throws IllegalArgumentException, JWTCreationException {
        User user = userService.getByUsernameAndRole(username, role);

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 1000))
                .withIssuedAt(new Date())
                .withIssuer("Lorena")
                .sign(Algorithm.HMAC256(secret));

    }

    public User validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("Lorena")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        String username = jwt.getSubject();
        String role = jwt.getClaim("role").asString();
        return User.builder()
                .username(username)
                .role(Role.valueOf(role.toUpperCase()))
                .build();
    }


    public String getTokenSubject(String token) {
        return JWT.decode(token).getSubject();
    }
}
