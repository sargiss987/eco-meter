package com.example.ecometer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.ecometer.constants.EcoMeterConstants;
import com.example.ecometer.dto.UsageMeasurementRequest;
import com.example.ecometer.exception.EcoMeterException;
import com.example.ecometer.model.UsageMeasurement;
import com.example.ecometer.model.User;
import com.example.ecometer.repository.UsageMeasurementRepository;
import com.example.ecometer.repository.UserRepository;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@SpringBootTest
class UsageMeasurementServiceTest {

  @Autowired private UsageMeasurementService usageMeasurementService;

  @MockBean private UserRepository userRepository;

  @MockBean private UsageMeasurementRepository usageMeasurementRepository;

  @Test
  void submitMeasurements() {

    when(userRepository.findById(any())).thenReturn(Optional.of(new User()));

    usageMeasurementService.submitMeasurements(1L, new UsageMeasurementRequest());

    verify(userRepository, times(1)).findById(any());
    verify(usageMeasurementRepository, times(1)).save(any());
  }

  @Test
  void submitMeasurements_withIncorrectUserId_throwsEcoMeterException() {

    when(userRepository.findById(any())).thenReturn(Optional.empty());

    UsageMeasurementRequest usageMeasurementRequest = new UsageMeasurementRequest();
    EcoMeterException exception =
        assertThrows(
            EcoMeterException.class,
            () -> usageMeasurementService.submitMeasurements(1L, usageMeasurementRequest));

    assertEquals(EcoMeterConstants.USER_NOT_FOUND, exception.getMessage());
    assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
  }

  @Test
  void getMeasurementHistoryByUserId() {
    Set<UsageMeasurement> expected = new HashSet<>();
    expected.add(
        new UsageMeasurement()
            .setId(1L)
            .setGasUsage(BigDecimal.ONE)
            .setColdWaterUsage(BigDecimal.TEN)
            .setHotWaterUsage(BigDecimal.ONE));

    when(usageMeasurementRepository.getMeasurementHistoryByUserId(any())).thenReturn(expected);

    Set<UsageMeasurement> actual = usageMeasurementService.getMeasurementHistoryByUserId(1L);

    assertEquals(expected.size(), actual.size());
    assertTrue(expected.containsAll(actual));
  }
}
