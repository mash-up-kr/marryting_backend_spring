package mashup.spring.jsmr.adapter.api.keyword.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.keyword.Keyword;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeywordResponseDTO {

    @ApiModelProperty(value = "키워드 Id", example = "1")
    private Long keywordId;

    @ApiModelProperty(value = "키워드", example = "유머 있는")
    private String keyword;

    @Builder
    public KeywordResponseDTO(
            Long keywordId,
            String keyword
    ) {
        this.keywordId = keywordId;
        this.keyword = keyword;
    }

    public static KeywordResponseDTO from(Keyword keyword) {
        return KeywordResponseDTO.builder()
                .keywordId(keyword.getId())
                .keyword(keyword.getKeyword())
                .build();
    }
}
