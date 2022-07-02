package mashup.spring.jsmr.adapter.api.profile.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.keyword.Keyword;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeywordResponseDTO {
    private Long keywordId;
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
