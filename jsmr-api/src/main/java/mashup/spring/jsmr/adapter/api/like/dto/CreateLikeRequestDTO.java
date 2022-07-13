package mashup.spring.jsmr.adapter.api.like.dto;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateLikeRequestDTO {

    @NotNull
    private Long senderProfileId;
    @NotNull
    private Long receiverProfileId;
    @NotEmpty
    private String message;

}
