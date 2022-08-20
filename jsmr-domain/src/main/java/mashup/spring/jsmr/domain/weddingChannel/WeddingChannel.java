package mashup.spring.jsmr.domain.weddingChannel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.BaseEntity;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.wedding.Role;
import mashup.spring.jsmr.domain.wedding.Wedding;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
public class WeddingChannel extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wedding_id")
    private Wedding wedding;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "default 'GUEST'")
    private Role role;
}
