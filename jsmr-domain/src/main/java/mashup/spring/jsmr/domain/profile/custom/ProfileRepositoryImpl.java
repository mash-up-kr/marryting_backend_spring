package mashup.spring.jsmr.domain.profile.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.QProfile;
import mashup.spring.jsmr.domain.profileKeyword.ProfileKeyword;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class ProfileRepositoryImpl extends QuerydslRepositorySupport implements ProfileCustomRepository {

    private final QProfile profile = QProfile.profile;

    private final JPAQueryFactory jpaQueryFactory;

    public ProfileRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(ProfileKeyword.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<Profile> findByUserIdByQuerydsl(final Long userId) {
        Profile p = jpaQueryFactory.selectFrom(profile)
                .where(profile.user.id.eq(userId))
                .fetchOne();

        return Optional.ofNullable(p);
    }
}
