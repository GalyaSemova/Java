import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class E06AddingANewAddressAndUpdatingTheEmployee {
    public static void main(String[] args) {
        EntityManager em = Utils.createEntityManager();
        Scanner sc = new Scanner(System.in);
        String eLastName = sc.nextLine();
        em.getTransaction().begin();

        Town townToChangeAddress = em.createQuery("FROM Town WHERE id = 7", Town.class)
                        .getSingleResult();
        List<Employee> employeeList =
                List.copyOf(em.createQuery("FROM Employee WHERE lastName = :eLastName", Employee.class)
                                .setParameter("eLastName", eLastName)
                                .getResultList()
                );


        Address changedAddress = new Address();
        changedAddress.setText("Vitoshka 15");
        changedAddress.setTown(townToChangeAddress);

        em.persist(changedAddress);


        employeeList.forEach(e -> e.setAddress(changedAddress));

        em.flush();
        em.getTransaction().commit();
        em.close();

    }
}
