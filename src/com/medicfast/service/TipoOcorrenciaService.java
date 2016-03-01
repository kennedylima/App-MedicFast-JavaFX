
package com.medicfast.service;

import com.medicfast.DAO.TipoOcorrenciaDAO;
import com.medicfast.model.TipoOcorrencia;
import java.util.List;


public class TipoOcorrenciaService {
    TipoOcorrenciaDAO dao = new TipoOcorrenciaDAO();
    
    public String salvar(TipoOcorrencia t) {
        return dao.salvar(t);
    }

    public List<TipoOcorrencia> buscarOcorrencias() {
       return dao.buscarOcorrencias(); 
    }

    public String excluir(TipoOcorrencia t) {
       return dao.excluir(t);
    }

    public List<TipoOcorrencia> buscarOcorrenciasPorNome(String text) {
        return dao.buscarPorNome(text);
    }
    
}
