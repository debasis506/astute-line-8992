package com.masai.service;

import java.util.List;

import com.masai.entity.Book;
import com.masai.entity.Feedback;
import com.masai.entity.Rental;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface LibrarianService {
	List<Rental> viewStudentRentals();

	List<Feedback> viewBookFeedbacks();

	Book addBook(Book book) throws SomethingWentWrongException, NoRecordFoundException;

	boolean removeBook(int id) throws SomethingWentWrongException, NoRecordFoundException;

	boolean updateBookInformation(Book book) throws SomethingWentWrongException, NoRecordFoundException;
}
