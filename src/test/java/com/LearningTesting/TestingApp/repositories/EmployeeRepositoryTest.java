package com.LearningTesting.TestingApp.repositories;


import com.LearningTesting.TestingApp.TestContainerConfiguration;
import com.LearningTesting.TestingApp.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Import(TestContainerConfiguration.class)
@DataJpaTest //by default config embedded dB
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setup(){
        //create an employee
        employee = Employee.builder()
                //.id(1L)
                .email("mk@gmail.com")
                .name("Mohnish")
                .salary(100L)
                .build();
    }
    //and now we can use this employee in each of the test cases

    //happy test
    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnEmployee() {
        //Arrange or given
        //i want to pass the employee and when call the method then get the employee
        employeeRepository.save(employee); //save the employee in test dB

        //Act or when
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());//this email is coming from setup method

        //Assert or then
        //now we have the employeeList and we can get something
       assertThat(employeeList).isNotNull();
       assertThat(employeeList).isNotEmpty();
       assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());

    }

    //not happy test case
    @Test
    void testFindByEmail_whenEmailIsNotFound_thenReturnEmptyEmployeeList(){
        // given
        String email = "notpresent@gmail.com";
        //when
        List<Employee> employeeList = employeeRepository.findByEmail(email);

        //then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty(); //it should be isEmpty
    }
}
