package com.masaischool.dao;

import java.math.BigDecimal;

import com.masaischool.entity.Book;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public interface BookDAO {
	public void addBook(Book book) throws SomethingWentWrongException;
	public Book getBookById(int id) throws SomethingWentWrongException, NoRecordFoundException;
	public void updateBookPrice(int id, BigDecimal price) throws SomethingWentWrongException, NoRecordFoundException;
	public void deleteBookById(int id) throws SomethingWentWrongException, NoRecordFoundException;
}
