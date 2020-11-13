package com.desafio.rh.resources;

import com.desafio.rh.schedules.UpdateByServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"0 - Banco de Dados"}, value = "Recursos de Banco de Dados", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@RestController
@RequestMapping("/database")
public class DataBaseResources {

    private final UpdateByServices updateByServices;

    @ApiOperation(value = "Carregar Dados de Banco", notes = "Carregar Dados de Banco com MockApi.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @GetMapping
    public void updateDatabase() {
        updateByServices.updateDataBaseByService();
    }

}
