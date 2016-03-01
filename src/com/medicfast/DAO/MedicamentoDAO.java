/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.medicfast.DAO;

import com.medicfast.entity.SessionEntity;
import com.medicfast.model.Medicamento;
import com.medicfast.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class MedicamentoDAO {

    static EntityManager em;
    static EntityTransaction et;

    public MedicamentoDAO() {

        em = SessionEntity.getEntity();
        
    }

    public String salvar(Medicamento m) {
        et = em.getTransaction();
        try {
            et.begin();
            em.merge(m);
            et.commit();
            return "Salvo com sucesso";
        } catch (Exception ex) {
            et.rollback();
            return "Erro ao salvar" + ex.getMessage();
        }

    }

    public  String excluir(Medicamento m) {
        et = em.getTransaction();
        try {
            et.begin();
            em.remove(em.getReference(Medicamento.class, m.getId()));
            et.commit();
            return "Removido com Sucesso !";

        } catch (Exception e) {
            //et.rollback(); // Desfaz o que foi feito em caso de erro
            return "Não foi possivel remover, pois um ou mais Pontos de Atendimento contém este medicamento!" ;
        }
    }

    public List<Medicamento> buscarMedicamentos() {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Medicamento.class);
        return criteria.list();
    }

    public List<Medicamento> buscarPorFabricante(String txt) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Medicamento.class);
        criteria.add(Restrictions.ilike("fabricante", "%" + txt + "%"));

        return criteria.list();
    }

    public List<Medicamento> buscarPorNome(String txt) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Medicamento.class);
        criteria.add(Restrictions.ilike("nome", "%" + txt + "%"));

        return criteria.list();
    }

    public List<Medicamento> buscarMedicamentos(Usuario usuarioLogado) {
        Session session = (Session) em.getDelegate();
        Query query = session.createQuery("Select  medicamento  from PontoAtendimento as ponto" +                
                "            inner join  ponto.medicamento as medicamento" +
                "            where  ponto.id=:pontoId");

        query.setParameter("pontoId", usuarioLogado.getPontoAtendimento().getId());
        List<Medicamento> medicamento = query.list();
        return medicamento;
    }

    public List<Medicamento> buscarPorNome(String txt, Usuario usuarioLogado) {
        Session session = (Session) em.getDelegate();
        Query query = session.createQuery("Select  medicamento  from PontoAtendimento as ponto" +                
                "            inner join  ponto.medicamento as medicamento" +
                "            where  ponto.id=:pontoId and medicamento.nome like :txt");

        query.setParameter("pontoId", usuarioLogado.getPontoAtendimento().getId());
        String texto = "%"+txt+"%";
        query.setParameter("txt", texto);
        List<Medicamento> medicamento = query.list();
        return medicamento;
    }
    
    public List<Medicamento> buscarPorFabricante(String txt, Usuario usuarioLogado) {
        Session session = (Session) em.getDelegate();
        Query query = session.createQuery("Select  medicamento  from PontoAtendimento as ponto" +                
                "            inner join  ponto.medicamento as medicamento" +
                "            where  ponto.id=:pontoId and medicamento.fabricante like :txt");

        query.setParameter("pontoId", usuarioLogado.getPontoAtendimento().getId());
        String texto = "%"+txt+"%";
        query.setParameter("txt", texto);
        List<Medicamento> medicamento = query.list();
        return medicamento;
    }

}
