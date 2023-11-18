package com.masai.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.masai.entity.Book;
import com.masai.entity.Feedback;
import com.masai.entity.Rental;
import com.masai.entity.SessionStd;
import com.masai.entity.Student;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class StudentDaoImpl implements StudentDao {

	@Override
	public Student save(Student student) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EMUtils.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(student);
			et.commit();
		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}
		return student;
	}

	@Override
	public List<Book> findAvailableBooks() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		LibrarianDao ld = new LibrarianDaoImpl();
		return ld.viewBookAvailable();
	}

	@Override
	public Book findBookById(int id) throws NoRecordFoundException {
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			Book bookdb = em.find(Book.class, id);
			if (bookdb == null) {
				System.out.println("Please enter Valid Book details");
			} else {
				return bookdb;
			}

		} catch (IllegalArgumentException ex) {
			throw new NoRecordFoundException("No Student Found");
		} finally {
			em.close();
		}
		return null;

	}

	@Override
	public List<Book> searchBooksByGenre(String genre) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub

		EntityManager em = null;
		List<Book> bookList = new ArrayList<Book>();
		try {

			em = EMUtils.getEntityManager();
			Query query = em.createQuery("SELECT b From Book b where b.genre LIKE :genre");
			query.setParameter("genre", "%" + genre + "%");
			bookList = query.getResultList();

			if (bookList.isEmpty()) {
				throw new NoRecordFoundException("No Book Found");
			}
			EntityTransaction et = em.getTransaction();

		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}

		return bookList;
	}

	@Override
	public List<Book> searchBooksByTitle(String title) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub

		EntityManager em = null;
		List<Book> bookList = new ArrayList<Book>();
		try {

			em = EMUtils.getEntityManager();
			Query query = em.createQuery("SELECT b From Book b where b.title LIKE :title AND ");
			query.setParameter("title", "%" + title + "%");
			bookList = query.getResultList();
			EntityTransaction et = em.getTransaction();

		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}

		return bookList;
	}

	@Override
	public void saveRental(Rental rental) throws SomethingWentWrongException, NoRecordFoundException {

		EntityManager em = null;
		EntityTransaction et = null;
		try {
			rental.getBook().setAvailability(true);
			em = EMUtils.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(rental);
			et.commit();
		} catch (PersistenceException ex) {
			System.out.println("Duplicate entry");
			ex.getMessage();
		} finally {
			em.close();
		}

	}

	@Override
	public void updateRental(Rental rental) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EMUtils.getEntityManager();
			Rental rent = em.find(Rental.class, rental.getRentalId());
			if (rent == null) {
				System.out.println("Please enter correct rental id");
			} else {
				et = em.getTransaction();
				et.begin();

				rent.setReturnDate(rental.getReturnDate());
				long differenceInReturn = rent.getReturnDate().getTime() - rent.getRentalDate().getTime();

				System.out.println(TimeUnit.MILLISECONDS.toDays(differenceInReturn));
				if (TimeUnit.MILLISECONDS.toDays(differenceInReturn) > 7) {
					rent.setFine((TimeUnit.MILLISECONDS.toDays(differenceInReturn) / 7) * 5);
					rent.getStudent().setWallet(rent.getStudent().getWallet()
							- ((TimeUnit.MILLISECONDS.toDays(differenceInReturn) / 7) * 5));

				} else {
					rent.setFine(0);

				}
				et.commit();
			}

		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}
	}

	@Override
	public void updateName(String stdName) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		EntityTransaction et = null;
		Student std = SessionStd.getCurrentStd();
		try {
			em = EMUtils.getEntityManager();
			Student stddb = em.find(Student.class, std.getStudentId());

			et = em.getTransaction();
			et.begin();

			stddb.setStudentName(stdName);

			et.commit();

		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}
	}

	@Override
	public void updateBalance(long balance) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		EntityTransaction et = null;
		Student std = SessionStd.getCurrentStd();
		try {
			em = EMUtils.getEntityManager();
			Student stddb = em.find(Student.class, std.getStudentId());

			et = em.getTransaction();
			et.begin();

			stddb.setWallet(balance);

			et.commit();

		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}
	}

	@Override
	public void changePassword(String stdPassword) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		EntityTransaction et = null;
		Student std = SessionStd.getCurrentStd();
		try {
			em = EMUtils.getEntityManager();
			Student stddb = em.find(Student.class, std.getStudentId());

			et = em.getTransaction();
			et.begin();

			stddb.setPassword(stdPassword);

			et.commit();

		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}
	}

	@Override
	public void saveFeedback(Feedback feedback) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EMUtils.getEntityManager();
			et = em.getTransaction();
			feedback.getBook().getFeedbacks().add(feedback);
			feedback.getStudent().getFeedbacks().add(feedback);
			et.begin();
			em.persist(feedback);
			et.commit();
		} catch (PersistenceException ex) {
			System.out.println("Duplicate entry");
			ex.getMessage();
		} finally {
			em.close();
		}
	}

	@Override
	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException {
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();

			Query query = em.createQuery(
					"SELECT c FROM Student c WHERE username = :username AND password = :password AND isDeleted = 0");
			query.setParameter("username", username);
			query.setParameter("password", password);

			List<Student> listStd = (List<Student>) query.getResultList();
			if (listStd == null) {

				System.out.println("The username or password is Incorrect");

			}

			for (Student std : listStd) {
				SessionStd.setCurrentStd(std);
				System.out.println("Log in Successfull");
			}

		} catch (IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		} finally {
			em.close();
		}

	}

}
