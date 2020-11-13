package com.desafio.rh.services;


import com.desafio.rh.domain.DTO.EmployerDTO;
import com.desafio.rh.domain.Department;
import com.desafio.rh.domain.Employer;
import com.desafio.rh.repositories.DepartmentRepository;
import com.desafio.rh.repositories.EmployerRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class EmployerService {

    private final EmployerRepository employerRepository;

    private final DepartmentRepository departmentRepository;


    private Employer getById(Long id) {
        return employerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Employer.class.getName()));
    }

    public List<EmployerDTO> getAll(String departmentName) {

        return EmployerDTO.mapFromEmployers(
                departmentName == null ? employerRepository.findAll()
                        : employerRepository.findEmployersByDepartmentName(departmentName));

    }

    public Employer insert(EmployerDTO employerDTO) {
        Department department = new Department();
        if (!employerDTO.getDepartmentName().isEmpty() && employerDTO.getDepartmentName() != null) {
            department = departmentRepository.findDistinctFirstByName(employerDTO.getDepartmentName()).orElse(null);
        }
        Employer employer = EmployerDTO.mapToEmployer(employerDTO, department);

        return employerRepository.save(employer);
    }

    public void update(Long oldId, EmployerDTO updatedEmployer) {
        this.employerRepository.save(updateEmployer(this.getById(oldId), updatedEmployer));
    }

    public void delete(Long id) {
        employerRepository.deleteById(id);
    }

    private Employer updateEmployer(Employer oldEmployer, EmployerDTO updatedEmployer) {
        if (!oldEmployer.getFirstName().equals(updatedEmployer.getFirstName()))
            oldEmployer.setFirstName(updatedEmployer.getFirstName());

        if (!oldEmployer.getLastName().equals(updatedEmployer.getLastName()))
            oldEmployer.setLastName(updatedEmployer.getLastName());

        if (!oldEmployer.getCareer().equals(updatedEmployer.getCareer()))
            oldEmployer.setCareer(updatedEmployer.getCareer());

        return oldEmployer;
    }

}
