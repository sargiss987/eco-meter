package com.example.ecometer.service;

import com.example.ecometer.constants.EcoMeterConstants;
import com.example.ecometer.dto.UsageMeasurementRequest;
import com.example.ecometer.exception.EcoMeterException;
import com.example.ecometer.mapper.UsageMeasurementMapper;
import com.example.ecometer.model.UsageMeasurement;
import com.example.ecometer.model.User;
import com.example.ecometer.repository.UsageMeasurementRepository;
import com.example.ecometer.repository.UserRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsageMeasurementService {

  private final UsageMeasurementRepository usageMeasurementRepository;
  private final UserRepository userRepository;

  public void submitMeasurements(Long userId, UsageMeasurementRequest usageMeasurementRequest) {

    User user =
        userRepository
            .findById(userId)
            .orElseThrow(
                () ->
                    new EcoMeterException(EcoMeterConstants.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

    usageMeasurementRepository.save(
        UsageMeasurementMapper.toUsageMeasurement(usageMeasurementRequest, user));
  }

  public Set<UsageMeasurement> getMeasurementHistoryByUserId(Long userId) {
    return usageMeasurementRepository.getMeasurementHistoryByUserId(userId);
  }
}
