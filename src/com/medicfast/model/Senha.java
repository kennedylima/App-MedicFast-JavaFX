
package com.medicfast.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Senha implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer numero;
    
    
    @ManyToOne
    @JoinColumn(name="Id_PontoAtendimento")
    private PontoAtendimento pontoAtendimento;
    
    @ManyToOne
    @JoinColumn(name="Id_Especialidade")
    private Especialidade especialidade;
    
    @ManyToOne
    @JoinColumn(name="Id_Ocorrencia")
    private TipoOcorrencia ocorrencia;
    
    @Column
    private String sintoma;
    
    @Column
    private String observacao;
    
    @Column
    private Boolean atendido;
    
    @Column
    private String horario;

    @Column
    private String data;
    
    @Column
    private Boolean chamado;

    
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getAtendido() {
        return atendido;
    }

    public void setAtendido(Boolean atendido) {
        this.atendido = atendido;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public PontoAtendimento getPontoAtendimento() {
        return pontoAtendimento;
    }

    public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {
        this.pontoAtendimento = pontoAtendimento;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

     public TipoOcorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(TipoOcorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Boolean getChamado() {  return chamado; }

    public void setChamado(Boolean chamado) { this.chamado = chamado;  }
}
