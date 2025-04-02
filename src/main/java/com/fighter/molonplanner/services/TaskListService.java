package com.fighter.molonplanner.services;

import com.fighter.molonplanner.domain.entities.TaskList;
import com.fighter.molonplanner.repositories.TaskListRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {

    List<TaskList> ListTaskLists();

    TaskList createTaskList(TaskList taskList);

    Optional<TaskList> getTaskList(UUID id);

    TaskList updateTaskList(UUID id , TaskList taskList);

    void deleteTaskList(UUID id);
}
