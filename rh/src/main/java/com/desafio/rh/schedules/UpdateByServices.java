package com.desafio.rh.schedules;


import com.desafio.rh.domain.DTO.EmployerDTO;
import com.desafio.rh.domain.Department;
import com.desafio.rh.domain.Employer;
import com.desafio.rh.repositories.DepartmentRepository;
import com.desafio.rh.repositories.EmployerRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@Component
@EnableScheduling
@Service
public class UpdateByServices {

    private final String TIMEZONE = "America/Fortaleza";

    private final String CRON_TIME = "0 0 1 * * *";

    private final String URL = "https://5e61af346f5c7900149bc5b3.mockapi.io/desafio03/employer";

    private final EmployerRepository employerRepository;

    private final DepartmentRepository departmentRepository;

    public UpdateByServices(DepartmentRepository departmentRepository, EmployerRepository employerRepository) {
        this.departmentRepository = departmentRepository;
        this.employerRepository = employerRepository;
    }

    @Scheduled(cron = CRON_TIME, zone = TIMEZONE)
    public void updateDataBaseByService() {
        RestTemplate restTemplate = new RestTemplate();

        EmployerDTO[] forObject = restTemplate.getForObject(URL, EmployerDTO[].class);

        Arrays.stream(forObject).forEach(employerDTO -> {

            Department department = departmentRepository
                    .findDistinctFirstByName(employerDTO.getDepartmentName())
                    .orElse(new Department(null, employerDTO.getDepartmentName()));

            Employer employer = employerRepository
                    .findById(employerDTO.getId())
                    .orElse(new Employer(null, employerDTO.getFirstName(), employerDTO.getLastName(), employerDTO.getCareer(), Collections.singletonList(department)));

            departmentRepository.save(department);
            employerRepository.save(employer);
        });
    }
}
