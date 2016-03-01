
package com.medicfast.service;

import com.medicfast.DAO.UsuarioDAO;
import com.medicfast.model.Usuario;
import java.util.List;


public class UsuarioService extends Exception {
    static UsuarioDAO dao = new UsuarioDAO();
    
    public String salvar(Usuario u) {
        
        if(u.getNome().isEmpty()){
            return "Insira um Nome !";
        }
        else if(u.getUsuario().isEmpty()){
            return "Insira um Nome de Usuario !";
        }
        else if(u.getSenha().isEmpty()){
            return "Insira uma Senha !";
        }
        
        return dao.salvar(u);
    }
    
    public Usuario indentificarUsuario(String u, String s){
        return dao.indentificarUsuario(u, s);
    }
    
    public List<Usuario> buscarUsuarios(){
       return dao.buscarUsuarios();
    }

    public String excluir(Usuario u) {
        return dao.excluir(u);
    }

    public List<Usuario> buscarNome(String text) {
        return dao.buscarNome(text);
    }
}
