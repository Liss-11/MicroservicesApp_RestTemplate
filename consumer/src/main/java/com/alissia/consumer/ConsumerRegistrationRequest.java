package com.alissia.consumer;

public record ConsumerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {}
