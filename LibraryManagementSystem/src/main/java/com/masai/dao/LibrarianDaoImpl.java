package com.masai.dao;

import java.util.ArrayList;
import java.util.List;
import com.masai.entity.Book;
import com.masai.entity.Feedback;
import com.masai.entity.Rental;
import com.masai.entity.Student;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.utility.EMUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class LibrarianDaoImpl implements LibrarianDao {

	@Override
	public List<Student> findByUsername(String username) throws SomethingWentWrongException, NoRecordFoundException {

		EntityManager em = null;
		List<Student> stdList = new ArrayList<Student>();
		try {

			em = EMUtils.getEntityManager();
			Query query = em.createQuery("SELECT s From Student s where s.username LIKE :username");
			query.setParameter("username", "%" + username + "%");
			stdList = query.getResultList();
			if (stdList.isEmpty()) {
				throw new NoRecordFoundException("No Student Found");
			}
			EntityTransaction et = em.getTransaction();
		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}

		return stdList;
	}

	@Override
	public List<Student> findAllStudent() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub

		EntityManager em = null;
		List<Student> stdList = new ArrayList<Student>();
		try {

			em = EMUtils.getEntityManager();
			Query query = em.createQuery("SELECT s From Student s");
			stdList = query.getResultList();

			if (stdList.isEmpty()) {
				throw new NoRecordFoundException("No Student Found");
			}
			EntityTransaction et = em.getTransaction();
			return stdList;
		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}

	}

	@Override
	public Book saveBook(Book book) throws SomethingWentWrongException, NoRecordFoundException {

		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EMUtils.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(book);
			et.commit();
		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");

		} finally {
			em.close();
		}
		return book;
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateBook(Book book) throws SomethingWentWrongException, NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EMUtils.getEntityManager();
			Book bookdb = em.find(Book.class, book.getBookId());
			if (bookdb == null) {
				System.out.println("Please enter Valid Book Id");
			} else {
				et = em.getTransaction();
				et.begin();

				bookdb.setAuthor(book.getAuthor());
				bookdb.setGenre(book.getGenre());
				bookdb.setTitle(book.getTitle());

				bookdb.setAvailability(book.isAvailability());

				et.commit();
				System.out.println("Book Updated Successfully");
			}

		} catch (IllegalArgumentException ex) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to process request, try again later");

		} finally {
			em.close();
		}

		// TODO Auto-generated method stub

		return true;
	}

	@Override
	public boolean deleteStudent(int id) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		boolean flag = true;
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			Student studentDb = em.find(Student.class, id);
			if (studentDb == null) {
				flag = false;
			} else {
				EntityTransaction et = em.getTransaction();
				et.begin();
				studentDb.setDeleted(true);
				et.commit();

			}
		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}
		return flag;
	}

	@Override
	public boolean deleteBook(int id) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		boolean flag = true;
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			Book bookD = em.find(Book.class, id);
			if (bookD == null) {
				flag = false;
			} else {
				EntityTransaction et = em.getTransaction();
				et.begin();
				bookD.setAvailability(false);
				et.commit();

			}
		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}
		return flag;
	}

	@Override
	public List<Rental> findStudentRentals() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		List<Rental> rentList = new ArrayList<Rental>();
		try {

			em = EMUtils.getEntityManager();
			Query query = em.createQuery("SELECT r From Rental r", Rental.class);
			rentList = query.getResultList();
			if (rentList.isEmpty()) {
				throw new NoRecordFoundException("No Student rentals Found");
			}

		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}

		return rentList;

	}

	@Override
	public List<Feedback> findBookFeedbacks() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		List<Feedback> feedList = new ArrayList<Feedback>();
		try {

			em = EMUtils.getEntityManager();
			Query query = em.createQuery("SELECT f From Feedback f");
			feedList = query.getResultList();
//			EntityTransaction et = em.getTransaction();
			if (feedList.isEmpty()) {
				throw new NoRecordFoundException("No feedback Found");
			}

		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");

		} finally {
			em.close();
		}

		return feedList;
	}

	@Override
	public List<Book> viewBookAvailable() throws SomethingWentWrongException, NoRecordFoundException {

		EntityManager em = null;
		List<Book> bookList = new ArrayList<Book>();
		try {

			em = EMUtils.getEntityManager();
			Query query = em.createQuery("SELECT b From Book b where b.availability=true");
			bookList = query.getResultList();
			if (bookList.isEmpty()) {
				throw new NoRecordFoundException("No Book Found");
			}

		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}

		return bookList;
	}

}
