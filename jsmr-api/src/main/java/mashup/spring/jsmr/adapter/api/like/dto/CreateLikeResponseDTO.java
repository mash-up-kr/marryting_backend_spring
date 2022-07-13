package mashup.spring.jsmr.adapter.api.like.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.like.Likes;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateLikeResponseDTO {

    private Long senderProfileId;

    private Long receiverProfileId;

    private String message;

    private Boolean isMatch;

    @Builder
    public CreateLikeResponseDTO(Long senderProfileId, Long receiverProfileId, String message, Boolean isMatch) {
        this.senderProfileId = senderProfileId;
        this.receiverProfileId = receiverProfileId;
        this.message = message;
        this.isMatch = isMatch;
    }

    public static CreateLikeResponseDTO from(Likes likes) {
        return CreateLikeResponseDTO.builder()
                .senderProfileId(likes.getSender().getId())
                .receiverProfileId(likes.getReceiver().getId())
                .message(likes.getMessage())
                .isMatch(likes.getIsMatch())
                .build();
    }
}
