import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class E05EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManager em = Utils.createEntityManager();

        em.getTransaction().begin();

                em.createQuery("FROM Employee WHERE department.name = :name ORDER BY salary, id",
                        Employee.class)
                        .setParameter("name", "Research and Development")
                        .getResultList()
                        .forEach(e ->
                                System.out.printf("%s %s from %s - $%.2f%n",
                                        e.getFirstName(),
                                        e.getLastName(),
                                        e.getDepartment().getName(),
                                        e.getSalary()));

                em.getTransaction().commit();
                em.close();
    }
}
