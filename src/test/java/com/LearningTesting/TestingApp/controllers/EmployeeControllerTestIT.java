package com.LearningTesting.TestingApp.controllers;

import com.LearningTesting.TestingApp.TestContainerConfiguration;
import com.LearningTesting.TestingApp.dto.EmployeeDto;
import com.LearningTesting.TestingApp.entities.Employee;
import com.LearningTesting.TestingApp.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@Import(TestContainerConfiguration.class)
@AutoConfigureWebTestClient(timeout = "100000") //this will auto config web test client
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //Integration Test
class EmployeeControllerTestIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    //use the employee Repository to first the save an employee
    private Employee testEmployee;
    private EmployeeDto testEmployeeDto;

    @BeforeEach
    void setup(){
        testEmployee = Employee.builder()
                //.id(1L)
                .email("mk@gmail.com")
                .name("Mohnish")
                .salary(200L)
                .build();
        testEmployeeDto = EmployeeDto.builder()
                //.id(1L)
                .email("mk@gmail.com")
                .name("Mohnish")
                .salary(200L)
                .build();
        employeeRepository.deleteAll(); //every time now we will save new employee to avoid the exception
    }

    //GetController
    //happy case
    @Test
    void testGetEmployeeById_success(){
        Employee savedEmployee = employeeRepository.save(testEmployee);

        testEmployeeDto.setId(savedEmployee.getId());

        webTestClient.get()
                .uri("/employees/{id}", savedEmployee.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeeDto.class)
                .isEqualTo(testEmployeeDto);

    }

    @Test
    void testGetEmployeeById_Failure(){
        webTestClient.get()
                .uri("/employees/1")
                .exchange()
                .expectStatus().isNotFound();
    }

    //CreateController
    //sad case
    @Test
    void testCreateEmployee_whenEmployeeAlreadyExistThen_ThrowException(){
        Employee savedEmployee = employeeRepository.save(testEmployee); //save the employee

        webTestClient.post()
                .uri("/employees")
                .bodyValue(testEmployeeDto)//again saving the employee with same email id
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testCreateEmployee_whenEmployeeDoesNotExist_ThenCreateEmployee(){
        webTestClient.post()
                .uri("/employees")
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.email").isEqualTo(testEmployeeDto.getEmail())
                .jsonPath("$.name").isEqualTo(testEmployeeDto.getName());
    }
}




















