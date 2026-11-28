package com.authservice.infrastructure.adapters.in.rest;


import com.authservice.domain.exception.DuplicatedResourceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicatedResourceException.class)
    private ProblemDetail handleDuplicateResourceException(DuplicatedResourceException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());

        problem.setTitle("Duplicated Resource");
        problem.setType(URI.create("https://kandlhealth.do/errors/duplicated-resource"));
        problem.setProperty("field", ex.getField());
        problem.setProperty("timestamp", Instant.now());
        return problem;

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityFallback(DataIntegrityViolationException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                "The operation violates a data integrity constraint."
        );
        problem.setTitle("Data integrity conflict");
        problem.setType(URI.create("https://klhealth.do/errors/data-integrity"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // 3. @Valid validation errors on input DTOs
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        err -> err.getDefaultMessage() != null ? err.getDefaultMessage() : "Invalid value",
                        (existing, replacement) -> existing
                ));

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "One or more fields in the request are invalid."
        );
        problem.setTitle("Validation error");
        problem.setType(URI.create("https://klhealth.do/errors/validation-error"));
        problem.setProperty("errors", fieldErrors);
        problem.setProperty("timestamp", Instant.now());

        return ResponseEntity.badRequest().body(problem);
    }

    // 4. Authentication errors (401) delegated from SecurityConfig via handlerExceptionResolver
    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthenticationException(AuthenticationException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNAUTHORIZED,
                "Invalid credentials or missing/expired access token."
        );
        problem.setTitle("Unauthenticated");
        problem.setType(URI.create("https://klhealth.do/errors/unauthorized"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // 5. RBAC authorization errors (403) delegated from SecurityConfig via handlerExceptionResolver
    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.FORBIDDEN,
                "You do not have sufficient permissions to perform this operation."
        );
        problem.setTitle("Access denied");
        problem.setType(URI.create("https://klhealth.do/errors/forbidden"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Domain invariant errors (e.g. User.verifyUsername, password rules in domain model)
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        problem.setTitle("Invalid domain argument");
        problem.setType(URI.create("https://klhealth.do/errors/invalid-argument"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Fallback so unexpected errors never leak to Tomcat /error and get masked as 401/403
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpectedException(Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected internal error occurred."
        );
        problem.setTitle("Internal server error");
        problem.setType(URI.create("https://klhealth.do/errors/internal-error"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}
