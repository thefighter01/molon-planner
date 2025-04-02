package com.fighter.molonplanner.mappers;

import com.fighter.molonplanner.domain.dto.TaskDto;
import com.fighter.molonplanner.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
