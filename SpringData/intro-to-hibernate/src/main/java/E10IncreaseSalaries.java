import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class E10IncreaseSalaries {
    private static final  List<String> DEPARTMENTS =
            List.of("Engineering",
            "Tool Design",
            "Marketing",
            "Information Services");

    public static void main(String[] args) {
        EntityManager em = Utils.createEntityManager();

        em.getTransaction().begin();
        List<Employee> allEmployees = em.createQuery("FROM Employee WHERE department.name in (:departments)",
                Employee.class)
                .setParameter("departments", DEPARTMENTS)
                .getResultList();


        allEmployees.forEach(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))));


        em.flush();
        em.getTransaction().commit();
        em.close();

        allEmployees.forEach(e -> System.out.printf("%s %s ($%s)%n"
                ,e.getFirstName()
                ,e.getLastName()
                ,e.getSalary()
        ));


    }
}
