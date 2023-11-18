package com.masai.dao;

import java.util.List;

import com.masai.entity.Book;
import com.masai.entity.Feedback;
import com.masai.entity.Rental;
import com.masai.entity.Student;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface LibrarianDao {
	    Book saveBook(Book book)throws SomethingWentWrongException,NoRecordFoundException;
	    boolean updateBook(Book book)throws SomethingWentWrongException,NoRecordFoundException;
	    List<Book> viewBookAvailable()throws SomethingWentWrongException,NoRecordFoundException;
	    List<Rental> findStudentRentals()throws SomethingWentWrongException,NoRecordFoundException;
	    List<Feedback> findBookFeedbacks()throws SomethingWentWrongException,NoRecordFoundException;
		boolean deleteBook(int id)throws SomethingWentWrongException,NoRecordFoundException;
		List<Student> findByUsername(String username)throws SomethingWentWrongException,NoRecordFoundException;
		List<Student> findAllStudent()throws SomethingWentWrongException,NoRecordFoundException;
		boolean deleteStudent(int id)throws SomethingWentWrongException,NoRecordFoundException;
}
