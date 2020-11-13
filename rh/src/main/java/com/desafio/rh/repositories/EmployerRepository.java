package com.desafio.rh.repositories;


import com.desafio.rh.domain.Employer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployerRepository extends BaseRepository<Employer, Long> {

    List<Employer> findEmployersByDepartment_Name(String departmentName);

    @Query(value="SELECT * \n" +
            "FROM \n" +
            "EMPLOYER  E,\n" +
            "EMPLOYER_DEPARTMENT ED,\n" +
            "DEPARTMENT D\n" +
            "WHERE E.ID = ED.EMPLOYER_ID \n" +
            "AND ED.DEPARTMENT_ID = D.ID \n" +
            "AND D.NAME = :departmentName",nativeQuery = true)
    List<Employer> findEmployersByDepartmentName(String departmentName);
}
