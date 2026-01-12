/**
 * @author mohnishkumar on 12 Jan, 2026 at 19:08:09
 */

package com.LearningTesting.TestingApp.advices;

import com.LearningTesting.TestingApp.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
}
