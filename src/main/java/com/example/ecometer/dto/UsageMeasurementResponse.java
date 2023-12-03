package com.example.ecometer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UsageMeasurementResponse {

  private Long id;

  private BigDecimal gasUsage;

  private BigDecimal coldWaterUsage;

  private BigDecimal hotWaterUsage;

  private LocalDateTime issuedAt;
}
