package com.fighter.molonplanner.mappers.impl;


import com.fighter.molonplanner.domain.dto.TaskListDto;
import com.fighter.molonplanner.domain.entities.Task;
import com.fighter.molonplanner.domain.entities.TaskList;
import com.fighter.molonplanner.domain.entities.TaskStatus;
import com.fighter.molonplanner.mappers.TaskListMapper;
import com.fighter.molonplanner.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

   private final TaskMapper taskMapper;

   public TaskListMapperImpl(TaskMapper taskMapper) {
       this.taskMapper = taskMapper;
   }


    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(taskList.getId(), taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0),
              calculateProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks()).map(tasks -> tasks.stream().map(taskMapper::toDto).toList()).orElse(null));
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(taskListDto.id(), taskListDto.title(), taskListDto.description(), null , null,
                Optional.ofNullable(taskListDto.tasks()).map(tasks -> tasks.stream().map(taskMapper::fromDto).toList()).orElse(null));
    }

    private Double calculateProgress(List<Task> tasks) {
        if (null == tasks) return null;
        return tasks.stream().filter(task -> task.getTaskStatus() == TaskStatus.CLOSED).count() * 1.0 / tasks.size();
    }
}
