import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class E02ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
               EntityManager em = factory.createEntityManager();

               em.getTransaction().begin();
        List<Town> townsList = em.createQuery("FROM Town", Town.class).getResultList();

        for (Town town : townsList) {

            if(town.getName().length() > 5) {
                em.detach(town);
                continue;
            }
            town.setName(town.getName().toUpperCase());
            em.persist(town);
        }
        em.getTransaction().commit();
        em.close();

    }
}
