package com.cala.rabbits.models.training.dto;

import java.util.List;

public record TrainingDTO(String type, List<WodDTO> exercises) {}
