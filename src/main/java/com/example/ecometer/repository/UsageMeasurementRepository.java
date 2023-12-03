package com.example.ecometer.repository;

import com.example.ecometer.model.UsageMeasurement;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsageMeasurementRepository extends JpaRepository<UsageMeasurement, Long> {

  @Query(
      value =
          "SELECT * FROM usage_measurement "
              + "WHERE user_id=:userId "
              + "ORDER BY issued_at DESC ",
      nativeQuery = true)
  Set<UsageMeasurement> getMeasurementHistoryByUserId(Long userId);
}
