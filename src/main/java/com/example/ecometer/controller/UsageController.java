package com.example.ecometer.controller;

import com.example.ecometer.dto.ErrorResponse;
import com.example.ecometer.dto.UsageMeasurementRequest;
import com.example.ecometer.dto.UsageMeasurementResponse;
import com.example.ecometer.mapper.UsageMeasurementMapper;
import com.example.ecometer.service.UsageMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/measurements/")
public class UsageController {

  private final UsageMeasurementService usageMeasurementService;

  @Operation(summary = "Attach new measurements (Gas and Water) to th user by user id")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201"),
        @ApiResponse(
            responseCode = "404",
            description = "Invalid user id supplied",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid measurement value(s) supplied",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      })
  @PostMapping("/{userId}")
  public ResponseEntity<Void> submitMeasurements(
      @PathVariable Long userId,
      @Valid @RequestBody UsageMeasurementRequest usageMeasurementRequest) {
    usageMeasurementService.submitMeasurements(userId, usageMeasurementRequest);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Operation(summary = "Get the user measurements (Gas and Water) history by user id")
  @ApiResponses(
      @ApiResponse(
          responseCode = "200",
          content =
              @Content(
                  mediaType = "application/json",
                  array =
                      @ArraySchema(
                          schema = @Schema(implementation = UsageMeasurementResponse.class)))))
  @GetMapping("/{userId}")
  public ResponseEntity<Set<UsageMeasurementResponse>> getMeasurementHistoryByUserId(
      @PathVariable Long userId) {

    Set<UsageMeasurementResponse> usageMeasurementResponses =
        usageMeasurementService.getMeasurementHistoryByUserId(userId).stream()
            .map(UsageMeasurementMapper::toUsageMeasurementResponse)
            .collect(Collectors.toSet());

    return ResponseEntity.ok(usageMeasurementResponses);
  }
}
