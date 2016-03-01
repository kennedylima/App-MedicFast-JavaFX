
package com.medicfast.service;

import com.medicfast.DAO.EspecialidadeDAO;
import com.medicfast.model.Especialidade;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class EspecialidadeService {
    static EspecialidadeDAO dao = new EspecialidadeDAO();
    public String Salvar (Especialidade e){
        
        if(e.getNome().isEmpty()){
            return "Insira o nome da Especialidade";
        }
        EspecialidadeDAO dao = new EspecialidadeDAO();
        return dao.salvar(e);
    }
    
    public static ObservableList<Especialidade> listarTodos(){
        ObservableList<Especialidade> 
         especialidades = FXCollections.observableArrayList( dao.listarTodos());
        
        return especialidades;
    }

    public List<Especialidade> buscarEspecialidades() {
        return dao.listarTodos();
    }

    public String excluir(Especialidade e) {
        return dao.excluir(e);
    }

    public List<Especialidade> buscarPorNome(String text) {
        return dao.buscarPorNome(text);
    }
}
