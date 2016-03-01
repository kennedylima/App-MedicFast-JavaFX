
package com.medicfast.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Medico implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer      id;
    
    @Column
    private String     nome;
    
    @Column
    private String    email;
    
    @Column
    private String telefone;
    
    @Column
    private String informacao;
    
    @Column 
    private String CRM;

    @Column
    private String endereco;
    
    @OneToOne
    private Especialidade atendendoComo;
    
    
    private Integer LocalAtendimento;

    @ManyToMany
    private List<Especialidade> especialidade;
    
    @Override
    public String toString() {
        return  nome ;
    }
    /*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PontoAtendimento pontoAtendimento;
    //@JoinColumn(name = "idEspecialidade" ,nullable = false)

    public PontoAtendimento getPontoAtendimento() {
        return pontoAtendimento;
    }

    public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {
        this.pontoAtendimento = pontoAtendimento;
    }
    */

    //Get e Set Id
    public void setId(Integer id) { this.id = id; }
    public Integer getId() { return id; }

    //Get e Set Nome
    public void setNome(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    
    //Get e Set Email
    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }
    
    //Get e Set Telefone
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getTelefone() { return telefone; }
    
    //Get e Set Endereço
    public String getEndereco() {return endereco;}
    public void setEndereco(String endereco) {this.endereco = endereco; }
    
    //Get e Set Especialidade
    public List<Especialidade> getEspecialidade() { return especialidade;}
    public void setEspecialidade(List<Especialidade> especialidade) {this.especialidade = (List<Especialidade>) especialidade;}
 
    //Get e Set CRM
    public String getCRM() {return CRM;}
    public void setCRM(String CRM) {this.CRM = CRM;}
    
    //Get e Set Infromação
    public String getInformacao() {return informacao;}
    public void setInformacao(String informacao) {this.informacao = informacao;}

    //Get e Set AtendendoComo
    public Especialidade getAtendendoComo() {return atendendoComo; }
    public void setAtendendoComo(Especialidade atendendoComo) {this.atendendoComo = atendendoComo;}
    
     //Get e Set LocalAtendimento
    public Integer getLocalAtendimento() {return LocalAtendimento;}
    public void setLocalAtendimento(Integer LocalAtendimento) {this.LocalAtendimento = LocalAtendimento;}
    
    
    
    
}
