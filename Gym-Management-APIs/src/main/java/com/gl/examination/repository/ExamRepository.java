package com.gl.examination.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.examination.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {}
