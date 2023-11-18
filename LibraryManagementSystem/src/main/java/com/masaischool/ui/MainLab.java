package com.masai.ui;

import java.util.Scanner;

import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class MainLab {

	public static void main(String[] args) throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		String choice = "0";
		do {
			System.out.println("1. Admin Side");
			System.out.println("2. Student Side");
			System.out.println("0. Exit");
			System.out.print("Enter Selection ");
			choice = sc.next();
			switch (choice) {
			case "1":
				LibrarianUi.main(args);
				break;
			case "2":
				StudentUi.main(args);
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

}
