package quest.crealytics.repo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import quest.crealytics.model.ReportEntity;
import quest.crealytics.model.ReportSite;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@Repository
@AllArgsConstructor
@Slf4j
public class ReportRepo implements IReportRepo {

    private final EntityManager entityManager;


    @Override
    public Flux<ReportEntity> findReportEntityByMonthAndSite(Month month, ReportSite site) {
        log.info("Fetching Repostitory entity from database with respect to month '{}' and site '{}'", month, site);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReportEntity> query = criteriaBuilder.createQuery(ReportEntity.class);
        Root<ReportEntity> from = query.from(ReportEntity.class);

        query.select(from);
        List<Predicate> predicates = new ArrayList<>();
        if (month != null) {
            predicates.add(criteriaBuilder.equal(from.get("id").get("month"), month));
        }
        if (site != null) {
            predicates.add(criteriaBuilder.equal(from.get("id").get("site"), site));
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        return Flux.fromStream(entityManager.createQuery(query).getResultList().stream());
    }

    @Override
    public void populate(List<ReportEntity> reports) {
        log.info("Populating database with records");
        reports.stream().forEach(entityManager::persist);
        log.info("Populated database with records");
    }
}
