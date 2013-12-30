package org.coder36.pdf.service;

import org.coder36.pdf.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long > {
}
