package mashup.spring.jsmr.adapter.api.like.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateLikeRequestDTO {

    @ApiModelProperty(value = "보낸사람 profileId", example = "1L")
    @NotNull
    private Long senderProfileId;

    @ApiModelProperty(value = "받는사람 profileId", example = "2L")
    @NotNull
    private Long receiverProfileId;

    @ApiModelProperty(value = "좋아요 보낼 때 보내는 메세지", example = "너가 좋아~")
    @NotEmpty
    private String message;

}
