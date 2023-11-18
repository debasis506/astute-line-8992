package com.masai.service;

import java.util.List;

import com.masai.dao.LibrarianDao;
import com.masai.dao.LibrarianDaoImpl;
import com.masai.entity.Book;
import com.masai.entity.Feedback;
import com.masai.entity.Rental;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class LibrarianServiceImpl implements LibrarianService {

	LibrarianDao ld = new LibrarianDaoImpl();

	@Override
	public Book addBook(Book book) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return ld.saveBook(book);
	}

	@Override
	public boolean updateBookInformation(Book book) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return ld.updateBook(book);
	}

	@Override
	public boolean removeBook(int id) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return ld.deleteBook(id);
	}

	@Override
	public List<Rental> viewStudentRentals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> viewBookFeedbacks() {
		// TODO Auto-generated method stub
		return null;
	}

}
