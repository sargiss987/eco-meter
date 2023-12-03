package com.example.ecometer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info =
        @Info(
            title = "Eco meter",
            version = "1.0.0",
            description =
                "Eco Meter API empowers users to submit real-time gas, cold water, and hot water usage data. "
                    + "Additionally, it offers a way to access the historical usage data for each user. "))
public class ApiDocConfig {}
