package mashup.spring.jsmr.adapter.api.like.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.like.Likes;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateLikeResponseDTO {

    @ApiModelProperty(value = "좋아요 보낸 사람 profileId", example = "1L")
    private Long senderProfileId;

    @ApiModelProperty(value = "좋아요 받는 사람 profileId", example = "2L")
    private Long receiverProfileId;

    @ApiModelProperty(value = "좋아요 보낼 때 보낸 메세지", example = "너가 좋아~")
    private String message;

    @ApiModelProperty(value = "매칭 여부", example = "true or false")
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
