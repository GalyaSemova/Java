

import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class E08GetEmployeesWithProject {
    public static void main(String[] args) {
        EntityManager em = Utils.createEntityManager();
        Scanner sc = new Scanner(System.in);

        int employeeId = Integer.parseInt(sc.nextLine());

        em.getTransaction().begin();

        List<Employee> employees = em.createQuery("FROM Employee WHERE id = :employeeId", Employee.class)
                        .setParameter("employeeId", employeeId)
                                .getResultList();


//        employees.forEach(e -> {
//                    System.out.printf("%s %s - ", e.getFirstName(), e.getLastName());
//                    e.getProjects()
//                            .forEach(p -> System.out.println(p.getName()));
//                });

        employees.forEach(e -> {
            System.out.printf("%s %s - %s%n%s", e.getFirstName(), e.getLastName(), e.getJobTitle(),
                    e.getProjects().stream()
                            .map(Project::getName)
                            .sorted()
                            .collect(Collectors.joining(System.lineSeparator())));
        });



        em.getTransaction().commit();
        em.close();
    }
}
