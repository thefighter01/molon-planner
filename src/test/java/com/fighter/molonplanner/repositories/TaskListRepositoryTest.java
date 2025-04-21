package com.fighter.molonplanner.repositories;

import com.fighter.molonplanner.domain.entities.TaskList;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
class TaskListRepositoryTest {

    @Autowired
    private TaskListRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }




    @Test
    @Transactional
    void checkIfTaskListExists() {

        // given
        TaskList taskList = new TaskList();
        taskList.setTitle("Test");
        taskList.setDescription("Test");
        taskList.setCreatedDate(LocalDateTime.now());
        taskList.setUpdatedDate(LocalDateTime.now());
        taskList.setTasks(null);

        taskList = underTest.save(taskList);

        // when

        TaskList result = underTest.findById(taskList.getId()).orElse(null);
        log.error("fucken result is : {}", result);

        // then
        assertThat(result).isEqualTo(taskList);
    }


    @Test
    @Transactional
    void checkIfTaskListNotExists() {

        // given
        TaskList taskList = new TaskList();
        taskList.setId(UUID.randomUUID());
        taskList.setTitle("Test");
        taskList.setDescription("Test");
        taskList.setCreatedDate(LocalDateTime.now());
        taskList.setUpdatedDate(LocalDateTime.now());
        taskList.setTasks(null);

       // taskList = underTest.save(taskList);

        // when

        TaskList result = underTest.findById(taskList.getId()).orElse(null);
        log.error("fucken result is : {}", result);

        // then
        assertThat(result).isNull();
    }

}