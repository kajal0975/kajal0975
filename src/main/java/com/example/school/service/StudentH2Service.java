package com.example.school.service;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class StudentH2Service implements StudentRepository {

	@Autowired
	private JdbcTemplate db;

	@Override
	public ArrayList<Student> getStudents() {
		List<Student> studenData = db.query("select * student", new StudentRowMapper());
		ArrayList<Student> students = new ArrayList<>(studenData);
		return students;
	}

	@Override
	public Student gStudentById(int sudentId) {
		try {
			Student student = db.queryForObject("select * from student where studentId =?", new StudentRowMapper(),
					sudentId);
			return student;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Student addStudent(Student student) {
		db.update("insert into student(studentName, Gender, Standard) values (?,?,?)", student.getStudentName(),
				student.getGender(), student.getStandard());
		Student savedStudent = db.queryForObject("select * from studentName = ? and Gender = ? and Standard = ?",
				new StudentRowMapper(),
				student.getStudentName(), student.getGender(), student.getStandard());
		return savedStudent;
	}

	@Override
	public String addMultipleStudents(ArrayList<Student> studentsList) {

		for (Student eachStudent : studentsList) {

			db.update("insert into student(studentName,gerder,standard) values (?,?,?)", eachStudent.getStudentName(),
					eachStudent.getGender(), eachStudent.getStudentId());
		}
		String reponseMessage = String.format("Successfull added %d students", studentsList.size());
		return reponseMessage;
	}

	@Override
	public Student updatStudent(int studentId, Student student) {
		if (student.getStudentName() != null) {
			db.update("update student set studentName = ? where studentId  =?", student.getGender(), studentId);
		}
		if (student.getGender() != null) {
			db.update("update student set Gender = ? where studentId =?", student.getGender(), studentId);
		}
		if (student.getStandard() != 0) {
			db.update("update student set Standard = ? where studentId = ?", student.getStandard(), studentId);
		}
		return gStudentById(studentId);
	}

	@Override
	public void deleteStudent(int studentId) {
		db.update("delete from student where studentId ?", studentId);
	}
}
