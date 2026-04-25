package com.example.homemortgages.service;

import com.example.homemortgages.domain.MortgageApplication;
import com.example.homemortgages.exception.ResourceNotFoundException;
import com.example.homemortgages.repository.MortgageApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MortgageApplicationService {

    private final MortgageApplicationRepository repository;

    public MortgageApplication createApplication(MortgageApplication application) {
        application.setStatus("PENDING");
        return repository.save(application);
    }

    public List<MortgageApplication> getAllApplications() {
        return repository.findAll();
    }

    public MortgageApplication getApplicationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mortgage Application not found for this id :: " + id));
    }

    public MortgageApplication updateApplicationStatus(Long id, String status) {
        MortgageApplication application = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mortgage Application not found for this id :: " + id));
        application.setStatus(status);
        return repository.save(application);
    }

    public void deleteApplication(Long id) {
        MortgageApplication application = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mortgage Application not found for this id :: " + id));
        repository.delete(application);
    }
}
