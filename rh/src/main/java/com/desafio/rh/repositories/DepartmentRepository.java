package com.desafio.rh.repositories;


import com.desafio.rh.domain.Department;

import java.util.Optional;

public interface DepartmentRepository extends BaseRepository<Department, Long> {

    Optional<Department> findDistinctFirstByName(String name);

}
