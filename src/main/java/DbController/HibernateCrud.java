package DbController;

import DbModel.Condeso;
import DbModel.HibernateUtil;
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
    condesodb.setDondePuedeTrabajar(condeso.getDondePuedeTrabajar());
    condesodb.setEntrega(condeso.getEntrega());
    condesodb.setFijos(condeso.isFijos());
    condesodb.setLevel(condeso.getLevel());
    condesodb.setManana(condeso.isManana());
    condesodb.setMaster(condeso.getMaster());
    condesodb.setNombre(condeso.getNombre());
    condesodb.setPersonal(condeso.getPersonal());
    condesodb.setTipo(condeso.getTipo());

    // save condeso object
    session.save(condesodb);


    // commit transaction
    session.getTransaction().commit();
    session.close();

    HibernateUtil.shutdown();
    return ("condeso saved, id:  " + condesodb.getId());
  }

}
