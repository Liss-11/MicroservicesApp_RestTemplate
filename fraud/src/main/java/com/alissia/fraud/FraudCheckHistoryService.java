package com.alissia.fraud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class FraudCheckHistoryService {

    private final FraudCheckHistoryRepository repository;

    public ResponseEntity<FraudCheckResponse> isFraudulentCustomer(Integer customerId){
        FraudCheckHistory fraudCheckHistory = new FraudCheckHistory(
                customerId, false, LocalDateTime.now());
        FraudCheckResponse response = new FraudCheckResponse(fraudCheckHistory.getIsFraudster());
        log.info("fraud check request for customer with ID: {}", customerId);
        repository.save(fraudCheckHistory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
