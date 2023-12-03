package com.example.ecometer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.ecometer.dto.UsageMeasurementRequest;
import com.example.ecometer.dto.UsageMeasurementResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class UsageControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void submitMeasurements_validData_ReturnsHttpStatusCreated() throws Exception {

    UsageMeasurementRequest request =
        new UsageMeasurementRequest()
            .setGasUsage(BigDecimal.ONE)
            .setColdWaterUsage(BigDecimal.ONE)
            .setHotWaterUsage(BigDecimal.ONE);

    mockMvc
        .perform(
            post("/api/v1/measurements/{userId}", 0L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated());
  }

  @Test
  void submitMeasurements_invalidData_ReturnsHttpStatusBadRequest() throws Exception {

    UsageMeasurementRequest request =
        new UsageMeasurementRequest()
            .setGasUsage(new BigDecimal("999999999999.89098"))
            .setColdWaterUsage(BigDecimal.ONE)
            .setHotWaterUsage(BigDecimal.ONE);

    mockMvc
        .perform(
            post("/api/v1/measurements/{userId}", 0L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void submitMeasurements_notExistingUser_ReturnsHttpStatusNotFound() throws Exception {

    UsageMeasurementRequest request =
        new UsageMeasurementRequest()
            .setGasUsage(BigDecimal.ONE)
            .setColdWaterUsage(BigDecimal.ONE)
            .setHotWaterUsage(BigDecimal.ONE);

    mockMvc
        .perform(
            post("/api/v1/measurements/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isNotFound());
  }

  @Test
  void getMeasurementHistoryByUserId_returnUsageMeasurementResponseSet() throws Exception {

    MvcResult result =
        mockMvc
            .perform(get("/api/v1/measurements/{userId}", 0L))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    String responseContent = result.getResponse().getContentAsString();
    UsageMeasurementResponse[] actual =
        objectMapper.readValue(responseContent, UsageMeasurementResponse[].class);
    assertTrue(actual.length > 0);
  }

  @Test
  void getMeasurementHistoryByUserId_returnsEmptySet() throws Exception {

    MvcResult result =
        mockMvc
            .perform(get("/api/v1/measurements/{userId}", 1L))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    String responseContent = result.getResponse().getContentAsString();
    UsageMeasurementResponse[] actual =
        objectMapper.readValue(responseContent, UsageMeasurementResponse[].class);
    assertEquals(0, actual.length);
  }
}
