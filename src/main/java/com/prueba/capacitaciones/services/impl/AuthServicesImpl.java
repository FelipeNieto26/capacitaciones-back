package com.prueba.capacitaciones.services.impl;

import com.prueba.capacitaciones.dto.login.LoginRequestDto;
import com.prueba.capacitaciones.entity.UserEntity;
import com.prueba.capacitaciones.exceptions.ExceptionGlobalResponse;
import com.prueba.capacitaciones.repository.UserRepository;
import com.prueba.capacitaciones.services.IAuthServices;
import com.prueba.capacitaciones.utils.JwtServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServicesImpl implements IAuthServices {

    private final UserRepository userRepository;
    private final JwtServices jwtService;

    @Override
    public String login(LoginRequestDto loginRequestDto) {

        UserEntity userEntity = userRepository.findByUsername(loginRequestDto.getUsername());

        if (userEntity == null) {
            throw new ExceptionGlobalResponse.UserNotFoundException("Usuario no encontrado");
        }

        if (!userEntity.getPassword().equals(loginRequestDto.getPassword())) {
            throw new ExceptionGlobalResponse.UnauthorizedException("Credenciales inválidas");
        }

        return jwtService.generateToken(loginRequestDto, userEntity.getUsername(), userEntity.getId(), userEntity.isAdmin());
    }

}
