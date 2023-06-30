import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class E04EmployeesWithASalaryOver50000 {
    public static void main(String[] args) {
        EntityManager em = Utils.createEntityManager();

        em.getTransaction().begin();

                em.createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                        .getResultList()
                        .forEach(e -> System.out.println(e.getFirstName()));

        em.getTransaction().commit();
        em.close();
    }
}
