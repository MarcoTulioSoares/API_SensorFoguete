package com.example.demo.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_LANCAMENTO")
public class LancamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "LancamentoSequence")
    @SequenceGenerator(name = "LancamentoSequence", sequenceName = "SEQ_LANCAMENTO", allocationSize = 1)
    @Column(name = "ID_LANCAMENTO")
    private Integer idLancamento;

    @Column(name = "NOME_LANCAMENTO")
    private String nome;

    @Column(name = "DATA_LANCAMENTO")
    private Date dataLancamento;

    @OneToMany(mappedBy = "lancamento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<VLAEntity> sensores;



}
