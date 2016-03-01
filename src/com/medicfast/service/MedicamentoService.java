/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medicfast.service;

import com.medicfast.DAO.MedicamentoDAO;
import com.medicfast.model.Medicamento;
import com.medicfast.model.Usuario;
import java.util.List;

/**
 *
 * @author Kennedy
 */
public class MedicamentoService {
    static MedicamentoDAO dao = new MedicamentoDAO();
   
    public List<Medicamento> buscarMedicamentos() {
      return dao.buscarMedicamentos();
    }
    
    public List<Medicamento> buscarMedicamentos(Usuario usuarioLogado) {
      return dao.buscarMedicamentos(usuarioLogado);
    }
    
    public String salvar(Medicamento m) {
        if(m.getNome().isEmpty()){
            return "Insira o nome do medicamento!";
        
        }else if(m.getFabricante().isEmpty()){
            return "Insira o Fabricante do medicamento !";
        
        }else if(m.getQuantidade() <= 0){
            return "Insira a quantidade do medicamento  !";
            
        }else if (m.getSintoma().isEmpty()){
            return "Insira o Peso do medicamento !";
            
        }/*else if (m.getPeso().isEmpty()){
            return "Insita o Peso do medicamento !";
        }*/
        
        return dao.salvar(m);
        
    }

    public List<Medicamento> buscarPorFabricante(String txt) {
        return dao.buscarPorFabricante(txt);
    }

    public List<Medicamento> buscarPorNome(String txt) {
        return dao.buscarPorNome(txt);
    }

    public String excluir(Medicamento m) {
        return dao.excluir(m);
    }

    public List<Medicamento> buscarPorNome(String txt, Usuario usuarioLogado) {
        return dao.buscarPorNome(txt, usuarioLogado);
    }
    
     public List<Medicamento> buscarPorFabricante(String txt, Usuario usuarioLogado) {
        return dao.buscarPorFabricante(txt, usuarioLogado);
    }
    
}
