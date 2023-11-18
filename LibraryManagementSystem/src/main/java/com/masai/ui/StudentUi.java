package com.masai.ui;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import com.masai.dao.LibrarianDao;
import com.masai.dao.LibrarianDaoImpl;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;
import com.masai.entity.Book;
import com.masai.entity.Feedback;
import com.masai.entity.Rental;
import com.masai.entity.SessionStd;
import com.masai.entity.Student;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class StudentUi {

	private static void availableBook() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		StudentDao sd = new StudentDaoImpl();

		List<Book> bookList = sd.findAvailableBooks();
		CommandLineTable st = new CommandLineTable();
		st.setShowVerticalLines(true);
		System.out.println("\n");
		st.setHeaders("Id", "Title", "Author", "Genre", "Feedbacks");

		for (Book b : bookList) {
			String str = "";
			StringJoiner joiner = new StringJoiner(", ");
			b.getFeedbacks().forEach(a -> joiner.add(a.getComment()));

			String feedbacksString = joiner.toString();
			str += feedbacksString + " ";
			st.addRow("" + b.getBookId(), b.getTitle(), b.getAuthor(), b.getGenre(), str);

		}
		st.print();
		System.out.println();

	}

	private static void searchByGenre(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao sd = new StudentDaoImpl();
		System.out.println("Enter Genre you waant to search");
		String genre = sc.next();
		List<Book> bookList = sd.searchBooksByGenre(genre);
		CommandLineTable st = new CommandLineTable();
		st.setShowVerticalLines(true);
		System.out.println("\n");
		st.setHeaders("Id", "Title", "Author", "Genre", "Feedbacks");
		System.out.println();
		if (bookList.size() == 0) {
			System.out.println("No Book Available");
		} else {
			for (Book b : bookList) {
				String str = "";
				StringJoiner joiner = new StringJoiner(", ");
				b.getFeedbacks().forEach(a -> joiner.add(a.getComment()));

				String feedbacksString = joiner.toString();
				str += feedbacksString + " ";
				st.addRow("" + b.getBookId(), b.getTitle(), b.getAuthor(), b.getGenre(), str);

			}
		}
		st.print();

	}

	private static void searchByTitle(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao sd = new StudentDaoImpl();
		System.out.println("Enter Title of book You want to Search");
		String title = sc.next();
		List<Book> bookList = sd.searchBooksByTitle(title);

		CommandLineTable st = new CommandLineTable();
		st.setShowVerticalLines(true);
		System.out.println("\n");
		st.setHeaders("Id", "Title", "Author", "Genre", "Feedbacks");

		System.out.println();
		if (bookList.size() == 0) {
			System.out.println("No Book Available");
		} else {
			for (Book b : bookList) {
				String str = "";
				StringJoiner joiner = new StringJoiner(", ");
				b.getFeedbacks().forEach(a -> joiner.add(a.getComment()));

				String feedbacksString = joiner.toString();
				str += feedbacksString + " ";
				st.addRow("" + b.getBookId(), b.getTitle(), b.getAuthor(), b.getGenre(), str);

			}
		}
		st.print();

	}

	private static void registerStudent(Scanner sc) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		System.out.println("Please Add Student");
		StudentDao ls = new StudentDaoImpl();
		System.out.println("Enter Student Name");
		String name = sc.next();
		System.out.println("Enter Username");
		String username = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		Student std = new Student(name, username, password, new ArrayList<Rental>());
		Student t = ls.save(std);

		if (t == null) {
			System.out.println("no Student added");
		} else {
			System.out.println("Student added successfull");
		}

	}

	private static void viewProfile() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao ls = new StudentDaoImpl();
		CommandLineTable st = new CommandLineTable();
		st.setShowVerticalLines(true);
		CommandLineTable st1 = new CommandLineTable();
		st.setShowVerticalLines(true);
		CommandLineTable st2 = new CommandLineTable();
		st.setShowVerticalLines(true);
		System.out.println("\n");
		st.setHeaders("Id", "Name", "Username", "Password");
		st.addRow("" + SessionStd.getCurrentStd().getStudentId(), SessionStd.getCurrentStd().getStudentName(),
				SessionStd.getCurrentStd().getPassword());

		System.out.println("\n");
		System.out.println("Your Rentals:-");
		st1.setHeaders("Id", "Book", "Rental Date", "Return Date");
		SessionStd.getCurrentStd().getRentals().forEach(a -> st1.addRow("" + a.getRentalId(), a.getBook().getTitle(),
				"" + a.getRentalDate(), "" + a.getReturnDate()));
		st1.print();
		System.out.println("Your feedbacks:-");
		System.out.println("\n");
		st2.setHeaders("Id", "Book", "Comment", "Rating");
		SessionStd.getCurrentStd().getFeedbacks().forEach(
				a -> st2.addRow("" + a.getFeedbackId(), a.getBook().getTitle(), a.getComment(), "" + a.getRating()));
		st2.print();
		System.out.println("\n");
		System.out.println("Your Wallet Balance is : " + SessionStd.getCurrentStd().getWallet());

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome");
		String choice = "0";
		do {
			System.out.println("Profile Setting");
			System.out.println("Enter");
			System.out.println("1.Change Profile name  2.Add Wallet Balance  3.Change Password 0.Main Menu");

			choice = sc.next();
			switch (choice) {
			case "1":
				System.out.println("Enter new Profile name");
				String stdName = sc.nextLine();
				ls.updateName(stdName);
				StudentUi.main(null);
				break;
			case "2":
				System.out.println("Enter Adding Wallet");
				long stdBalance = sc.nextLong();
				ls.updateBalance(stdBalance);
				StudentUi.main(null);
				break;
			case "3":
				System.out.println("Enter new Password");
				String stdPassword = sc.nextLine();
				ls.changePassword(stdPassword);
				StudentUi.main(null);
				break;
			case "4":
				System.out.println("Thanks for using the services");
				StudentUi.main(null);
				break;

			case "0":
				System.out.println("Thanks for using the services");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != "0");
		sc.close();

	}

	private static void loginStudent(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {

		System.out.println("Enter Your Username");
		String username = sc.next();
		System.out.println("Enter Your Password");
		String password = sc.next();

		StudentDao ls = new StudentDaoImpl();
		ls.login(username, password);

		System.out.println("Welcome " + SessionStd.getCurrentStd().getStudentName());
		String choice = "0";
		do {
			System.out.println("1. List of Book Available");
			System.out.println("2. Search Book by Genre");
			System.out.println("3. Search Book by Title");
			System.out.println("4. Rent a Book");
			System.out.println("5. Return Book");
			System.out.println("6. Give Feedback for Book");
			System.out.println("7. Delete Account");
			System.out.println("8. Profile Setting");
			System.out.println("9. Back");
			System.out.println("0. Exit");
			System.out.print("Enter Selection ");
			choice = sc.next();
			switch (choice) {
			case "1":
				availableBook();
				break;
			case "2":
				searchByGenre(sc);
				break;
			case "3":
				searchByTitle(sc);
				break;
			case "4":
				bookRent(sc);
				break;
			case "5":
				returnBook(sc);
				break;
			case "6":
				giveFeedback(sc);
				break;
			case "7":
				deleteStudent(sc);
				break;
			case "8":
				viewProfile();
				break;
			case "9":
				MainLab.main(null);
				break;
			case "0":
				System.out.println("Thanks for using the services");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != "0");
		sc.close();

	}

	private static void deleteStudent(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		System.out.println("Are You Sure You want to Delete Your Account");
		System.out.println("Enter y for confirmation enter n if not want to delete");
		String cnf = sc.next();
		if (cnf.equals("y")) {
			int id = SessionStd.getCurrentStd().getStudentId();
			LibrarianDao ld = new LibrarianDaoImpl();
			if (ld.deleteStudent(id)) {
				System.out.println("Deleted Successfully");
				StudentUi.main(null);
			} else {
				System.out.println("Student Not Found");
			}
		} else if (cnf.equals("n")) {
			System.out.println("Thank you so much");
		} else {
			System.out.println("Invalid Selection");
			deleteStudent(sc);
		}

	}

	private static void bookRent(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao ls = new StudentDaoImpl();
		ls.findAvailableBooks().forEach(a -> System.out.println(a));
		System.out.println("Enter Book Id You want to Rent");
		int id = sc.nextInt();
		StudentDao st = new StudentDaoImpl();
		Book book = st.findBookById(id);

		Rental rental = new Rental(SessionStd.getCurrentStd(), book, Date.valueOf(LocalDate.now()), null);
		st.saveRental(rental);
	}

	private static void returnBook(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao ls = new StudentDaoImpl();
		System.out.println("Enter Rental Id You want to Return");
		int id = sc.nextInt();
		System.out.println("Enter Book Id You want to Return");
		int idb = sc.nextInt();

		StudentDao st = new StudentDaoImpl();
		Book book = st.findBookById(idb);
		Rental rental = new Rental(SessionStd.getCurrentStd(), book, Date.valueOf(LocalDate.now()),
				Date.valueOf("2023-07-23"));

		rental.setRentalId(id);
		st.updateRental(rental);

	}

	private static void giveFeedback(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		StudentDao ls = new StudentDaoImpl();
		System.out.println("Enter Book Id");
		int id = sc.nextInt();
		StudentDao st = new StudentDaoImpl();
		Book book = st.findBookById(id);
		System.out.println("Give Feedback");
		String msg = sc.nextLine();
		sc.next();
		System.out.println("Enter Book Rating");
		int rating = sc.nextInt();
		Feedback feedback = new Feedback(SessionStd.getCurrentStd(), book, msg, rating);
		st.saveFeedback(feedback);
	}

	public static void main(String[] args) throws SomethingWentWrongException, NoRecordFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome");
		String choice = "0";
		do {
			System.out.println("1. Sign In");
			System.out.println("2. Register new Student");
			System.out.println("3. Back to Main Menu");
			System.out.println("0. Exit");
			System.out.print("Enter Selection ");
			choice = sc.next();
			switch (choice) {
			case "1":
				loginStudent(sc);
				break;
			case "2":
				registerStudent(sc);
				break;
			case "3":
				MainLab.main(null);
				break;
			case "0":
				System.out.println("Thanks for using the services");
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != "0");
		sc.close();

	}

}
