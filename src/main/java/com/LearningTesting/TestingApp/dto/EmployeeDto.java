/**
 * @author mohnishkumar on 09 Jan, 2026 at 19:18:55
 */

package com.LearningTesting.TestingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String email;
    private String name;
    private Long salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDto that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getName(), that.getName()) && Objects.equals(getSalary(), that.getSalary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getName(), getSalary());
    }
}
