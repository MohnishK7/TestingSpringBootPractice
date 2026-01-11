package com.LearningTesting.TestingApp.services.impl;

import com.LearningTesting.TestingApp.TestContainerConfiguration;
import com.LearningTesting.TestingApp.dto.EmployeeDto;
import com.LearningTesting.TestingApp.entities.Employee;
import com.LearningTesting.TestingApp.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Import(TestContainerConfiguration.class) // and use the dB that is provided by test container configuration
//@DataJpaTest //this is only valid for repository and entity related code and not for service related code
//@SpringBootTest //for integration test...for testing the service use mocking mockito
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //don't replace the dB
class EmployeeServiceImplTest {

    /*
    * Mock the employee Repository now
    * and with the help of Inject mock put these @Mock
    private EmployeeRepository employeeRepository; inside the employee service impl
    * and soon on.
    * */
    @Mock
    private EmployeeRepository employeeRepository;

    //we are using the real model mapper that is present the spring boot..not creating the mock
    @Spy
    private ModelMapper modelMapper;

    //@Autowired
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee mockEmployee;
    private EmployeeDto mockEmployeeDto;

    @BeforeEach
    void setUp(){
        Long id = 1L;
        mockEmployee = Employee.builder()
                .id(id)
                .email("mk@gmail.com")
                .name("mk")
                .salary(200L)
                .build();

        mockEmployeeDto = modelMapper.map(mockEmployee, EmployeeDto.class);
    }


    @Test
    void testGetEmployeeById_When_EmployeeId_Is_Then_Return_EmployeeDto(){
        //employeeService.getEmployeeById(1L);

        //Assign
//        Long id = 1L;
//        Employee mockEmployee = Employee.builder()
//                .id(id)
//                .email("mk@gmail.com")
//                .name("mk")
//                .salary(200L)
//                .build();
        Long id = mockEmployee.getId();
        //stubbing
        //not the real employee repo :)
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee));

        //Act
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);


        //Assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getId()).isEqualTo(id);
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());

        verify(employeeRepository).findById(id); //on the particular mock(employee Repo) was the method called ?
        //verify(employeeRepository, atLeast(2)).findById(id);
    }


    @Test
    void testCreateNewEmployee_When_Valid_Employee_Then_Create_New_Employee(){
        //assign
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of());
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);
        //act

        EmployeeDto employeeDto = employeeService.createEmployee(mockEmployeeDto);

        //assert


        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployeeDto.getEmail());

        //assertThat(employeeDto.getSalary()).isGreaterThan(300);
        //verify(employeeRepository).save(any(Employee.class));

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee captureEmployee = employeeArgumentCaptor.getValue();
        assertThat(captureEmployee.getEmail()).isEqualTo(mockEmployee.getEmail());
    }
}