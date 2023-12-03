package com.example.ecometer.dto;

import com.example.ecometer.constants.EcoMeterConstants;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UsageMeasurementRequest {

  @NotNull
  @DecimalMin(value = EcoMeterConstants.GAS_USAGE_MIN_VALUE, inclusive = false)
  @DecimalMax(value = EcoMeterConstants.GAS_USAGE_MAX_VALUE)
  @Digits(
      integer = EcoMeterConstants.GAS_USAGE_VALUE_LENGTH,
      fraction = EcoMeterConstants.GAS_USAGE_VALUE_FRACTION_LENGTH)
  private BigDecimal gasUsage;

  @NotNull
  @DecimalMin(value = EcoMeterConstants.COLD_WATER_USAGE_MIN_VALUE, inclusive = false)
  @DecimalMax(value = EcoMeterConstants.COLD_WATER_USAGE_MAX_VALUE)
  @Digits(
      integer = EcoMeterConstants.COLD_WATER_USAGE_VALUE_LENGTH,
      fraction = EcoMeterConstants.COLD_WATER_USAGE_VALUE_FRACTION_LENGTH)
  private BigDecimal coldWaterUsage;

  @NotNull
  @DecimalMin(value = EcoMeterConstants.HOT_WATER_USAGE_MIN_VALUE, inclusive = false)
  @DecimalMax(value = EcoMeterConstants.HOT_WATER_USAGE_MAX_VALUE)
  @Digits(
      integer = EcoMeterConstants.HOT_WATER_USAGE_VALUE_LENGTH,
      fraction = EcoMeterConstants.HOT_WATER_USAGE_VALUE_FRACTION_LENGTH)
  private BigDecimal hotWaterUsage;
}
