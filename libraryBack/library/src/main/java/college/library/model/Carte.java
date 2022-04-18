package college.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BOOKS")
public class Carte {
    @Id
    @GeneratedValue(generator = "SEQ_CARTE")
    @GenericGenerator(
            name = "SEQ_CARTE",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "TITLE")
    private String titlu;

    @Column(name = "AUTHOR")
    private String autor;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusCarte statusCarte;
}

