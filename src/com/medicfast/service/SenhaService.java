
package com.medicfast.service;

import com.medicfast.DAO.SenhaDAO;
import com.medicfast.model.Senha;
import com.medicfast.model.Usuario;


public class SenhaService {
    SenhaDAO dao = new SenhaDAO();
    
    public String salvar(Senha s){
        
        return dao.salvar(s);
    }

    public Senha buscarProximaSenha() {
        return dao.buscarProximaSenha();
    }
    

    public Senha buscarUltimoAtendido(Usuario usuarioLogado) {
        return dao.buscarUltimoAtendido(usuarioLogado);
    }
    
    
}
