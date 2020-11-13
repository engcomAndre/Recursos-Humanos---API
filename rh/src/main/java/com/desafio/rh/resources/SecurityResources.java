package com.desafio.rh.resources;

import com.desafio.rh.config.security.SecurityUserDto;
import com.desafio.rh.domain.DTO.EmployerDTO;
import com.desafio.rh.domain.Department;
import com.desafio.rh.domain.Employer;
import com.desafio.rh.repositories.DepartmentRepository;
import com.desafio.rh.schedules.UpdateByServices;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"1 - Login"}, value = "Autenticação", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/login")
public class SecurityResources {

    @ApiOperation(value = "Login", notes = "Realizar autenticação na API.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(
            @ApiResponse(code = 200, message = "Autenticado com sucesso",
                    responseHeaders = {
                            @ResponseHeader(name = "Authorization", description = "Bearer Token", response = String.class)}))
    @PostMapping
    public void login(SecurityUserDto securityUserDto) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

}
