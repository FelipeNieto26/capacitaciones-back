package com.prueba.capacitaciones.exceptions;

import com.prueba.capacitaciones.dto.utilidades.GenericResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Log4j2
public class ExceptionGlobalResponse {

    private <T> ResponseEntity<GenericResponseDto<T>> build(HttpStatus status, String message, T detail) {
        GenericResponseDto<T> body = GenericResponseDto.<T>builder()
                .codeResponse(status.value())
                .message(message)
                .detail(detail)
                .build();
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<GenericResponseDto<Object>> handleBadCreds(BadCredentialsException ex) {
        log.warn("Credenciales inválidas: {}", ex.getMessage());
        return build(HttpStatus.NOT_FOUND, "Usuario no encontrado", null);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GenericResponseDto<Object>> handleUserNotFound(UserNotFoundException ex) {
        log.warn("Usuario no encontrado: {}", ex.getMessage());
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<GenericResponseDto<Object>> handleUnauthorized(UnauthorizedException ex) {
        log.warn("Acceso no autorizado: {}", ex.getMessage());
        return build(HttpStatus.UNAUTHORIZED, ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponseDto<Object>> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .orElse("Solicitud inválida");
        log.warn("Validación fallida: {}", msg);
        return build(HttpStatus.BAD_REQUEST, msg, null);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<GenericResponseDto<Object>> handleCourseNotFound(CourseNotFoundException ex) {
        log.warn("Curso no encontrado: {}", ex.getMessage());
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(ModuleNotFoundException.class)
    public ResponseEntity<GenericResponseDto<Object>> handleModuleNotFound(ModuleNotFoundException ex) {
        log.warn("Módulo no encontrado: {}", ex.getMessage());
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<GenericResponseDto<Object>> handleConflict(ConflictException ex) {
        log.warn("Conflicto: {}", ex.getMessage());
        return build(HttpStatus.CONFLICT, ex.getMessage(), null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<GenericResponseDto<Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
        log.warn("Violación de integridad: {}", ex.getMostSpecificCause().getMessage());
        return build(HttpStatus.CONFLICT, "Registro duplicado o relación inválida", null);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<GenericResponseDto<Object>> handleBadRequest(Exception ex) {
        log.warn("Solicitud inválida: {}", ex.getMessage());
        return build(HttpStatus.BAD_REQUEST, "Solicitud inválida", null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponseDto<Object>> handleGeneric(Exception ex) {
        log.error("Error inesperado", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", null);
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }

    public static class CourseNotFoundException extends RuntimeException {
        public CourseNotFoundException(String message) {
            super(message);
        }
    }

    public static class ModuleNotFoundException extends RuntimeException {
        public ModuleNotFoundException(String message) {
            super(message);
        }
    }

    public static class ConflictException extends RuntimeException {
        public ConflictException(String message) {
            super(message);
        }
    }
}
