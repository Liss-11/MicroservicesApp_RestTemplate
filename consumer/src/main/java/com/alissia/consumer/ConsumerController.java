package com.alissia.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/consumers")
public record ConsumerController(ConsumerService service) {

    @PostMapping()
    public ResponseEntity<String> registerCustomer(@RequestBody ConsumerRegistrationRequest registrationRequest){
        log.info("New customer registration {}", registrationRequest);
        return service.registerConsumer(registrationRequest);
    }
}
