package college.library.model;

import college.library.utils.sequences.StringSequenceIDGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BOOKS")
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ")
    @GenericGenerator(
            name = "BOOK_SEQ",
            strategy = "college.library.utils.sequences.StringSequenceIDGenerator",
            parameters = {
                    @Parameter(name = StringSequenceIDGenerator.VALUE_PREFIX_PARAMETER, value = "B_"),
                    @Parameter(name = StringSequenceIDGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
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

