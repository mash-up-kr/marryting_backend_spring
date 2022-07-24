package mashup.spring.jsmr.adapter.api.profileKeyword.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProfileKeywordRequestDTO {

    private Long keywordId;
    private String keyword;
}
