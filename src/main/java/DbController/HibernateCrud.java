package DbController;

import DbModel.Condeso;
import DbModel.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by javier on 13/08/2018.
 */
public class HibernateCrud {
  public static String SaveCondeso(Condeso condeso)
  {
    SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();

    Session session = sessionFactory.openSession();

    // begin a transaction 
    session.getTransaction().begin();

    // save condeso object
    session.save(condeso);

    // commit transaction
    session.getTransaction().commit();
    session.close();
    sessionFactory.close();

    return ("condeso saved, id:  " + condeso.getId());
  }

  public static String UpdateCondeso(Condeso updatedCondeso){
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

    return  "Deleted:" + deletedCondeso.toString();
  }

  public static Condeso GetCondeso(Condeso condesoBuscado){
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session =  sessionFactory.openSession();
    Condeso condeso =  (Condeso) session.get(Condeso.class, condesoBuscado.getId());
    session.close();
    return condeso;
  }

  public static List<Condeso> GetAllCondesos(){
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    Criteria criteria = session.createCriteria(Condeso.class);
    List<Condeso> condesos = criteria.list();

    session.flush();
    session.close();

    return condesos;
  }
}
