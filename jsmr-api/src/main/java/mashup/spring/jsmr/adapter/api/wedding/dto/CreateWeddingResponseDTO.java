package mashup.spring.jsmr.adapter.api.wedding.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.wedding.Wedding;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateWeddingResponseDTO {
    @ApiModelProperty(value = "신랑 이름", example = "신랑 이름")
    private String groomName;

    @ApiModelProperty(value = "신부 이름", example = "신부 이름")
    private String brideName;

    @ApiModelProperty(value = "결혼식 참여 코드", example = "A12SD2")
    private String weddingCode;

    @ApiModelProperty(value = "결혼식 날짜", example = "2022-07-30")
    private LocalDate weddingDate;

    @Builder
    public CreateWeddingResponseDTO(String groomName, String brideName, String weddingCode, LocalDate weddingDate) {
        this.groomName = groomName;
        this.brideName = brideName;
        this.weddingCode = weddingCode;
        this.weddingDate = weddingDate;
    }

    public static CreateWeddingResponseDTO from(Wedding wedding) {
        return CreateWeddingResponseDTO.builder()
                .groomName(wedding.getGroomName())
                .brideName(wedding.getBrideName())
                .weddingCode(wedding.getWeddingCode())
                .weddingDate(wedding.getWeddingDate())
                .build();
    }
}
