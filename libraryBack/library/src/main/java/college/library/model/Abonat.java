package college.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "SUBSCRIBERS")
public class Abonat {
    @Id
    @GeneratedValue(generator = "SEQ_ABONAT")
    @GenericGenerator(
            name = "SEQ_ABONAT",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private String codUnic;

    @Column(name = "CNP")
    private String CNP;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "PASSWORD", nullable = false)
    private String parola;

    @Column(name = "NAME")
    private String nume;

    @Column(name = "ADDRESS")
    private String adresa;

    @Column(name = "PHONE_NUMBER")
    private String telefon;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role rol;

    @OneToMany(mappedBy = "abonat")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @Filter(name = "activ")
    private List<Imprumut> imprumuturiActive;

    @OneToMany(mappedBy = "abonat")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @Filter(name = "inactiv")
    private List<Imprumut> imprumuturiReturnate;
}
