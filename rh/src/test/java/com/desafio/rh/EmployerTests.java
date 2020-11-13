package com.desafio.rh;

import com.desafio.rh.domain.DTO.EmployerDTO;
import com.desafio.rh.domain.Department;
import com.desafio.rh.domain.Employer;
import com.desafio.rh.repositories.EmployerRepository;
import com.desafio.rh.testconfig.MMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class EmployerTests {

    private final Department department = Department.builder()
            .name("test")
            .build();

    private final Employer employer = Employer.builder()
            .firstName("firstName")
            .lastName("lastName")
            .career("career")
            .department(Arrays.asList(department))
            .build();

    private final EmployerDTO employerDTO = EmployerDTO.builder()
            .firstName("firstName")
            .lastName("lastName")
            .career("career")
            .departmentName("test")
            .build();

    @Autowired
    private EmployerRepository employerRepository;


    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        String uri = "/employer";

        String newEmployer = MMapper.mapToJson(employerDTO);
        String newDepartment = MMapper.mapToJson(department);


        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newDepartment))
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newEmployer))
                .andReturn();

    }

    @Test
    public void testWhenEmployerIsSubmittedOnPostSaveEmployer() throws Exception {
        String uri = "/employer";

        String newEmployer = MMapper.mapToJson(employerDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newEmployer))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(201, status);

        String content = mvcResult.getResponse().getContentAsString();
        String contentHeader = mvcResult.getResponse().getHeader("location");

        Assert.assertTrue(content.isEmpty());
        Assert.assertNotNull(contentHeader);
        Assert.assertFalse(contentHeader.isEmpty());
    }


    @Test
    public void testWhenIdIsNotProvided_RetrievalListOfEmployersAndResponseStatus200() throws Exception {
        String uri = "/employer";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();

        List employerList = MMapper.mapFromJson(content, List.class);

        Assert.assertNotNull(employerList);

    }


    @Test
    public void testWhenDeleteEmployerById_RetrievalNotFoundEmployerResponseAndResponseStatus204() throws Exception {
        String uri = "/employer/1";

        String newEmployer = MMapper.mapToJson(employerDTO);

        MvcResult mvcResultAdd = mockMvc.perform(MockMvcRequestBuilders.post("/employer")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newEmployer))
                .andReturn();

        int status = mvcResultAdd.getResponse().getStatus();

        assertEquals(201, status);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        status = mvcResult.getResponse().getStatus();

        assertEquals(204, status);
    }

}
