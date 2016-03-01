
package com.medicfast.service;

import com.medicfast.DAO.PontoAtendimentoDAO;
import com.medicfast.model.PontoAtendimento;
import java.util.List;


public class PontoAtendimentoService {
    static PontoAtendimentoDAO dao = new PontoAtendimentoDAO();
   
    public String salvar (PontoAtendimento pt){
        
        if(pt.getNome().isEmpty()){
            return "Insira um Nome !";
        }else if(pt.getNumero().isEmpty()){
            return "Insira o Numero da residência !";
        }else if(pt.getRua().isEmpty()){
            return "Insira o nome da rua !";
        }else if(pt.getTelefone().isEmpty()){
            return "Insira um Telefone !";
        }else if(pt.getBairro().isEmpty()){
            return "Insira um Bairro !";
        }else if(pt.getMedicamento().isEmpty()){
            return "Insira um Medicamento !";
        }else if(pt.getMedico().isEmpty()){
            return "Insira um Medico !";
        }
        
        return dao.salvar(pt);
    }

    public List<PontoAtendimento> buscarPontos() {
        return dao.buscarPontos();
    }

    public String excluir(PontoAtendimento pa) {
       return dao.excluir(pa); 
    }

    public List<PontoAtendimento> buscarPontoPorNome(String text) {
        return dao.buscarPontoPorNome(text);
    }
}
