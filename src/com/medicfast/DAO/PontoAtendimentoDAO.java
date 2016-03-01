package com.medicfast.DAO;

import com.medicfast.entity.SessionEntity;
import com.medicfast.model.PontoAtendimento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class PontoAtendimentoDAO {

    static EntityManager em;
    static EntityTransaction et;

    public PontoAtendimentoDAO() {
        em = SessionEntity.getEntity();

    }

    public String salvar(PontoAtendimento pt) {
        et = em.getTransaction();
        try {
            et.begin();
            em.merge(pt);
            et.commit();

            return "Salvo com Sucesso !";

        } catch (Exception e) {
            et.rollback(); // Desfaz o que foi feito em caso de erro
            return "Não foi possivel Salvar!  Erro = " + e.getMessage();
        }
    }

    public String excluir(PontoAtendimento pa) {
        et = em.getTransaction();
        try {
            et.begin();
            em.remove(em.getReference(PontoAtendimento.class, pa.getId()));
            et.commit();

            return "Removido com Sucesso !";

        } catch (Exception e) {
            et.rollback(); // Desfaz o que foi feito em caso de erro
            return "Não foi possivel remover!  Erro = " + e.getMessage();
        }
    }

    public List<PontoAtendimento> buscarPontos() {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(PontoAtendimento.class);
        return criteria.list();
    }

    public List<PontoAtendimento> buscarPontoPorNome(String text) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(PontoAtendimento.class);
        criteria.add(Restrictions.ilike("nome","%"+text+"%"));
        return criteria.list();
    }

}
