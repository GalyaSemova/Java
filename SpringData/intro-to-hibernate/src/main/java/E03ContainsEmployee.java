import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class E03ContainsEmployee {
    public static void main(String[] args) {

        EntityManager em = Utils.createEntityManager();

        Scanner sc = new Scanner(System.in);
        String fullName = sc.nextLine();

        em.getTransaction().begin();

        try {
            Employee employee = em.createQuery("FROM Employee WHERE concat_ws(' ', first_name, last_name) = :fullName"
                            , Employee.class)
                    .setParameter("fullName", fullName)
                    .getSingleResult();

            em.getTransaction().commit();
            System.out.println("Yes");
            em.close();
        } catch(Exception e) {
            System.out.println("No");

        }

//        String fullName1 = em.createQuery("FROM Employee WHERE concat_ws(' ', first_name, last_name) = :fullName"
//                        , Employee.class)
//                .setParameter("fullName", fullName)
//                .getResultList()
//                .isEmpty() ? "No" : "Yes";
    }
}
