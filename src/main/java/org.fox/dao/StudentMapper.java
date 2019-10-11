package org.fox.dao;

import org.fox.entity.Student;
import org.springframework.stereotype.Repository;

@Repository("studentMapper")
public interface StudentMapper {
	public Student queryStudentByNo(int sno);
	
	public void addStudent(Student student);
	
}
