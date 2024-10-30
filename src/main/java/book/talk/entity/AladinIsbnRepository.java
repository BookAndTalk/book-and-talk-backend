package book.talk.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AladinIsbnRepository extends JpaRepository<AladinIsbnEntity, String> {
    List<AladinIsbnEntity> findByCategory(String category);
}
