package Model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
public class Servidores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(nullable = false)
    private String nome;


}
