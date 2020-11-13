package com.desafio.rh.services;

import com.desafio.rh.domain.DTO.DepartmentDTO;
import com.desafio.rh.domain.Department;
import com.desafio.rh.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private Department getById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Department.class.getName()));
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department insert(DepartmentDTO departmentDTO) {
        return departmentRepository.save(DepartmentDTO.matToDepartament(departmentDTO));
    }

    public void update(Long id, Department updatedDepartment) {
        departmentRepository.save(this.updateDepartment(this.getById(id), updatedDepartment));
    }

    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    private Department updateDepartment(Department oldDepartment, Department updatedDepartment) {
        if (!oldDepartment.getName().equals(updatedDepartment.getName()))
            oldDepartment.setName(updatedDepartment.getName());

        return oldDepartment;
    }
}
