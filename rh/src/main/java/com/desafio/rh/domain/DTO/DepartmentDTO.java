package com.desafio.rh.domain.DTO;

import com.desafio.rh.domain.Department;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {

    private Long id;

    @NotEmpty
    @NotNull
    @JsonProperty("name")
    private String name;

    public static Department matToDepartament(DepartmentDTO departmentDTO){
        return Department.builder()
                .name(departmentDTO.getName())
                .build();
    }
}
