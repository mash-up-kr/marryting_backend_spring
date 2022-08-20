package mashup.spring.jsmr.domain.like.custom;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import mashup.spring.jsmr.domain.like.Likes;
import mashup.spring.jsmr.domain.like.QLikes;
import mashup.spring.jsmr.domain.profile.QProfile;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class LikeCustomRepositoryImpl extends QuerydslRepositorySupport implements LikeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final QLikes likes = QLikes.likes;

    private final QProfile profile = QProfile.profile;

    public LikeCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory1) {
        super(Likes.class);
        this.jpaQueryFactory = jpaQueryFactory1;
    }

    @Override
    public List<Likes> findAllBySenderIdAndIsMatch(Long profileId, Boolean isMatch) {
        return setReceiverFetchJoinQuery()
                .where(
                        likes.sender.id.eq(profileId),
                        likes.isMatch.eq(isMatch)
                )
                .fetch();
    }

    @Override
    public List<Likes> findMatchingProfileWithMessage(Long profileId, Boolean isMatch) {
        return setSenderFetchJoinQuery()
                .where(
                        likes.receiver.id.eq(profileId),
                        likes.isMatch.eq(isMatch)
                )
                .fetch();
    }

    @Override
    public boolean existsBySenderAndReceiver(Long senderId, Long receiverId) {
        return from(this.likes)
                .where(
                        likes.sender.id.eq(senderId),
                        likes.receiver.id.eq(receiverId)
                )
                .fetchCount() > 0;
    }

    private JPQLQuery<Likes> setReceiverFetchJoinQuery() {
        return jpaQueryFactory.selectFrom(this.likes)
                .join(likes.receiver, profile)
                .fetchJoin();
    }

    private JPQLQuery<Likes> setSenderFetchJoinQuery() {
        return jpaQueryFactory.selectFrom(this.likes)
                .join(likes.sender, profile)
                .fetchJoin();
    }

}
