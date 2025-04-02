package com.fighter.molonplanner.domain.dto;

import com.fighter.molonplanner.domain.entities.TaskPriority;
import com.fighter.molonplanner.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(UUID id , String title, String description, LocalDateTime dueDate, TaskStatus taskStatus, TaskPriority taskPriority) {
}
