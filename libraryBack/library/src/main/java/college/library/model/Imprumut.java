package college.library.model;

import college.library.utils.sequences.StringSequenceIDGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "LOANS")
@FilterDef(name="activ", defaultCondition="loan_status = 'ACTIV'")
@FilterDef(name="inactiv", defaultCondition="loan_status = 'INACTIV'")
public class Imprumut {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOAN_SEQ")
    @GenericGenerator(
            name = "LOAN_SEQ",
            strategy = "college.library.utils.sequences.StringSequenceIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringSequenceIDGenerator.VALUE_PREFIX_PARAMETER, value = "L_"),
                    @org.hibernate.annotations.Parameter(name = StringSequenceIDGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    @OneToOne()
    @JoinColumn(name = "ID_BOOK", referencedColumnName = "ID")
    private Carte carte;

    @ManyToOne
    @JoinColumn(name = "ID_SUBSCRIBER")
    private Abonat abonat;

    @Column(name = "LOAN_STATUS")
    @Enumerated(EnumType.STRING)
    private StatusImprumut status;

    public Imprumut(Carte carte, Abonat abonat, StatusImprumut statusImprumut) {
        this.carte = carte;
        this.abonat = abonat;
        this.status = statusImprumut;
    }
}
