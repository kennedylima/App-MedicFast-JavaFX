
package com.medicfast.DAO;

import com.medicfast.entity.SessionEntity;
import com.medicfast.model.TipoOcorrencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class TipoOcorrenciaDAO {
    
    static EntityManager em;
    static EntityTransaction et;
    
    public TipoOcorrenciaDAO() {
        em = SessionEntity.getEntity();
    }
    public String salvar(TipoOcorrencia t) {
         et = em.getTransaction();
        
        try{
        et.begin();
        em.merge(t);
        et.commit();
        return "Salvo com Sucesso !";
        
        }catch(Exception e){
            et.rollback(); // Desfaz o que foi feito em caso de erro
            return "Não foi possivel Salvar!  Erro = " +e.getMessage();
        }
    }

    public List<TipoOcorrencia> buscarOcorrencias() {
       Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(TipoOcorrencia.class);
        return criteria.list();
    }

    public String excluir(TipoOcorrencia t) {
        et = em.getTransaction();
        try{
            et.begin();
            em.remove(em.getReference(TipoOcorrencia.class, t.getId()));
            et.commit();
         return "Removido com Sucesso !";
        
        }catch(Exception ex){
            //et.rollback(); // Desfaz o que foi feito em caso de erro
            //System.out.println(ex.getMessage());
            return "Não foi possivel remover, pois há médicos cadastrados com essa especialidade";
        }
    }

    public List<TipoOcorrencia> buscarPorNome(String text) {
       Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(TipoOcorrencia.class);
        criteria.add(Restrictions.ilike("descricao","%"+text+"%"));
        return criteria.list();
    }
    
}
