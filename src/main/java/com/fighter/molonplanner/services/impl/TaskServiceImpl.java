package com.fighter.molonplanner.services.impl;

import com.fighter.molonplanner.domain.entities.Task;
import com.fighter.molonplanner.domain.entities.TaskList;
import com.fighter.molonplanner.domain.entities.TaskPriority;
import com.fighter.molonplanner.domain.entities.TaskStatus;
import com.fighter.molonplanner.repositories.TaskListRepository;
import com.fighter.molonplanner.repositories.TaskRepository;
import com.fighter.molonplanner.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    @Transactional
    public Task createTask(UUID taskListId, Task task) {
        if (null != task.getId()) throw new IllegalArgumentException("Task already has an id");

        if (null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title must be present ");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getTaskPriority()).orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(()-> new IllegalArgumentException("TaskList does not exist"));

        LocalDateTime now = LocalDateTime.now();

        Task newTask = new Task(null, task.getTitle(), task.getDescription(), task.getDueDate(), taskStatus, taskPriority, now, now, taskList);;

        return taskRepository.save(newTask);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID id) {
         return taskRepository.findByTaskListIdAndId(taskListId, id);
    }

    @Override
    @Transactional
    public Task updateTask(UUID taskListId , UUID taskId, Task task) {
        if (null == task.getId() || !task.getId().equals(taskId)) {
            throw new IllegalArgumentException("Task id must match");
        }
        if (null == task.getTaskPriority()) throw new IllegalArgumentException("Task priority must be present");

        if (null == task.getTaskStatus()) throw new IllegalArgumentException("Task status must be present");

        Task taskToUpdate = taskRepository.findByTaskListIdAndId(taskListId ,taskId).orElseThrow(()-> new IllegalArgumentException("Task does not exist"));

        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setDueDate(task.getDueDate());
        taskToUpdate.setTaskPriority(task.getTaskPriority());
        taskToUpdate.setTaskStatus(task.getTaskStatus());
        LocalDateTime now = LocalDateTime.now();
        taskToUpdate.setUpdatedDate(now);



        return taskRepository.save(taskToUpdate);
    }

    @Override
    @Transactional
    public void deleteTask(UUID taskListId, UUID id) {
        taskRepository.deleteByTaskListIdAndId(taskListId, id);
    }


}
