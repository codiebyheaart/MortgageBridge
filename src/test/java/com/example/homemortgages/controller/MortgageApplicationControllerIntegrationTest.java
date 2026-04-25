package com.example.homemortgages.controller;

import com.example.homemortgages.domain.MortgageApplication;
import com.example.homemortgages.repository.MortgageApplicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MortgageApplicationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MortgageApplicationRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateMortgageApplication() throws Exception {
        MortgageApplication application = MortgageApplication.builder()
                .applicantName("John Doe")
                .applicantEmail("john@example.com")
                .loanAmount(new BigDecimal("250000"))
                .interestRate(5.5)
                .termInYears(30)
                .propertyAddress("123 Main St, City")
                .status("PENDING") // Status might be ignored by service, but setting for completeness
                .build();

        mockMvc.perform(post("/api/v1/mortgages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(application)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.applicantName", is("John Doe")))
                .andExpect(jsonPath("$.status", is("PENDING")));
    }

    @Test
    void shouldFetchAllApplications() throws Exception {
        MortgageApplication application = MortgageApplication.builder()
                .applicantName("Jane Doe")
                .applicantEmail("jane@example.com")
                .loanAmount(new BigDecimal("300000"))
                .interestRate(4.5)
                .termInYears(15)
                .propertyAddress("456 Oak Ave")
                .status("PENDING")
                .build();
        repository.save(application);

        mockMvc.perform(get("/api/v1/mortgages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].applicantName", is("Jane Doe")));
    }
}
