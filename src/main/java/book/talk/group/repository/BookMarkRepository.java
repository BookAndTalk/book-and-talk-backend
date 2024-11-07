package book.talk.group.repository;

import book.talk.group.domain.BookMark;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookMarkRepository {

    private final EntityManager em;

    public void save(BookMark bookMark) {
        em.persist(bookMark);
    }

    public BookMark findOne(Long id) {
        return em.find(BookMark.class, id);
    }

    public List<BookMark> findAllByCriteria(BookMarkSearch bookMarkSearch) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BookMark> cq = cb.createQuery(BookMark.class);
        Root<BookMark> bookMark = cq.from(BookMark.class);

        List<Predicate> criteria = new ArrayList<>();

        // 북마크 상태 검색
        if (bookMarkSearch.getBookMarkStatus() != null) {
            Predicate status = cb.equal(bookMark.get("status"), bookMarkSearch.getBookMarkStatus());
            criteria.add(status);
        }


        // 조건 적용
        cq.where(cb.and(criteria.toArray(new Predicate[0])));
        TypedQuery<BookMark> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }
}
