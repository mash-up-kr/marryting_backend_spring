package mashup.spring.jsmr.adapter.api.wedding.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.wedding.Wedding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateWeddingRequestDTO {

    @ApiModelProperty(value = "신랑 이름", example = "신랑 이름")
    @NotBlank
    private String groomName;

    @ApiModelProperty(value = "신부 이름", example = "신부 이름")
    @NotBlank
    private String brideName;

    @ApiModelProperty(value = "결혼식 날짜", example = "2022-07-30")
    @NotNull
    private LocalDate weddingDate;

    public Wedding toEntity() {
        return Wedding.builder()
                .groomName(groomName)
                .brideName(brideName)
                .weddingDate(weddingDate)
                .build();
    }
}
