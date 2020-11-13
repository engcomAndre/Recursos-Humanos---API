package com.desafio.rh.domain.DTO;

import com.desafio.rh.domain.Department;
import com.desafio.rh.domain.Employer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployerDTO {

    @JsonProperty("id")
    private Long id;

    @NotEmpty
    @NotNull
    @JsonProperty("first_name")
    private String firstName;

    @NotEmpty
    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    @NotEmpty
    @NotNull
    @JsonProperty("career")
    private String career;

    @JsonProperty("department")
    private String departmentName;

    public static List<EmployerDTO> mapFromEmployers(List<Employer> employers) {
        return employers.stream().map(
                employer -> EmployerDTO.builder()
                        .id(employer.getId())
                        .firstName(employer.getFirstName())
                        .lastName(employer.getLastName())
                        .career(employer.getCareer())
                        .departmentName(employer.getDepartment().get(0).getName()
                        ).build()
        ).collect(Collectors.toList());

    }

    public static Employer mapToEmployer(EmployerDTO employerDTO, Department department) {
        return Employer.builder()
                .id(employerDTO.getId())
                .firstName(employerDTO.getFirstName())
                .lastName(employerDTO.getLastName())
                .career(employerDTO.getCareer())
                .department(Arrays.asList(department))
                .build();
    }


}


