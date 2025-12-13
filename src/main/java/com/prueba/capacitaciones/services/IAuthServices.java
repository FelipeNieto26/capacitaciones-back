package com.prueba.capacitaciones.services;

import com.prueba.capacitaciones.dto.login.LoginRequestDto;

public interface IAuthServices {

    String login(LoginRequestDto loginRequestDto);

}
