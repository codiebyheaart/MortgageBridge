package com.example.homemortgages.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "mortgage_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MortgageApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Applicant name is required")
    private String applicantName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Applicant email is required")
    private String applicantEmail;

    @NotNull(message = "Loan amount is required")
    @Min(value = 10000, message = "Loan amount must be at least 10000")
    private BigDecimal loanAmount;

    @NotNull(message = "Interest rate is required")
    private Double interestRate;

    @NotNull(message = "Term in years is required")
    @Min(value = 1, message = "Term must be at least 1 year")
    private Integer termInYears;

    @NotBlank(message = "Property address is required")
    private String propertyAddress;

    @NotBlank(message = "Status is required")
    private String status; // e.g., PENDING, APPROVED, REJECTED
}
