package com.example.ecometer.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.ecometer.dto.UsageMeasurementRequest;
import com.example.ecometer.dto.UsageMeasurementResponse;
import com.example.ecometer.model.UsageMeasurement;
import com.example.ecometer.model.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class UsageMeasurementMapperTest {

  @Test
  void toUsageMeasurement() {
    UsageMeasurementRequest usageMeasurementRequest =
        new UsageMeasurementRequest()
            .setGasUsage(BigDecimal.ONE)
            .setColdWaterUsage(BigDecimal.ONE)
            .setHotWaterUsage(BigDecimal.ONE);

    User user = new User().setId(1L);

    UsageMeasurement actual =
        UsageMeasurementMapper.toUsageMeasurement(usageMeasurementRequest, user);

    assertEquals(BigDecimal.ONE, actual.getGasUsage());
    assertEquals(BigDecimal.ONE, actual.getColdWaterUsage());
    assertEquals(BigDecimal.ONE, actual.getHotWaterUsage());
    assertEquals(1L, actual.getUser().getId());
    assertNotNull(actual.getIssuedAt());
  }

  @Test
  void toUsageMeasurementResponse() {
    LocalDateTime expectedIssuedAt = LocalDateTime.parse("2023-12-02T20:20:40.962");
    UsageMeasurement usageMeasurement =
        new UsageMeasurement()
            .setId(1L)
            .setGasUsage(BigDecimal.ONE)
            .setColdWaterUsage(BigDecimal.ONE)
            .setHotWaterUsage(BigDecimal.ONE)
            .setIssuedAt(expectedIssuedAt);

    UsageMeasurementResponse actual =
        UsageMeasurementMapper.toUsageMeasurementResponse(usageMeasurement);

    assertEquals(1L, actual.getId());
    assertEquals(BigDecimal.ONE, actual.getGasUsage());
    assertEquals(BigDecimal.ONE, actual.getColdWaterUsage());
    assertEquals(BigDecimal.ONE, actual.getHotWaterUsage());
    assertEquals(expectedIssuedAt, actual.getIssuedAt());
  }
}
