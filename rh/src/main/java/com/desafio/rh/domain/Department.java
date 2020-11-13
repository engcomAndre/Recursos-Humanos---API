package com.desafio.rh.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Department implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @JsonProperty("name")
    private String name;

//    @JsonIgnore
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="DEPARTMENT_EMPLOYER",
//            joinColumns = @JoinColumn(name="department_id"),
//            inverseJoinColumns = @JoinColumn(name="employer_id"))
//    private List<Employer> employers = new ArrayList<>();

}
