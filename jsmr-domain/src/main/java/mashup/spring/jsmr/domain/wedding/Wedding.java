package mashup.spring.jsmr.domain.wedding;

import lombok.*;
import mashup.spring.jsmr.domain.BaseEntity;
import mashup.spring.jsmr.domain.user.User;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Wedding extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groom_id")
    private User groom;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bride_id")
    private User bride;

    private String weddingHallAddress;

    private LocalDate weddingDate;

    @OneToMany(mappedBy = "wedding", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<WeddingChannel> weddingChannels = new ArrayList<>();
}
