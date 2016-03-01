package com.medicfast.DAO;

import com.medicfast.entity.SessionEntity;
import com.medicfast.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsuarioDAO {

    static EntityManager em;
    static EntityTransaction et;

    public UsuarioDAO() {
        em = SessionEntity.getEntity();
        et = em.getTransaction();
    }

    public String salvar(Usuario u) {

        try {
            et.begin();
            em.merge(u);
            et.commit();

            return "Salvo com Sucesso !";

        } catch (Exception e) {
            et.rollback(); // Desfaz o que foi feito em caso de erro
            return "Não foi possivel Salvar!  Erro = " + e.getMessage();
        }
    }

    public String excluir(Usuario u) {

        try {
            et.begin();
            em.remove(em.getReference(Usuario.class, u.getId()));
            et.commit();

            return "Removido com Sucesso !";

        } catch (Exception e) {
            et.rollback(); // Desfaz o que foi feito em caso de erro
            return "Não foi possivel remover!  Erro = " + e.getMessage();
        }
    }

    public List<Usuario> buscarNome(String text) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.ilike("nome", "%" + text + "%"));

        return criteria.list();
    }

    public Usuario indentificarUsuario(String u, String s) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("usuario", u));
        criteria.add(Restrictions.eq("senha", s));
        return (Usuario) criteria.uniqueResult();
    }

    public List<Usuario> buscarUsuarios() {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Usuario.class);
        return criteria.list();
    }
}
