package com.desafio.rh.resources;

import com.desafio.rh.domain.DTO.DepartmentDTO;
import com.desafio.rh.domain.Department;
import com.desafio.rh.services.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"2 - Departamentos"}, value = "Recursos de Eventos", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentResources {

    private final DepartmentService departmentService;

    @ApiOperation(value = "Buscar Departamentos", notes = "Busca de Departamentos.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok().body(departmentService.getAll());
    }

    @ApiOperation(value = "Cadastrar Departamento", notes = "Cadastro de um novo departamentos.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody DepartmentDTO departmentDTO) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(departmentService
                        .insert(departmentDTO).getId())
                .toUri()).build();
    }

    @ApiOperation(value = "Atualizar Departamento", notes = "Atualizar um  Departamento existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody Department department) {
        departmentService.update(id, department);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Remover Departamento", notes = "Remove um  Departamento existente por Id", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
