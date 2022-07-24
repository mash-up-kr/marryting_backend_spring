package mashup.spring.jsmr.adapter.api.wedding.dto;

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

    private Long weddingId;
    private String groomName;
    private String brideName;
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
