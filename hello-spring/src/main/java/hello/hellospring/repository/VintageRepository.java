package hello.hellospring.repository;


import hello.hellospring.domain.VintageBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VintageRepository extends JpaRepository<VintageBoard, Long> {
    Page<VintageBoard> findByVintageTitleContaining(@Param("vintageTitle") String vintageTitle, Pageable pageable); //Containing을 붙이면 like sql작동 //검색시
}
