package mashup.spring.jsmr.adapter.api.like.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateLikeRequestDTO {

    private Long userId;

    private Long partnerId;

    private String message;

}
