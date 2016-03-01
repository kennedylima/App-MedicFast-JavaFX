
package com.medicfast.service;

import com.medicfast.DAO.MedicoDAO;
import com.medicfast.model.Especialidade;
import com.medicfast.model.Medico;
import com.medicfast.model.PontoAtendimento;
import com.medicfast.model.Usuario;
import java.util.List;

public class MedicoService extends Exception {
    public String msg;
    static MedicoDAO mdao = new MedicoDAO();
    
    public String salvar(Medico m){
        if(m.getNome().isEmpty()){
            msg = "Insira um nome !";
            return msg;
        }else if(m.getEndereco().isEmpty()){
            msg = "Insira um Enedereco";
            return msg;
        }else if(m.getEspecialidade().isEmpty()){
            msg = "Insira uma Especialidade !";
            return msg;
        }else if(m.getTelefone().isEmpty()){
            msg = "Insira um Telefone !";
            return msg;
        }
        
        return mdao.salvar(m);
    }
    
    public List<Medico> buscarMedico(Usuario usuarioLogado){
        return mdao.buscarMedico(usuarioLogado);
    }
    
    public List<Medico> buscarTodosOsMedicos(){
        return mdao.buscarTodosOsMedicos();
    }
    public String excluir(Medico m){
       return mdao.excluir(m);
    }

    public List<Medico> buscarMedicoPorNome(String especialidade) {
        return mdao.buscarMedicoPorNome(especialidade);
    }

    public List<Medico> buscarMedicoPorPonto(PontoAtendimento pontoAtendimento) {
        return mdao.buscarMedicoPorPonto(pontoAtendimento);
    }

    public List<Medico> buscarMedicoPorEspecialidade(Especialidade e) {
        return mdao.buscarMedicoPorEspecialidade(e);
    }
    
}
