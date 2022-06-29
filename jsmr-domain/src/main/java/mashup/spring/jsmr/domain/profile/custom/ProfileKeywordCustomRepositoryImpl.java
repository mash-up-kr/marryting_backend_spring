package mashup.spring.jsmr.domain.profile.custom;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileKeyword;
import mashup.spring.jsmr.domain.profile.QProfile;
import mashup.spring.jsmr.domain.profile.QProfileKeyword;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProfileKeywordCustomRepositoryImpl extends QuerydslRepositorySupport implements ProfileKeywordCustomRepository {

    private final QProfileKeyword profileKeywords = QProfileKeyword.profileKeyword;
    private final QProfile profile = QProfile.profile;

    private final JPAQueryFactory jpaQueryFactory;

    public ProfileKeywordCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(ProfileKeyword.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Profile> findByProfileByFetch(final Long userId, final Profile profile) {
        return setFetchJoinQuery()
                .where(this.profileKeywords.profile.eq(profile))
                .where(this.profile.user.id.eq(userId))
                .distinct()
                .fetch();
    }

    private JPQLQuery<Profile> setFetchJoinQuery() {
        return jpaQueryFactory.selectFrom(this.profile)
                .join(profile.profileKeywords, profileKeywords)
                .fetchJoin();
    }
}
