
package com.medicfast.DAO;

import com.medicfast.entity.SessionEntity;
import com.medicfast.model.Especialidade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class EspecialidadeDAO {
    
    private static EntityManager em;
    private static EntityTransaction et;
    
    public EspecialidadeDAO() {
        em = SessionEntity.getEntity();
        
    }
    
    
    
    public String salvar(Especialidade e) {
        et = em.getTransaction();
        try{
            et.begin();
            em.merge(e);
            et.commit();
            return "Salvo com Sucesso !" ;
            
        }catch(Exception ex){
            et.rollback();
            return "Erro ao Salvar" + ex.getMessage();
        }
        
    }
    
    public String excluir(Especialidade e) {
        et = em.getTransaction();
        try{
            et.begin();
            em.remove(em.getReference(Especialidade.class, e.getId()));
            et.commit();
         return "Removido com Sucesso !";
        
        }catch(Exception ex){
            //et.rollback(); // Desfaz o que foi feito em caso de erro
            //System.out.println(ex.getMessage());
            return "Não foi possivel remover, pois há médicos cadastrados com essa especialidade";
        }
    }
    public List<Especialidade> listarTodos (){
        Session session = (Session) em.getDelegate();
        Criteria c = session.createCriteria(Especialidade.class);
        return c.list();
    }
    
    public List<Especialidade> buscarPorNome(String text) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Especialidade.class);
        criteria.add(Restrictions.ilike("nome","%"+text+"%"));
        return criteria.list();
    }
}
