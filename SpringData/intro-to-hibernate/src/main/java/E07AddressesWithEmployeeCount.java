import entities.Address;

import javax.persistence.EntityManager;
import java.util.List;

public class E07AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManager em = Utils.createEntityManager();

        em.getTransaction().begin();

        List<Address> adressList = em.createQuery("FROM Address ORDER BY employees.size DESC", Address.class)
                .setMaxResults(10)
                        .getResultList();


        adressList.forEach(a -> System.out.printf("%s, %s - %d employees%n"
        ,a.getText()
        ,a.getTown().getName()
        ,a.getEmployees().size()));


        em.getTransaction().commit();
        em.close();
    }
}
