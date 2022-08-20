package mashup.spring.jsmr.domain.wedding.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import mashup.spring.jsmr.domain.wedding.QWedding;
import mashup.spring.jsmr.domain.wedding.Wedding;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;

public class WeddingCustomRepositoryImpl extends QuerydslRepositorySupport implements WeddingCustomRepository {

    private final QWedding wedding = QWedding.wedding;

    private final JPAQueryFactory jpaQueryFactory;

    public WeddingCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Wedding.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public void deleteWeddingsByWeddingDateIsBefore(LocalDate localDate) {
        jpaQueryFactory
                .delete(wedding)
                .where(wedding.weddingDate.before(localDate))
                .execute();
    }
}
