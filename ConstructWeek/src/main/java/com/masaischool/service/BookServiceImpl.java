package com.masaischool.service;

import java.math.BigDecimal;

import com.masaischool.dao.BookDAO;
import com.masaischool.dao.BookDAOImpl;
import com.masaischool.entity.Book;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public class BookServiceImpl implements BookService {
	@Override
	public void addBook(Book book) throws SomethingWentWrongException {
		BookDAO bookDAO = new BookDAOImpl();
		bookDAO.addBook(book);
	}

	@Override
	public Book getBookById(int id) throws SomethingWentWrongException, NoRecordFoundException {
		BookDAO bookDAO = new BookDAOImpl();
		return bookDAO.getBookById(id);
		
	}

	@Override
	public void updateBookPrice(int id, BigDecimal price) throws SomethingWentWrongException, NoRecordFoundException {
		BookDAO bookDAO = new BookDAOImpl();
		bookDAO.updateBookPrice(id, price);

	}

	@Override
	public void deleteBookById(int id) throws SomethingWentWrongException, NoRecordFoundException {
		BookDAO bookDAO = new BookDAOImpl();
		bookDAO.deleteBookById(id);
	}
}
