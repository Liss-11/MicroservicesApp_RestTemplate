package com.alissia.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    private final RestTemplate restTemplate;
    public ResponseEntity<String> registerConsumer(ConsumerRegistrationRequest registrationRequest){
        Consumer consumer = new Consumer(
                registrationRequest.firstName(),
                registrationRequest.lastName(),
                registrationRequest.email()
        );
        try{
            consumerRepository.saveAndFlush(consumer);
            FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                    "http://localhost:8081/api/v1/fraud-check/{customerId}",
                    FraudCheckResponse.class,
                    consumer.getId()
            );
            if(fraudCheckResponse != null && fraudCheckResponse.isFraudster()){
                return new ResponseEntity<>("User is Fraudster", HttpStatus.OK);
            }
            return new ResponseEntity<>("User registered Successfully", HttpStatus.CREATED);
            /*return new ResponseEntity<>("User registered Successfully", HttpStatus.CREATED);*/
        }catch(Exception e){
           log.error(e.getMessage());
        }
        return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
