import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class E11FindEmployeesByFirstName {
    public static void main(String[] args) {
        EntityManager em = Utils.createEntityManager();

        Scanner sc = new Scanner(System.in);

        String pattern = sc.nextLine().toLowerCase();
        int length = pattern.length();

        em.getTransaction().begin();

        List<Employee> employeeList =
                em.createQuery("FROM Employee WHERE SUBSTRING(LOWER(firstName), 1, :length) = :pattern ", Employee.class)
                        .setParameter("length", length)
                        .setParameter("pattern", pattern)
                                .getResultList();

        employeeList.forEach(e ->
                System.out.printf("%s %s - %s - ($%.2f)%n"
                , e.getFirstName()
                ,e.getLastName()
                ,e.getJobTitle()
                ,e.getSalary()));


        em.getTransaction().commit();

        em.close();

    }
}
