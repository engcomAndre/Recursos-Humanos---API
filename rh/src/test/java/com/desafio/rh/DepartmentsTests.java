package com.desafio.rh;

import com.desafio.rh.domain.Department;
import com.desafio.rh.repositories.DepartmentRepository;
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

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class DepartmentsTests {

    private final Department department = Department.builder()
            .name("test")
            .build();


    @Autowired
    private DepartmentRepository employerRepository;


    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void testWhenDepartmentIsSubmittedOnPostSaveDepartment() throws Exception {
        String uri = "/department";

        String newDepartment = MMapper.mapToJson(department);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newDepartment))
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
    public void testWhenIdIsNotProvided_RetrievalListOfDepartmentsAndResponseStatus200() throws Exception {
        String uri = "/department";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();

        List departments = MMapper.mapFromJson(content, List.class);

        Assert.assertNotNull(departments);

    }


    @Test
    public void testWhenDeleteDepartmentById_RetrievalNotFoundDepartmentResponseAndResponseStatus204() throws Exception {
        String uri = "/department/1";

        String newDepartment = MMapper.mapToJson(department);

        MvcResult mvcResultAdd = mockMvc.perform(MockMvcRequestBuilders.post("/department")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(newDepartment))
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
