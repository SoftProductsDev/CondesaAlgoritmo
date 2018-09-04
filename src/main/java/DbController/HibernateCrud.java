package DbController;

import DbModel.Condeso;
import DbModel.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import DbModel.Tiendas;

import java.util.ArrayList;
import java.util.List;


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
        List<Condeso> condesos = criteria.list();

        session.close();

        return condesos;
    }


        public static List<Tiendas> GetAllDTOTiendas () {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Tiendas.class);
            List<Tiendas> tiendas = criteria.list();
            session.close();
            return tiendas;
        }
        public static List<Tiendas> GetAllTiendas () {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();

            List<DbModel.Tiendas> tiendas = session.createQuery("select " +
                    "       t.id as id, " +
                    "       t.nombre as nombre " +
                    "from Tiendas t ")
                    .setResultTransformer(Transformers.aliasToBean(DbModel.Tiendas.class)).list();

            session.close();

            return tiendas;
        }

        public static String SaveTienda (DbModel.Tiendas tienda){
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();

            session.getTransaction().begin();

            session.save(tienda);
            session.getTransaction().commit();
            session.close();
            return ("tienda saved, id:  " + tienda.getId());
        }

        public static String DeleteTienda (Tiendas deletedTienda){
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
            return "Updated condeso: " + updatedTienda.toString();
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
    }
