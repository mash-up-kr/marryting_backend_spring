package mashup.spring.jsmr.domain.weddingChannel.custom;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.QProfile;
import mashup.spring.jsmr.domain.user.Gender;
import mashup.spring.jsmr.domain.weddingChannel.QWeddingChannel;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannel;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class WeddingChannelCustomRepositoryImpl extends QuerydslRepositorySupport implements WeddingChannelCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QWeddingChannel weddingChannel = QWeddingChannel.weddingChannel;
    private final QProfile profile = QProfile.profile;

    public WeddingChannelCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(WeddingChannel.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<WeddingChannel> findByWeddingGuestsByFetch(Gender gender) {
        return setFetchJoinQuery()
                .where(profile.gender.ne(gender))
                .fetch();
    }

    private JPQLQuery<WeddingChannel> setFetchJoinQuery() {
        return jpaQueryFactory.selectFrom(this.weddingChannel)
                .join(weddingChannel.profile, profile)
                .fetchJoin();
    }
}
