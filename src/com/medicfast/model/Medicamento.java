
package com.medicfast.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medicamento implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer         id;
    
    @Column
    private  String  nome;
    
    @Column
    private  String fabricante;
    
    @Column
    private  String peso;
    
    @Column
    private  Integer quantidade;
    
    @Column
    private String obs;
    
    @Column
    private String sintoma;
    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PontoAtendimento pontoAtendimento;

    public PontoAtendimento getPontoAtendimento() {
        return pontoAtendimento;
    }

    public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {
        this.pontoAtendimento = pontoAtendimento;
    }*/

    public String getObs() {
        return obs;
    }

    @Override
    public String toString() {
        return  nome ;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    ////Get e Set Id
    public void setId(Integer id) { this.id = id; }
    public Integer getId() { return id; }

    //Get Set Descricao
    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    
    //Get Set Fabricante
    public void setFabricante(String fabricante) { this.fabricante = fabricante;}
    public String getFabricante() { return fabricante; }
    
}
