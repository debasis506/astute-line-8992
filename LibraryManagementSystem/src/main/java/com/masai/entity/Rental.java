package com.masai.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rental")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rental_id")
	private int rentalId;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(name = "rental_date")
	private Date rentalDate;

	@Column(name = "return_date")
	private Date returnDate;

	@Column(name = "fine")
	private long fine;

	public long getFine() {
		return fine;
	}

	public void setFine(long fine) {
		this.fine = fine;
	}

	public Rental() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rental(Student student, Book book, Date rentalDate, Date returnDate) {
		super();
		this.student = student;
		this.book = book;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
	}

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
