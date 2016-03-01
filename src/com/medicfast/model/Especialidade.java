
package com.medicfast.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Especialidade implements Serializable{
   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Column
    private String nome;
    
    /*@ManyToOne
    @JoinColumn(name = "medico_id", nullable = false, insertable = false, updatable = false)
    private Medico medico;
    */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return  nome ;
    }
    
    
}
