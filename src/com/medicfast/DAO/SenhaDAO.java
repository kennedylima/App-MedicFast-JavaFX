/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medicfast.DAO;

import static com.medicfast.DAO.UsuarioDAO.et;
import com.medicfast.entity.SessionEntity;
import com.medicfast.model.Senha;
import com.medicfast.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Kennedy
 */
public class SenhaDAO {
    
    private static EntityManager em;
    private static EntityTransaction et;
    
    public SenhaDAO() {
        em = SessionEntity.getEntity();
        et = em.getTransaction();
    }
    
    public Senha buscarPorID(int id){
        Session session = (Session) em.getDelegate();
        Criteria c = session.createCriteria(Senha.class);
        c.add(Restrictions.eq("numero", id));
        return (Senha)c.uniqueResult();
    }

    public String salvar(Senha s) {
        try {
            et.begin();
            em.merge(s);
            et.commit();

            return "Salvo com Sucesso !";

        } catch (Exception e) {
            et.rollback(); // Desfaz o que foi feito em caso de erro
            return "Não foi possivel Salvar!  Erro = " + e.getMessage();
        }
    }

    public Senha buscarUltimoAtendido(Usuario usuarioLogado) {
        Session session = (Session) em.getDelegate();
        Criteria c = session.createCriteria(Senha.class);
        c.add(Restrictions.eq("chamado", false));
        c.add(Restrictions.eq("pontoAtendimento", usuarioLogado.getPontoAtendimento()));
        c.setMaxResults(1);
        return (Senha)c.uniqueResult();
    }

    public Senha buscarProximaSenha() { // nao usado
        Session session = (Session) em.getDelegate();
        
        DetachedCriteria max = DetachedCriteria.forClass(Senha.class);
        max.add(Restrictions.eq("chamado", true));
        max.setProjection(Projections.max("numero"));
        
        Criteria c = session.createCriteria(Senha.class);
        c.add(Restrictions.eq("chamado", false));
        
        c.add(Property.forName("numero").eq(max));
        return (Senha)c.uniqueResult();
    }
    
}
