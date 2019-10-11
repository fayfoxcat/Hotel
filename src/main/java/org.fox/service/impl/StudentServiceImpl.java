package org.fox.service.impl;

import org.fox.entity.Student;
import org.fox.dao.StudentMapper;
import org.fox.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
	@Autowired
	@Qualifier("studentMapper")
	private StudentMapper studentMapper;
	
	@Override
	public Student queryStudentByNo(int sno) {
		return studentMapper.queryStudentByNo(sno);
	}
}
