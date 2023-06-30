import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class E13RemoveTowns {
    public static void main(String[] args) {
        EntityManager em = Utils.createEntityManager();

        Scanner sc = new Scanner(System.in);

        String townInput = sc.nextLine();
        em.getTransaction().begin();


        Town town = em.createQuery("FROM Town  WHERE name = :townInput", Town.class)
                .setParameter("townInput", townInput)
                .getSingleResult();


        List<Address> addressList = em.createQuery("FROM Address " +
                        "WHERE town.id = :id", Address.class)
                .setParameter("id", town.getId())
                .getResultList();

        addressList.forEach(t ->
                t.getEmployees().forEach(e -> e.setAddress(null)));


        addressList.forEach(em::remove);
         em.remove(town);


//        Print

        System.out.printf("%d address in %s deleted", addressList.size(), townInput);


        em.getTransaction().commit();

        em.close();
    }
}
