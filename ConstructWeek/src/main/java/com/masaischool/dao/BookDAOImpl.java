package com.masaischool.dao;

import java.math.BigDecimal;

import com.masaischool.entity.Book;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;
import com.masaischool.utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class BookDAOImpl implements BookDAO {
	@Override
	public void addBook(Book book) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			em = EMUtils.getEntityManager();
			et = em.getTransaction();
			et.begin();
	
			em.persist(book);	
			et.commit();
		}catch(PersistenceException ex) {
			et.rollback();
		
			throw new SomethingWentWrongException("Unable to add book, try again later");
		}finally {
			em.close();
		
		}
	}

	@Override
	public Book getBookById(int id) throws SomethingWentWrongException, NoRecordFoundException {
		Book book = null;

		try(EntityManager em = EMUtils.getEntityManager()){
			book = em.find(Book.class, id);
			
			if(book == null) {
				throw new NoRecordFoundException("No book available for given id");
			}
		}catch(IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to fetch book details, please try again later");
		}

		return book;
	}

	@Override
	public void updateBookPrice(int id, BigDecimal price) throws SomethingWentWrongException, NoRecordFoundException {
		Book book = getBookById(id);

	
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			em = EMUtils.getEntityManager();
			et = em.getTransaction();
			et.begin();
			book = em.merge(book);

			book.setPrice(price);


			et.commit();
		}catch(PersistenceException ex) {
			et.rollback();

			throw new SomethingWentWrongException("Unable to add book, try again later");
		}finally {
			em.close();

		}
	}

	@Override
	public void deleteBookById(int id) throws SomethingWentWrongException, NoRecordFoundException {
		Book book = getBookById(id);

		
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EMUtils.getEntityManager();
			et = em.getTransaction();
			et.begin();
			

			book = em.merge(book);	
			em.remove(book);	

			et.commit();
		}catch(PersistenceException ex) {
			et.rollback();
			ex.printStackTrace();
			throw new SomethingWentWrongException("Unable to delete book, try again later");
		}finally {
			em.close();
		}
	}
	


}
