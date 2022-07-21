package hello.hellospring.repository;


import hello.hellospring.domain.VintageBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VintageRepository extends JpaRepository<VintageBoard, Long> {
}
