package DbController;

import condeso.Condeso;
import DbModel.HibernateUtil;
import horario.Dias;
import horario.HorarioMaster;
import horario.Plantillas;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.TypedQuery;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import tiendas.Tiendas;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.metamodel.relational.Tuple;
import org.hibernate.transform.Transformers;



public class HibernateCrud {
    public static String SaveCondeso(Condeso condeso) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        // begin a transaction
        session.getTransaction().begin();

        // save condeso object
        session.save(condeso);

        // commit transaction
        session.getTransaction().commit();
        session.close();

        return ("condeso saved, id:  " + condeso.getId());
    }

    public static String UpdateCondeso(Condeso updatedCondeso) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.getTransaction().begin();
        session.update(updatedCondeso);
        session.getTransaction().commit();

        session.close();

        return "Updated condeso: " + updatedCondeso.toString();
    }

    public static String DeleteCondeso(Condeso deletedCondeso) {
        deletedCondeso.setDondePuedeTrabajar(null);
        deleteCondesoFromTurnos(deletedCondeso);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(deletedCondeso);
        session.getTransaction().commit();
        session.close();

        return "Deleted:" + deletedCondeso.toString();
    }

    public static Condeso GetCondeso(Condeso condesoBuscado) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Condeso condeso = (Condeso) session.get(Condeso.class, condesoBuscado.getId());
        session.close();
        return condeso;
    }

    public static List<Condeso> GetAllCondesos() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Condeso.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFetchMode("condeso.horasMes", FetchMode.JOIN);
        List<Condeso> condesos = criteria.list();
        session.close();
        return condesos;
    }

        public static List<Tiendas> GetAllTiendas () {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Tiendas.class);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<Tiendas> tiendas = criteria.list();
            session.close();
            return tiendas;
        }

        public static String SaveTienda (Tiendas tienda){
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();

            session.getTransaction().begin();

            session.save(tienda);
            session.getTransaction().commit();
            session.close();
            return ("tienda saved, id:  " + tienda.getId());
        }

        public static String DeleteTienda (Tiendas deletedTienda){
            deletedTienda.removeTiendasFromCondesos();
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.delete(deletedTienda);
            session.getTransaction().commit();
            session.close();
            return "Deleted:" + deletedTienda.toString();
        }

        public static String UpdateTienda (Tiendas updatedTienda){
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(updatedTienda);
            session.getTransaction().commit();
            session.close();
            return "Updated tienda: " + updatedTienda.toString();
        }

        public static void UpdateMultipleTiendas (List<Tiendas> updatedTiendas){
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            for (Tiendas t:updatedTiendas){
                session.update(t);
            }
            session.getTransaction().commit();
            session.close();
        }

        public static String UpdatePlantilla(Plantillas plantilla) {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(plantilla);
            session.getTransaction().commit();
            session.close();
            return "Updated tienda: " + plantilla.toString();
        }

        public static void deleteCondesoFromTurnos(Condeso condeso){
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            Query query = session.createQuery(
                "update Turnos t set t.condeso = :nullC where t.condeso = :condeso");
            query.setParameter("nullC", null);
            query.setParameter("condeso", condeso);
            query.executeUpdate();
            session.close();
        }
}
