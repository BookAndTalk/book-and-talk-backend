package book.talk.createbook.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByIsbn13(Long isbn13);
    Optional<BookEntity> findById(Long bookId);
}
