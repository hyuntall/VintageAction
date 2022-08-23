package hello.hellospring.repository;

import hello.hellospring.domain.ChatRoom;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    Optional<ChatRoom> findByItemIdAndBuyerId(Long itemId, String buyerId);
}
