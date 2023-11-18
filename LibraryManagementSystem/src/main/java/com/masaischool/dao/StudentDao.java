package com.masai.dao;

import java.util.List;
import java.util.Scanner;

import com.masai.entity.Book;
import com.masai.entity.Feedback;
import com.masai.entity.Rental;
import com.masai.entity.Student;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface StudentDao {
	Student save(Student student) throws SomethingWentWrongException;

	List<Book> findAvailableBooks() throws SomethingWentWrongException, NoRecordFoundException;

	List<Book> searchBooksByGenre(String genre) throws SomethingWentWrongException, NoRecordFoundException;

	void saveRental(Rental rental) throws SomethingWentWrongException, NoRecordFoundException;

	void updateRental(Rental rental) throws SomethingWentWrongException, NoRecordFoundException;

	void saveFeedback(Feedback feedback) throws SomethingWentWrongException, NoRecordFoundException;

	List<Book> searchBooksByTitle(String title) throws SomethingWentWrongException, NoRecordFoundException;

	void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException;

	Book findBookById(int id) throws SomethingWentWrongException, NoRecordFoundException;

	void changePassword(String stdPassword) throws SomethingWentWrongException, NoRecordFoundException;

	void updateBalance(long stdBalance) throws SomethingWentWrongException, NoRecordFoundException;

	void updateName(String stdName) throws SomethingWentWrongException, NoRecordFoundException;
}
