package com.gl.examination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gl.examination.model.Exam;
import com.gl.examination.service.ExamService;

import java.util.List;

public class ExamController {
    
    private ExamService examService;


    public Exam createExam(Exam exam) {
      
    }

   
    public List<Exam> getAllExams() {
       
    }

    
    public Exam getExamById(Long id) {

    }
}
