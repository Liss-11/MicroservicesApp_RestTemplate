package com.alissia.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;
    public ResponseEntity<String> registerConsumer(ConsumerRegistrationRequest registrationRequest){
        Consumer consumer = new Consumer(
                registrationRequest.firstName(),
                registrationRequest.lastName(),
                registrationRequest.email()
        );
        try{
            consumerRepository.save(consumer);
            return new ResponseEntity<>("User registered Successfully", HttpStatus.CREATED);
        }catch(Exception e){
           log.error(e.getMessage());
        }
        return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
