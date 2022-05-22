package college.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "LIBRARIANS")
public class Bibliotecar {

    @Id
    @GeneratedValue(generator = "SEQ_BIBLIOTECAR")
    @GenericGenerator(
            name = "SEQ_BIBLIOTECAR",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "PASSWORD", nullable = false)
    private String parola;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role rol;
}
