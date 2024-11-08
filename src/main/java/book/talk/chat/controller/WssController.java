package book.talk.chat.controller;

import book.talk.chat.domain.Message;
import book.talk.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
@Slf4j
public class WssController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/message/{chatRoomId}/{userId}")
    public void recievedMessage(
            @DestinationVariable Long chatRoomId,
            @DestinationVariable Long userId,
            Message msg
    ) {
        log.info("Message Received -> From: {}, msg: {}", userId, msg.getMessage());

        chatService.saveChatMessage(msg, chatRoomId, userId);

        msg.setUserId(userId);

        messagingTemplate.convertAndSend("/sub/chat/" + chatRoomId, msg);
    }

}

