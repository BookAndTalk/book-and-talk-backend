package book.talk.group.repository;

import jakarta.persistence.EntityManager;
import book.talk.group.domain.Groups;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupRepository {

    private final EntityManager em;

    public void save(Groups group) {
        if (group.getId() == null) {
            em.persist(group);
        } else {
            em.merge(group);
        }
    }

    public Groups findOne(Long id) {
        return em.find(Groups.class, id);
    }

    public List<Groups> findAll() {
        return em.createQuery("select g from Groups g", Groups.class)
                .getResultList();
    }
}
