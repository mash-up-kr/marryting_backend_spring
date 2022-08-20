package mashup.spring.jsmr.adapter.api.weddingChannel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.wedding.Wedding;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParticipateWeddingChannelResponseDTO {
    @ApiModelProperty(value = "웨딩 ID", example = "1")
    private Long weddingId;

    @Builder
    public ParticipateWeddingChannelResponseDTO(Long weddingId) {
        this.weddingId = weddingId;
    }

    public static ParticipateWeddingChannelResponseDTO from(Wedding wedding) {
        return ParticipateWeddingChannelResponseDTO.builder()
                .weddingId(wedding.getId())
                .build();
    }
}
