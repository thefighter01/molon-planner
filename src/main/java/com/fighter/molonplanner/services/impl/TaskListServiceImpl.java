package com.fighter.molonplanner.services.impl;

import com.fighter.molonplanner.domain.entities.Task;
import com.fighter.molonplanner.domain.entities.TaskList;
import com.fighter.molonplanner.repositories.TaskListRepository;
import com.fighter.molonplanner.services.TaskListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository){
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> ListTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null != taskList.getId()) {
            throw new IllegalArgumentException("TaskList already exists");
        }

        if (null == taskList.getTitle() || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("TaskList title must be present ");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(null, taskList.getTitle(), taskList.getDescription(),
                now, now, null));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    @Transactional
    public TaskList updateTaskList(UUID id, TaskList taskList) {
        if (null == taskList.getId() || !taskList.getId().equals(id)) {
            throw new IllegalArgumentException("TaskList id must match");
        }

        TaskList existedTaskList = taskListRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("TaskList does not exist"));

        existedTaskList.setTitle(taskList.getTitle());
        existedTaskList.setDescription(taskList.getDescription());
        LocalDateTime now = LocalDateTime.now();
        existedTaskList.setUpdatedDate(now);

        return taskListRepository.save(existedTaskList);
    }

    @Override
    public void deleteTaskList(UUID id) {
        taskListRepository.deleteById(id);
    }
}
