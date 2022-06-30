package mashup.spring.jsmr.adapter.api.like.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.like.Likes;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateLikeResponseDTO {

    private Long senderId;

    private Long receiverId;

    private String senderMessage;

    private String receiverMessage;
    private Boolean isMatch;

    @Builder
    public CreateLikeResponseDTO(Long senderId, Long receiverId, String senderMessage, String receiverMessage, Boolean isMatch) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.senderMessage = senderMessage;
        this.receiverMessage = receiverMessage;
        this.isMatch = isMatch;
    }

    public static CreateLikeResponseDTO from(Likes likes){
        return CreateLikeResponseDTO.builder()
                .senderId(likes.getSender().getId())
                .receiverId(likes.getReceiver().getId())
                .senderMessage(likes.getSenderMessage())
                .receiverMessage(likes.getReceiverMessage())
                .isMatch(likes.getIsMatch())
                .build();
    }
}
