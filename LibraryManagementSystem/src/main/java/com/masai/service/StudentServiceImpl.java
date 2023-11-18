package com.masai.service;

import java.util.List;

import com.masai.entity.Book;
import com.masai.entity.Feedback;
import com.masai.entity.Rental;
import com.masai.entity.Student;

public class StudentServiceImpl implements StudentService {

	@Override
	public Student registerStudent(String studentName, String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> viewAvailableBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> searchBooksByCriteria(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rental rentBook(Student student, Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback provideFeedback(Student student, Book book, String comment, int rating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean returnBook(Rental rental) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean returnBook(Student student, Book book) {
		// TODO Auto-generated method stub
		return false;
	}

}
