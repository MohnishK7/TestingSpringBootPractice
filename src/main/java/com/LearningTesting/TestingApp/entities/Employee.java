/**
 * @author mohnishkumar on 09 Jan, 2026 at 19:17:25
 */

package com.LearningTesting.TestingApp.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;

    private String name;

    private Long salary;

}
