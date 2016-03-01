
package com.medicfast.DAO;

import com.medicfast.entity.SessionEntity;
import com.medicfast.model.Especialidade;
import com.medicfast.model.Medico;
import com.medicfast.model.PontoAtendimento;
import com.medicfast.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class MedicoDAO {
    
    static EntityManager em;
    static EntityTransaction et;
    
    public MedicoDAO() {
        em = SessionEntity.getEntity();
    }
    
    public String salvar(Medico m){
        et = em.getTransaction();
        
        try{
        et.begin();
        em.merge(m);
        et.commit();
        return "Salvo com Sucesso !";
        
        }catch(Exception e){
            et.rollback(); // Desfaz o que foi feito em caso de erro
            return "Não foi possivel Salvar!  Erro = " +e.getMessage();
        }
    }
    
    public String excluir (Medico m){
        et = em.getTransaction();
        try{
            et.begin();
            em.remove(em.getReference(Medico.class, m.getId()));
            et.commit();
            
         return "Removido com Sucesso !";
        
        }catch(Exception e){
            et.rollback(); // Desfaz o que foi feito em caso de erro
            return "Não foi possivel Remover!  Erro = " +e.getMessage();
        }
    }
    
     public List<Medico> buscarMedico(Usuario usuarioLogado) {       
        Session session = (Session) em.getDelegate();
        Query query = session.createQuery("Select  medico  from PontoAtendimento as ponto" +                
                "            inner join  ponto.medico as medico" +
                "            where  ponto.id=:pontoId");

        query.setParameter("pontoId", usuarioLogado.getPontoAtendimento().getId());
        List<Medico> medicos = query.list();
        return medicos;
    }
     
     public List<Medico> buscarTodosOsMedicos(){
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Medico.class);
        return criteria.list();
     }

    public List<Medico> buscarMedicoPorPonto(PontoAtendimento pontoAtendimento) {
        Session session = (Session) em.getDelegate();
        Query query = session.createQuery("Select  medico  from PontoAtendimento as ponto" +                
                "            inner join  ponto.medico as medico" +
                "            where  ponto.id=:pontoId");

        query.setParameter("pontoId", pontoAtendimento.getId());
        List<Medico> medicos = query.list();
        return medicos;
    }

    public List<Medico> buscarMedicoPorNome(String nome) {
        Session session = (Session) em.getDelegate();
        Query query = session.createQuery("Select  medico  from Medico as medico" +
                "            where  medico.nome like :nome");
        
        nome = "%"+nome+"%";
        query.setParameter("nome", nome);
        List<Medico> medicos = query.list();
        return medicos;
    }

    public List<Medico> buscarMedicoPorEspecialidade(Especialidade especialidade) {
        Session session = (Session) em.getDelegate();
        Query query = session.createQuery("Select  medico  from Medico as medico" +
                "            inner join  medico.especialidade as me" +
                "            where  me.nome like :especialidade");
        
        
        query.setParameter("especialidade", especialidade.getNome());
        List<Medico> medicos = query.list();
        return medicos;
    }
    
    
    
}
