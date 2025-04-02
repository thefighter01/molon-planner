package com.fighter.molonplanner.controllers;


import com.fighter.molonplanner.domain.dto.TaskDto;
import com.fighter.molonplanner.mappers.TaskMapper;
import com.fighter.molonplanner.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TasksController {

    private final TaskMapper taskMapper;
    private final TaskService taskService;

    public TasksController(TaskMapper taskMapper, TaskService taskService) {
        this.taskMapper = taskMapper;
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> getTasks(@PathVariable("task_list_id") UUID taskListId) {
        return taskService.listTasks(taskListId).stream().map(taskMapper::toDto).toList();
    }

    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDto taskDto) {
        return taskMapper.toDto(taskService.createTask(taskListId, taskMapper.fromDto(taskDto)));
    }

    @GetMapping("/{task_id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId) {
        return taskService.getTask(taskListId, taskId).map(taskMapper::toDto);
    }

    @PutMapping("/{task_id}")
    public TaskDto updateTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId, @RequestBody TaskDto taskDto) {
        return taskMapper.toDto(taskService.updateTask(taskListId, taskId, taskMapper.fromDto(taskDto)));
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId) {
        taskService.deleteTask(taskListId, taskId);
    }


}