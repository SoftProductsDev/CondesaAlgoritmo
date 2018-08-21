import DbModel.Condeso;
import java.util.List;
import junit.framework.TestCase;

public class HibernateCrudTest extends TestCase {

  public void test()
  {
    List<Condeso> condesos = DbController.HibernateCrud.GetAllCondesos();
    for (Condeso condeso: condesos
    ) {
      System.out.println(condeso.toString());
    }

  }

}