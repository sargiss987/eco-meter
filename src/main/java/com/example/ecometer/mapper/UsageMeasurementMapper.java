package com.example.ecometer.mapper;

import com.example.ecometer.dto.UsageMeasurementRequest;
import com.example.ecometer.dto.UsageMeasurementResponse;
import com.example.ecometer.model.UsageMeasurement;
import com.example.ecometer.model.User;
import java.time.LocalDateTime;

public final class UsageMeasurementMapper {

  private UsageMeasurementMapper() {}

  public static UsageMeasurement toUsageMeasurement(
      UsageMeasurementRequest usageMeasurementRequest, User user) {
    return new UsageMeasurement()
        .setUser(user)
        .setGasUsage(usageMeasurementRequest.getGasUsage())
        .setColdWaterUsage(usageMeasurementRequest.getColdWaterUsage())
        .setHotWaterUsage(usageMeasurementRequest.getHotWaterUsage())
        .setIssuedAt(LocalDateTime.now());
  }

  public static UsageMeasurementResponse toUsageMeasurementResponse(
      UsageMeasurement usageMeasurement) {
    return new UsageMeasurementResponse()
        .setId(usageMeasurement.getId())
        .setGasUsage(usageMeasurement.getGasUsage())
        .setColdWaterUsage(usageMeasurement.getColdWaterUsage())
        .setHotWaterUsage(usageMeasurement.getHotWaterUsage())
        .setIssuedAt(usageMeasurement.getIssuedAt());
  }
}
