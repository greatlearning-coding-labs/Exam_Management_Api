package com.gl.examination;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.examination.controller.ExamController;
import com.gl.examination.model.Exam;
import com.gl.examination.service.ExamService;

import java.util.*;

@WebMvcTest(ExamController.class)
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateExam() throws Exception {
        Exam exam = new Exam();
        exam.setTitle("Chemistry");
        exam.setDescription("Final");
        exam.setDurationInMinutes(60);

        when(examService.createExam(any(Exam.class))).thenReturn(exam);

        mockMvc.perform(post("/exams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Chemistry"));
    }

    @Test
    void testGetAllExams() throws Exception {
        Exam e1 = new Exam(); e1.setTitle("Math");
        Exam e2 = new Exam(); e2.setTitle("History");

        when(examService.getAllExams()).thenReturn(Arrays.asList(e1, e2));

        mockMvc.perform(get("/exams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetExamById() throws Exception {
        Exam exam = new Exam(); exam.setId(1L); exam.setTitle("Biology");
        when(examService.getExamById(1L)).thenReturn(exam);

        mockMvc.perform(get("/exams/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Biology"));
    }
}
