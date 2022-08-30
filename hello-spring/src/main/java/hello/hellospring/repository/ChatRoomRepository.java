package hello.hellospring.repository;

import hello.hellospring.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
@Component
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    Optional<ChatRoom> findByItemAndBuyer(Long itemId, String buyerId);

    Optional<ChatRoom> findById(Long id);

}
