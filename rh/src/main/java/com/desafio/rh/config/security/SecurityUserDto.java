package com.desafio.rh.config.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(description = "Classe que representa os atributos de login de usuários.")
public class SecurityUserDto {
    @ApiModelProperty(notes = "Unique identifier of the person. No two persons can have the same id.", example = "admin@admin.com", required = true, position = 0)
    private String username;
    @ApiModelProperty(notes = "Unique identifier of the person. No two persons can have the same id.", example = "@admin", required = true, position = 1)
    private String password;
}
