package com.fighter.molonplanner.services;

import com.fighter.molonplanner.domain.entities.TaskList;
import com.fighter.molonplanner.repositories.TaskListRepository;
import com.fighter.molonplanner.services.impl.TaskListServiceImpl;
import org.hibernate.sql.ast.tree.expression.Over;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskListServiceTest {

    @Mock private TaskListRepository taskListRepository;

    private TaskListService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TaskListServiceImpl(taskListRepository);
    }





    @Test
    void listTaskLists() {
        // when

        underTest.ListTaskLists();

        // then
        verify(taskListRepository).findAll();
        // this will verify the method findAll() was called
    }

    @Test
    void createTaskList() {
        // given
        TaskList taskList = new TaskList();
        taskList.setTitle("Test");
        taskList.setDescription("Test");
        taskList.setTasks(null);
        // when
        underTest.createTaskList(taskList);
        // then
        ArgumentCaptor<TaskList> taskListArgumentCaptor = ArgumentCaptor.forClass(TaskList.class);
        verify(taskListRepository).save(taskListArgumentCaptor.capture());
        TaskList capturedTaskList = taskListArgumentCaptor.getValue();
        assertThat(capturedTaskList.getTitle()).isEqualTo(taskList.getTitle());
        assertThat(capturedTaskList.getDescription()).isEqualTo(taskList.getDescription());
        assertThat(capturedTaskList.getTasks()).isEqualTo(taskList.getTasks());
        assertThat(capturedTaskList.getCreatedDate()).isNotNull();
        assertThat(capturedTaskList.getUpdatedDate()).isNotNull();
//        assertThat(capturedTaskList.getId()).isNotNull(); the captured argument is null


        assertThat(capturedTaskList.getCreatedDate())
                .isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));

    }

    @Test
    void createTaskListTestNullTitle() {
        // given
        TaskList taskList = new TaskList();
        taskList.setTitle(null);
        taskList.setDescription("Test");
        taskList.setTasks(null);
       // given(taskListRepository.save(taskList)).willReturn(taskList);
        // when
        // then
        //underTest.createTaskList(taskList);
        assertThatThrownBy(()-> underTest.createTaskList(taskList)).isInstanceOf(IllegalArgumentException.class).hasMessage("TaskList title must be present ");


    }
    @Test
    void createTaskListTestNotNullId() {
        // given
        TaskList taskList = new TaskList();
        taskList.setId(UUID.randomUUID());
        taskList.setTitle("test");
        taskList.setDescription("Test");
        taskList.setTasks(null);
        // given(taskListRepository.save(taskList)).willReturn(taskList);
        // when
        // then
        //underTest.createTaskList(taskList);
        assertThatThrownBy(()-> underTest.createTaskList(taskList)).isInstanceOf(IllegalArgumentException.class).hasMessage("TaskList already exists");


    }

    @Test
    void getTaskList() {
    }

    @Test
    void updateTaskList() {
    }

    @Test
    void deleteTaskList() {
    }
}