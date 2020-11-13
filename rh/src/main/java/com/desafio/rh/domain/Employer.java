package com.desafio.rh.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Employer implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @JsonSetter("first_name")
    private String firstName;

    @NotEmpty
    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    @NotEmpty
    @NotNull
    @JsonProperty("career")
    private String career;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="employer_department",
            joinColumns = {@JoinColumn(name = "employer_id")},
            inverseJoinColumns = {@JoinColumn(name = "department_id")})
    @JsonIgnore
    private List<Department> department;

}
