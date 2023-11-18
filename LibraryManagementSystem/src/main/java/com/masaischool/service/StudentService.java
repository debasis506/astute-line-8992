package com.masai.service;

import java.util.List;

import com.masai.entity.Book;
import com.masai.entity.Feedback;
import com.masai.entity.Rental;
import com.masai.entity.Student;

public interface StudentService {
	Student registerStudent(String studentName, String username, String password);

	Student login(String username, String password);

	List<Book> viewAvailableBooks();

	List<Book> searchBooksByCriteria(String criteria);

	Rental rentBook(Student student, Book book);

	boolean returnBook(Student student, Book book);

	Feedback provideFeedback(Student student, Book book, String comment, int rating);

	boolean returnBook(Rental rental);
}
