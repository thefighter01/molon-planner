package com.fighter.molonplanner.mappers.impl;

import com.fighter.molonplanner.domain.dto.TaskDto;
import com.fighter.molonplanner.domain.entities.Task;
import com.fighter.molonplanner.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(taskDto.id(), taskDto.title(), taskDto.description(), taskDto.dueDate(), taskDto.taskStatus(), taskDto.taskPriority() ,null , null , null);
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(), task.getTaskStatus(), task.getTaskPriority());
    }
}
