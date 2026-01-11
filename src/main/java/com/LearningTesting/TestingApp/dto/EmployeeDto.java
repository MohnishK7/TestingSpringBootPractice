/**
 * @author mohnishkumar on 09 Jan, 2026 at 19:18:55
 */

package com.LearningTesting.TestingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String email;
    private String name;
    private Long salary;
}
