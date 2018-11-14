package DbController;

import condeso.Condeso;
import DbModel.HibernateUtil;
import horario.Dias;
import horario.HorarioMaster;
import horario.Plantillas;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import javax.persistence.TypedQuery;
import org.hibernate.Query;
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
        List<Condeso> condesos = criteria.list();

        session.close();

        return condesos;
    }


        public static List<tiendas.Tiendas> GetAllDTOTiendas () {
          SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
          Session session = sessionFactory.openSession();
          List<tiendas.Tiendas> tiendas = session.createQuery(""
              + "select "
              + "t.id as id, "
              + "t.nombre as nombre, "
              + "t.plantilla as plantilla from Tiendas t"
              ).setResultTransformer(
              Transformers.aliasToBean(tiendas.Tiendas.class)).list();
          session.close();
          return tiendas;
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


        public static List<String> tiendasToList () {
            List<Tiendas> tiendas = GetAllTiendas();
            List<String> tiendasString = new ArrayList<String>();
            for (Tiendas tienda : tiendas) {
                String nombre = tienda.getNombre();
                tiendasString.add(nombre);
            }
            return tiendasString;
        }

    public static Condeso findCondesoId(int id) {
        // en caso de que se quede ordenado simpre los condesos con su id
        return null;
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

    public static List<Dias> GetMesHorarioMaster(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        LocalDate date = LocalDate.of(2018, 11, 10);
        Query query = session.createQuery("SELECT e FROM Dias e WHERE date BETWEEN :start AND :end");
        query.setParameter("start", date.minusDays(2));
        query.setParameter("end", date.plusDays(7));
        List<Dias> map = query.list() ;
        return map;
    }

    /*public static void updateAllCondesos(List<Condeso> condesos) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        for (Condeso c:condesos
        ) {
            session.update(c);
        }
        session.getTransaction().commit();
        session.close();
    }*/
}
