import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class E12EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManager em = Utils.createEntityManager();

        em.getTransaction().begin();

        em.createQuery("SELECT department.name, MAX(salary) FROM Employee " +
                "GROUP BY department.name " +
                "HAVING MAX(salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                        .getResultList()
                .forEach(o -> System.out.println(o[0] +" " + o[1]));


         em.getTransaction().commit();
        em.close();
    }


}
