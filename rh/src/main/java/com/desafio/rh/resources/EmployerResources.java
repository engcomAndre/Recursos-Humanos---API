package com.desafio.rh.resources;

import com.desafio.rh.domain.DTO.EmployerDTO;
import com.desafio.rh.services.EmployerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"3 - Pessoas"}, value = "Recursos de Pessoas", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@RestController
@RequestMapping("/employer")
public class EmployerResources {

    private final EmployerService employerService;

    @ApiOperation(value = "Buscar Pessoas", notes = "Busca de Pessoas por nome de departamento.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @GetMapping
    public ResponseEntity<List<EmployerDTO>> getAll(
            @ApiParam @RequestParam(name = "departmentName", required = false) String departmentName) {
        return ResponseEntity.ok().body(employerService.getAll(departmentName));
    }

    @ApiOperation(value = "Cadastrar Pessoas", notes = "Cadastro de um nova pessoa.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody EmployerDTO employerDTO) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employerService
                        .insert(employerDTO).getId())
                .toUri()).build();
    }

    @ApiOperation(value = "Atualizar Pessoas", notes = "Atualizar um  Pessoa existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody EmployerDTO employerDTO) throws Exception {
        employerService.update(id, employerDTO);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Remover Pessoas", notes = "Remove uma  Pessoa existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        employerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
