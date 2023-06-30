import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

public class E09FindTheLatest10Projects {
    public static void main(String[] args) {
        EntityManager em = Utils.createEntityManager();

        em.getTransaction().begin();

        List<Project> projectsList = em.createQuery("FROM Project ORDER BY startDate DESC, name", Project.class)
                        .setMaxResults(10)
                                .getResultList();

        projectsList.stream()
                .sorted(Comparator.comparing(Project::getName))
        .forEach(p -> System.out.printf(
                "Project name: %s%n" +
                        "Project Description: %s%n" +
                        "Project Start Date:%s%n" +
                        "Project End Date: %s%n", p.getName(),
                p.getDescription(), p.getStartDate(),p.getEndDate()
        ));


        em.getTransaction().commit();
        em.close();
    }
}
