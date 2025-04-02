package com.fighter.molonplanner.mappers;

import com.fighter.molonplanner.domain.dto.TaskListDto;
import com.fighter.molonplanner.domain.entities.TaskList;

public interface TaskListMapper {

    TaskListDto toDto(TaskList taskList);

    TaskList fromDto(TaskListDto taskListDto);
}
