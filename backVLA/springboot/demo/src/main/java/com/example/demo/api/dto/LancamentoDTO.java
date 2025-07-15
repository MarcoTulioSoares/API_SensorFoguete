package com.example.demo.api.dto;

import java.util.Date;

public class LancamentoDTO {
    private Integer idLancamento;
    private String nome;
    private Date dataLancamento;

    public LancamentoDTO(Integer idLancamento, String nome, Date dataLancamento) {
        this.idLancamento = idLancamento;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
    }

    public Integer getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Integer idLancamento) {
        this.idLancamento = idLancamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
} 