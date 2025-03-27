package jqpl;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team teamA = new Team();
            teamA.setName("TeamA");

            Team teamB = new Team();
            teamB.setName("TeamB");

            em.persist(teamA);
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("Member1");
            member1.setTeam(teamA);
            member1.setAge(10);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("Member2");
            member2.setTeam(teamB);
            member2.setAge(20);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("Member3");
            member3.setTeam(teamB);
            member3.setAge(30);
            em.persist(member3);
            
            em.flush();
            em.clear();

            String query = "select m from Member m join fetch m.team";

            List<Member> result = em.createQuery(query, Member.class).getResultList();

            for (Member member : result) {
                System.out.println("member.getUsername() + member.getTeam().getName() = "
                        + member.getUsername()
                        + member.getTeam().getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
