/**
 * @author mohnishkumar on 09 Jan, 2026 at 19:20:02
 */

package com.LearningTesting.TestingApp.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
