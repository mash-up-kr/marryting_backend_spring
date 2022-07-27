package mashup.spring.jsmr.adapter.api.wedding.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.wedding.Wedding;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeddingParticipateListDTO {

    @ApiModelProperty(value = "웨딩 Id", example = "1L")
    private Long weddingId;

    @ApiModelProperty(value = "신랑 이름", example = "자바")
    private String groomName;

    @ApiModelProperty(value = "신부 이름", example = "코틀린")
    private String brideName;

    @ApiModelProperty(value = "결혼식 날짜", example = "2022-07-27")
    private LocalDate weddingDate;

    @Builder
    public WeddingParticipateListDTO(Long weddingId, String groomName, String brideName, LocalDate weddingDate) {
        this.weddingId = weddingId;
        this.groomName = groomName;
        this.brideName = brideName;
        this.weddingDate = weddingDate;
    }

    public static WeddingParticipateListDTO from(Wedding wedding) {
        return WeddingParticipateListDTO.builder()
                .weddingId(wedding.getId())
                .groomName(wedding.getGroomName())
                .brideName(wedding.getBrideName())
                .weddingDate(wedding.getWeddingDate())
                .build();
    }
}
