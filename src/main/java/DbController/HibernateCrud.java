package DbController;

import DbModel.HibernateUtil;
import condeso.Condeso;
import horario.Plantillas;
import org.hibernate.*;
import tiendas.Tiendas;
import java.util.List;



/*public class HibernateCrud implements CrudOperations {

    @Override
    public int SaveCondeso(Condeso condeso) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        // begin a transaction
        session.getTransaction().begin();

        // save condeso object
        session.save(condeso);

        // commit transaction
        session.getTransaction().commit();
        session.close();

        return 0;
    }
    @Override
    public int UpdateCondeso(Condeso updatedCondeso) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.getTransaction().begin();
        session.merge(updatedCondeso);
        session.getTransaction().commit();

        session.close();

        return 0;
    }
    @Override
    public int DeleteCondeso(Condeso deletedCondeso) {
        deletedCondeso.setDondePuedeTrabajar(null);
        deleteCondesoFromTurnos(deletedCondeso);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(deletedCondeso);
        session.getTransaction().commit();
        session.close();

        return 200;
    }

    public static Condeso GetCondeso(Condeso condesoBuscado) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Condeso condeso = (Condeso) session.get(Condeso.class, condesoBuscado.getId());
        session.close();
        return condeso;
    }
    @Override
    public List<Condeso> GetAllCondesos() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Condeso.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFetchMode("condeso.horasMes", FetchMode.JOIN);
        List<Condeso> condesos = criteria.list();
        session.close();
        return condesos;
    }
    @Override
    public  List<Tiendas> GetAllTiendas () {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Tiendas.class);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<Tiendas> tiendas = criteria.list();
            session.close();
            return tiendas;
        }

    @Override
    public  String SaveTienda (Tiendas tienda){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.getTransaction().begin();

        session.save(tienda);
        session.getTransaction().commit();
        session.close();
        return ("tienda saved, id:  " + tienda.getId());
    }

    @Override
    public int DeleteTienda (Tiendas deletedTienda){
        deletedTienda.removeTiendasFromCondesos();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.delete(deletedTienda);
        session.getTransaction().commit();
        session.close();
        return 0;
    }

    @Override
    public int UpdateTienda (Tiendas updatedTienda){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(updatedTienda);
        session.getTransaction().commit();
        session.close();
        return 0;
    }

    @Override
    public int UpdateMultipleTiendas (List<Tiendas> updatedTiendas){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        for (Tiendas t:updatedTiendas){
            session.update(t);
        }
        session.getTransaction().commit();
        session.close();
        return 0;
    }

    @Override
    public String UpdatePlantilla(Plantillas plantilla) {
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

    @Override
    public int UpdateMultipleCondesos(List<Condeso> condesos) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        for (Condeso t:condesos){
            session.update(t);
        }
        session.getTransaction().commit();
        session.close();
        return 0;
    }
}*/
