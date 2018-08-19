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
  public static String SaveCondeso(condeso.Condeso condeso)
  {
    SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();

    Session session = sessionFactory.openSession();

    // begin a transaction 
    session.getTransaction().begin();

    Condeso condesodb = new Condeso();
    condesodb.setAntiguedad(condeso.getAntiguedad());
    condesodb.setCaja(condeso.isCaja());
    condesodb.setContrato(condeso.getContrato());
    //condesodb.setDondePuedeTrabajar(condeso.getDondePuedeTrabajar());
    condesodb.setEntrega(condeso.getEntrega().convertToDbModel());
    condesodb.setFijos(condeso.isFijos());
    condesodb.setLevel(condeso.getLevel());
    condesodb.setManana(condeso.isManana());
    //condesodb.setMaster(condeso.getMaster());
    condesodb.setNombre(condeso.getNombre());
    //condesodb.setPersonal(condeso.getPersonal());
    condesodb.setTipo(condeso.getTipo());

    // save condeso object
    session.save(condesodb);


    // commit transaction
    session.getTransaction().commit();
    session.close();

    HibernateUtil.shutdown();
    return ("condeso saved, id:  " + condesodb.getId());
  }

  public static String UpdateCondeso(Condeso updatedCondeso){
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    session.getTransaction().begin();
    session.update(updatedCondeso);
    session.getTransaction().commit();

    session.close();
    HibernateUtil.shutdown();

    return "Updated condeso: " + updatedCondeso.toString();
  }

  public static String DeleteCondeso(Condeso deletedCondeso) {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    session.getTransaction().begin();
    session.delete(deletedCondeso);
    session.getTransaction().commit();
    session.close();
    HibernateUtil.shutdown();

    return  "Deleted:" + deletedCondeso.toString();
  }

  public static Condeso GetCondeso(Condeso condesoBuscado){
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session =  sessionFactory.openSession();
    Condeso condeso =  (Condeso) session.get(Condeso.class, condesoBuscado.getId());
    session.close();
    HibernateUtil.shutdown();
    return condeso;
  }

  public static List<Condeso> GetAllCondesos(){
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    Criteria criteria = session.createCriteria(Condeso.class);
    List<Condeso> condesos = criteria.list();

    session.close();
    HibernateUtil.shutdown();

    return condesos;
  }

}
