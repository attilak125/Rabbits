package com.cala.rabbits.models.training.dto;

import java.time.LocalDate;

public record PassDTO(
    int amountLeft, LocalDate creationDate, LocalDate expiresOn, boolean expired) {}
