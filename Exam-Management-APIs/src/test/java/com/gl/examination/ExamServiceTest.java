package com.gl.examination;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import com.gl.examination.model.Exam;
import com.gl.examination.repository.ExamRepository;
import com.gl.examination.service.ExamService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ExamServiceTest {

    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private ExamService examService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateExam() {
        Exam exam = new Exam();
        exam.setTitle("Math");
        when(examRepository.save(any(Exam.class))).thenReturn(exam);

        Exam result = examService.createExam(exam);
        assertEquals("Math", result.getTitle());
    }

    @Test
    void testGetAllExams() {
        Exam e1 = new Exam(); e1.setTitle("Math");
        Exam e2 = new Exam(); e2.setTitle("Science");

        when(examRepository.findAll()).thenReturn(Arrays.asList(e1, e2));
        List<Exam> exams = examService.getAllExams();

        assertEquals(2, exams.size());
    }

    @Test
    void testGetExamById() {
        Exam exam = new Exam(); exam.setId(1L); exam.setTitle("Physics");
        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));

        Exam result = examService.getExamById(1L);
        assertEquals("Physics", result.getTitle());
    }
    
    @Test
    void testUpdateExamNotFound() {
        Exam updatedData = new Exam("Advanced Java", "Advanced topics");
        when(examRepository.findById(2L)).thenReturn(Optional.empty());

        Exam updated = examService.updateExam(2L, updatedData);
        assertNull(updated);
    }

    @Test
    void testDeleteExam() {
        doNothing().when(examRepository).deleteById(1L);
        examService.deleteExam(1L);
        verify(examRepository, times(1)).deleteById(1L);
    }
}
