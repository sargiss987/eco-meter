package com.example.ecometer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.ecometer.model.UsageMeasurement;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "test")
class UsageMeasurementRepositoryTest {

  @Autowired private UsageMeasurementRepository usageMeasurementRepository;

  @Test
  void getMeasurementHistoryByUserId_returnUsageMeasurementSet() {

    Set<UsageMeasurement> measurementHistory =
        usageMeasurementRepository.getMeasurementHistoryByUserId(0L);

    assertEquals(1, measurementHistory.size());
    Optional<UsageMeasurement> actual = measurementHistory.stream().findFirst();
    assertTrue(actual.isPresent());
    assertEquals(0, BigDecimal.ONE.compareTo(actual.get().getGasUsage()));
    assertEquals(0, BigDecimal.ONE.compareTo(actual.get().getColdWaterUsage()));
    assertEquals(0, BigDecimal.ONE.compareTo(actual.get().getHotWaterUsage()));
    assertEquals(0L, actual.get().getUser().getId());
  }

  @Test
  void getMeasurementHistoryByUserId_returnEmptySet() {

    Set<UsageMeasurement> actual = usageMeasurementRepository.getMeasurementHistoryByUserId(1L);

    assertEquals(0, actual.size());
  }
}
