package com.cala.rabbits.models.training.dto;

import java.time.LocalDate;

public record SessionsDTO(LocalDate doDate, String day, int week) {}