package mashup.spring.jsmr.adapter.api.profileKeyword.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProfileKeywordRequestDTO {

    @ApiModelProperty(value = "키워드 Id", example = "1L")
    private Long keywordId;

    @ApiModelProperty(value = "키워드 이름", example = "따듯한 or 유머있는")
    private String keyword;
}
