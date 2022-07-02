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

    private String message;

    private Boolean isMatch;

    @Builder
    public CreateLikeResponseDTO(Long senderId, Long receiverId, String message, Boolean isMatch) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.isMatch = isMatch;
    }

    public static CreateLikeResponseDTO from(Likes likes){
        return CreateLikeResponseDTO.builder()
                .senderId(likes.getSender().getId())
                .receiverId(likes.getReceiver().getId())
                .message(likes.getMessage())
                .isMatch(likes.getIsMatch())
                .build();
    }
}
