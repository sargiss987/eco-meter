package com.example.ecometer.constants;

public final class EcoMeterConstants {

  private EcoMeterConstants() {}

  // exception messages
  public static final String INTERNAL_SERVER_ERROR = "Internal server error";
  public static final String USER_NOT_FOUND = "User not exist in system";
  public static final String VALIDATION_ERROR = "Validation error: ";

  // usage measurements validation constants
  public static final String GAS_USAGE_MIN_VALUE = "0.0";
  public static final String GAS_USAGE_MAX_VALUE = "9999999999.99999";
  public static final int GAS_USAGE_VALUE_LENGTH = 15;
  public static final int GAS_USAGE_VALUE_FRACTION_LENGTH = 5;
  public static final String COLD_WATER_USAGE_MIN_VALUE = "0.0";
  public static final String COLD_WATER_USAGE_MAX_VALUE = "9999999999.99999";
  public static final int COLD_WATER_USAGE_VALUE_LENGTH = 15;
  public static final int COLD_WATER_USAGE_VALUE_FRACTION_LENGTH = 5;
  public static final String HOT_WATER_USAGE_MIN_VALUE = "0.0";
  public static final String HOT_WATER_USAGE_MAX_VALUE = "9999999999.99999";
  public static final int HOT_WATER_USAGE_VALUE_LENGTH = 15;
  public static final int HOT_WATER_USAGE_VALUE_FRACTION_LENGTH = 5;
}
