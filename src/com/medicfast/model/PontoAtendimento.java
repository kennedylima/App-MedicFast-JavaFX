package com.medicfast.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class PontoAtendimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer          id;
    
    @Column
    private String         nome;
    
    @Column
    private String          rua;
    
    @Column
    private String       numero;
    
    @Column
    private String       bairro;
    
    @Column
    private String  complemento;
    
    @Column
    private String     telefone;
    
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinColumn(name="id_Medico")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Medico>       medico;
    
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinColumn(name="id_Medicamento")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Medicamento> medicamento;
    
        
    //Get Set Id
    public void setId(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    
    //Get Set Nome
    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    
    //Get Set Rua
    public void setRua(String rua) { this.rua = rua; }
    public String getRua() { return rua; }
    
    //Get Set Numero
    public void setNumero(String numero) { this.numero = numero; }
    public String getNumero() { return numero; }

    //Get Set Bairro
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getBairro() { return bairro;}

    //Get Set Complemento
    public void setComplemento(String complemento) { this.complemento = complemento; }
    public String getComplemento() { return complemento; }
    
    //Get Set Telefone
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getTelefone() { return telefone; }
    
    
    public List<Medico> getMedico() {
        return  medico;
    }

    public void setMedico(List<Medico> medico) {
        this.medico = (List<Medico>) medico;
    }

    public List<Medicamento> getMedicamento() {
        return  medicamento;
    }

    public void setMedicamento(List<Medicamento> medicamento) {
        this.medicamento = (List<Medicamento>) medicamento;
    }

    @Override
    public String toString() {
        return nome ;
    }
    
    
    
}
