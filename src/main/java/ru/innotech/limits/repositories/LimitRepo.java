package ru.innotech.limits.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.innotech.limits.entities.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface LimitRepo extends JpaRepository<Limit, Long> {
    Optional<Limit> findLimitByUserId(Long userId);

    @Query("select l.value from Limit l where l.userId = ?1")
    Optional<BigDecimal> findValueByUserId(Long userId);

    @Query("select l from Limit l where l.value < ?1")
    List<Limit> findLimitsUpdated(BigDecimal maxLimit);

    @Modifying
    @Query("update Limit l set l.value = ?1")
    void resetLimits(BigDecimal maxLimit);

}
