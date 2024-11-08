package book.talk.group.repository;

import book.talk.createbook.entity.BookEntity;
import book.talk.group.domain.Club;
import book.talk.group.domain.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupsRepository extends JpaRepository<Club, Long> {
    List<Club> findByBook(BookEntity book);
}
