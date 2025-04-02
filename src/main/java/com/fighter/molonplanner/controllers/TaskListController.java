package com.fighter.molonplanner.controllers;


import com.fighter.molonplanner.domain.dto.TaskListDto;
import com.fighter.molonplanner.domain.entities.TaskList;
import com.fighter.molonplanner.mappers.TaskListMapper;
import com.fighter.molonplanner.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

   public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
   }


    @GetMapping
    public List<TaskListDto> getTaskLists() {
        return taskListService.ListTaskLists().stream().map(taskListMapper::toDto).toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody  TaskListDto taskListDto) {
       TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping("/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID id) {
       return taskListService.getTaskList(id).map(taskListMapper::toDto);
    }

    @PutMapping("/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID id, @RequestBody TaskListDto taskListDto) {
        return taskListMapper.toDto(taskListService.updateTaskList(id, taskListMapper.fromDto(taskListDto)));
    }

    @DeleteMapping("/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID id) {
        taskListService.deleteTaskList(id);
    }



}
