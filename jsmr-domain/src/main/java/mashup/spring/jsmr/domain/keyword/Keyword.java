package mashup.spring.jsmr.domain.keyword;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.BaseEntity;
import mashup.spring.jsmr.domain.profileKeyword.ProfileKeyword;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Keyword extends BaseEntity {

    private String keyword;

    @Builder.Default
    @OneToMany(mappedBy = "keyword")
    private List<ProfileKeyword> profileKeywords = new ArrayList<>();
}
