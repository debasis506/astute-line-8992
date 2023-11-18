package com.masai.ui;

import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import com.masai.dao.LibrarianDao;
import com.masai.dao.LibrarianDaoImpl;
import com.masai.entity.Book;
import com.masai.entity.Feedback;
import com.masai.entity.Rental;
import com.masai.entity.Student;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class LibrarianUi {

	private static void addBook(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		System.out.println("Enter Book Title");
		String title = sc.next();
		System.out.println("Enter Book Author");
		String author = sc.next();
		System.out.println("Enter Genre of Book");
		String genre = sc.next();

		Book book = new Book(title, author, genre, true, null);
		LibrarianDao ld = new LibrarianDaoImpl();
		Book t = ld.saveBook(book);
		ld.saveBook(book);
		if (t == null) {
			System.out.println("no book added");
		} else {
			System.out.println("book added successfull");
		}
	}

	private static void updateBook(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		System.out.println("Enter Id of Book");
		int id = sc.nextInt();
		System.out.println("Enter Book Title");
		String title = sc.nextLine();
		sc.next();
		System.out.println("Enter Book Author Name");
		String author = sc.nextLine();
		sc.next();
		System.out.println("Enter Genre of Book");
		String genre = sc.nextLine();
		sc.next();
		System.out.println("Enter y if Available else Enter Anything");
		String av = sc.next();
		sc.next();
		boolean available = false;
		if (av.equals("y")) {
			available = true;
		}

		Book book = new Book(title, author, genre, available, null);

		book.setBookId(id);
		LibrarianDao ld = new LibrarianDaoImpl();
		boolean t = ld.updateBook(book);

	}

	private static void deleteBook(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub

		System.out.println("Enter Id of Book you want to be deleted");
		int id = sc.nextInt();
		System.out.println("Are You Sure You want to Delete Your Account");
		System.out.println("Enter y for confirmation enter n if not want to delete");
		String cnf = sc.next();
		if (cnf.equals("y")) {
			LibrarianDao ld = new LibrarianDaoImpl();
			if (ld.deleteBook(id)) {
				System.out.println("Deleted Successfully");
				LibrarianUi.main(null);
			} else {
				System.out.println("Book Not Found");
			}
		} else if (cnf.equals("n")) {
			System.out.println("Thank you so much");
		} else {
			System.out.println("Invalid Selection");
			deleteBook(sc);
		}
	}

	private static void viewList() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub

		LibrarianDao ld = new LibrarianDaoImpl();
		List<Book> bookList = ld.viewBookAvailable();
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

	private static void findRentals() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		LibrarianDao ld = new LibrarianDaoImpl();

		List<Rental> rentL = ld.findStudentRentals();
		CommandLineTable st = new CommandLineTable();
		st.setShowVerticalLines(true);
		System.out.println("\n");
		st.setHeaders("Id", "Student Name", "Book Title", "Fine", "Rental Date", "Return Date");

		for (Rental f : rentL) {
			st.addRow("" + f.getRentalId(), f.getStudent().getStudentName(), f.getBook().getTitle(), "" + f.getFine(),
					"" + f.getRentalDate(), "" + f.getReturnDate());
		}
		st.print();
	}

	private static void findfeedback() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		LibrarianDao ld = new LibrarianDaoImpl();
		List<Feedback> feedList = ld.findBookFeedbacks();
		CommandLineTable st = new CommandLineTable();
		st.setShowVerticalLines(true);
		System.out.println("\n");
		st.setHeaders("Feedback Id", "Student Name", "Book Title", "Feedback", "Rating");

		for (Feedback f : feedList) {
			st.addRow(f.getFeedbackId() + "", f.getStudent().getStudentName(), f.getBook().getTitle(), f.getComment(),
					f.getRating() + "");
		}
		st.print();

	}

	private static void findStudentbyUsername(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		System.out.println("Enter Username");
		String username = sc.next();

		LibrarianDao ld = new LibrarianDaoImpl();
		List<Student> stdList = ld.findByUsername(username);
		CommandLineTable st = new CommandLineTable();
		st.setHeaders("Id", "Student Name", "Username", "Feedback", "Fine");
		st.setShowVerticalLines(true);
		System.out.println("\n");

		for (Student s : stdList) {

			String str = "";
			String str1 = "";
			StringJoiner joiner = new StringJoiner(", ");
			StringJoiner joiner1 = new StringJoiner(", ");
			s.getRentals().forEach(a -> joiner1.add(a.getFine() + ""));
			s.getFeedbacks().forEach(a -> joiner.add(a.getComment()));

			String feedbacksString = joiner.toString();
			str += feedbacksString + " ";
			String rentalString = joiner1.toString();
			str1 += rentalString + " ";

			st.addRow(s.getStudentId() + "", s.getStudentName(), s.getUsername(), str, str1);
			System.out.println(s.toString());
		}
		st.print();
	}

	private static void findAllStudent() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		LibrarianDao ld = new LibrarianDaoImpl();
		List<Student> stdList = ld.findAllStudent();
		CommandLineTable st = new CommandLineTable();
		st.setHeaders("Id", "Student Name", "Username", "Feedback", "Fine");
		st.setShowVerticalLines(true);
		System.out.println("\n");

		for (Student s : stdList) {

			String str = "";
			String str1 = "";
			StringJoiner joiner = new StringJoiner(", ");
			StringJoiner joiner1 = new StringJoiner(", ");
			s.getRentals().forEach(a -> joiner1.add(a.getFine() + ""));
			s.getFeedbacks().forEach(a -> joiner.add(a.getComment()));

			String feedbacksString = joiner.toString();
			str += feedbacksString + " ";
			String rentalString = joiner1.toString();
			str1 += rentalString + " ";

			st.addRow(s.getStudentId() + "", s.getStudentName(), s.getUsername(), str, str1);
			System.out.println(s.toString());
		}
		st.print();

	}

	private static void deleteStudent(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		System.out.println("Enter Id of Student you want to be deleted");
		int id = sc.nextInt();
		LibrarianDao ld = new LibrarianDaoImpl();
		if (ld.deleteStudent(id)) {
			System.out.println("Deleted Successfully");
		} else {
			System.out.println("Student Not Found");
		}

	}

	public static void main(String[] args) throws SomethingWentWrongException, NoRecordFoundException {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Your Username");
		String username = sc.next();
		if (username.equals("admin")) {
			System.out.println("Enter Your Password");
			String password = sc.next();
			if (password.equals("admin")) {
				System.out.println("Welcome Admin");
				String choice = "0";
				do {
					System.out.println("1. Add Book");
					System.out.println("2. Update Book");
					System.out.println("3. Delete Book");
					System.out.println("4. View List of Book");
					System.out.println("5. Show all Rentals");
					System.out.println("6. Show all Feedbacks");
					System.out.println("7. Show Student By Username");
					System.out.println("8. Show All Student");
					System.out.println("9. Delete Student");
					System.out.println("10. Back");
					System.out.println("0. Exit");
					System.out.print("Enter Selection ");
					choice = sc.next();
					switch (choice) {
					case "1":
						addBook(sc);
						break;
					case "2":
						updateBook(sc);
						break;
					case "3":
						deleteBook(sc);
						break;
					case "4":
						viewList();
						break;
					case "5":
						findRentals();
						break;
					case "6":
						findfeedback();
						break;
					case "7":
						findStudentbyUsername(sc);
						break;
					case "8":
						findAllStudent();
						break;
					case "9":
						deleteStudent(sc);
						break;
					case "10":
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

			} else {
				System.out.println("Invalid Password");
				main(null);
			}
		} else {
			System.out.println("Invalid Username");
			main(null);
		}

	}

}
