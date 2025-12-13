package com.prueba.capacitaciones.controller;

import com.prueba.capacitaciones.dto.login.LoginRequestDto;
import com.prueba.capacitaciones.dto.utilidades.GenericResponseDto;
import com.prueba.capacitaciones.services.IAuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("capacitaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final IAuthServices authServices;

    @PostMapping("/auth/login")
    public ResponseEntity<GenericResponseDto<String>> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authServices.login(loginRequestDto);
        return ResponseEntity.ok(new GenericResponseDto<>(200, "Credenciales correctas", token));
    }
}
