package mashup.spring.jsmr.domain.posting;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.BaseEntity;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannel;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Posting extends BaseEntity {

    private String title;

    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wedding_channel_id")
    private WeddingChannel weddingChannel;

    @LastModifiedDate
    protected LocalDateTime modifiedAt;

}
