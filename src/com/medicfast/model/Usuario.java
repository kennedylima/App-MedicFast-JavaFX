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
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer     id;
    
    @Column
    private String    nome;
    
    @Column
    private String usuario;
    
    @Column
    private String   senha;
    
    @Column 
    private Boolean administradorGeral;
    
    @Column 
    private Boolean administradorLocal;
    
    @Column 
    private Boolean colaborador;
    
    @Column
    private String descricaoTipoUsuario;
    
    @ManyToOne
    @JoinColumn(name="Id_PontoAtendimento")
    private PontoAtendimento pontoAtendimento;

    
    //Get Set Id
    public void setId(Integer id) { this.id = id; }
    public Integer getId() { return id; }
    
    //Get Set Nome
    public String getNome() { return nome; }
    public void setNome(String nome) {this.nome = nome;}
    
    //Get Set Usuario
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getUsuario() { return usuario; }
    
    //Get Set Senha
    public void setSenha(String senha) { this.senha = senha; }
    public String getSenha() { return senha; }

    //Get Set Administrador Geral
    public Boolean getAdministradorGeral() {return administradorGeral;}
    public void setAdministradorGeral(Boolean administradorGeral) {this.administradorGeral = administradorGeral;}

    //Get Set Administrador Local
    public Boolean getAdministradorLocal() {return administradorLocal;}
    public void setAdministradorLocal(Boolean administradorLocal) { this.administradorLocal = administradorLocal; }

    //Get Set Colaborador
    public Boolean getColaborador() { return colaborador; }
    public void setColaborador(Boolean colaborador) {this.colaborador = colaborador;}
    
    //Get Set PontoAtendimento
    public PontoAtendimento getPontoAtendimento() {return pontoAtendimento;}
    public void setPontoAtendimento(PontoAtendimento pontoAtendimento) {this.pontoAtendimento = pontoAtendimento;}

    //Get Set Variavel utilizada para colocar a infromação 
    public String getDescricaoTipoUsuario() {return descricaoTipoUsuario;}
    public void setDescricaoTipoUsuario(String descricaoTipoUsuario) {this.descricaoTipoUsuario = descricaoTipoUsuario;}
    
    
    
}
