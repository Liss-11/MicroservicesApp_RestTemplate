package com.alissia.fraud;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudCheckHistoryController {

    private final FraudCheckHistoryService service;

    @GetMapping ("/{customerId}")
    public ResponseEntity<FraudCheckResponse> isFraudster(@PathVariable ("customerId") Integer customerId){
        return service.isFraudulentCustomer(customerId);
    }
}
