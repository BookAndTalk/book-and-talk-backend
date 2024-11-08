package book.talk.chat.service;

import book.talk.chat.domain.Chat;
import book.talk.chat.domain.Message;
import book.talk.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Transactional(transactionManager = "createChatTransactionManager")
    public void saveChatMessage(Message msg, Long chatRoomId, Long userId) {
        Chat chat = Chat.builder().
                chatRoomId(chatRoomId).
                userId(userId).
                message(msg.getMessage()).
                created_at(new Timestamp(System.currentTimeMillis())).build();

        chatRepository.save(chat);
    }
}
