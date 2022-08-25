package mashup.spring.jsmr.domain.like.custom;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import mashup.spring.jsmr.domain.like.Likes;
import mashup.spring.jsmr.domain.like.QLikes;
import mashup.spring.jsmr.domain.profile.QProfile;
import mashup.spring.jsmr.domain.weddingChannel.QWeddingChannel;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class LikeCustomRepositoryImpl extends QuerydslRepositorySupport implements LikeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final QLikes likes = QLikes.likes;
    private final QWeddingChannel weddingChannel = QWeddingChannel.weddingChannel;

    private final QProfile profile = QProfile.profile;

    public LikeCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory1) {
        super(Likes.class);
        this.jpaQueryFactory = jpaQueryFactory1;
    }

    @Override
    public List<Likes> findAllBySenderIdAndIsMatch(Long profileId, Long weddingId, Boolean isMatch) {
        return setReceiverFetchJoinQuery()
                .innerJoin(weddingChannel)
                .on(weddingChannel.profile.id.eq(profile.id))
                .where(
                        likes.sender.id.eq(profileId),
                        likes.isMatch.eq(isMatch),
                        weddingChannel.wedding.id.eq(weddingId)
                )
                .fetch();
    }

    @Override
    public List<Likes> findMatchingProfileWithMessage(Long profileId, Long weddingId, Boolean isMatch) {
        return setSenderFetchJoinQuery()
                .innerJoin(weddingChannel)
                .on(weddingChannel.profile.id.eq(profile.id))
                .where(
                        likes.receiver.id.eq(profileId),
                        likes.isMatch.eq(isMatch),
                        weddingChannel.wedding.id.eq(weddingId)
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
