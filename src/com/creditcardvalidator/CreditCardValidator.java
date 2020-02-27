/*
 * Credit Card Validator using Luhn Algorithm - v0.1
 * Author: Alen Sreckovic
 * Date: February 22, 2020
 * JRE System Library: Java SE 13.0.2
 * */

package com.creditcardvalidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreditCardValidator {
	
	
	//Class constructor
	public CreditCardValidator() {

		System.out.println("Credit Card Validator using Luhn Algorithm - v0.1, by Alen Sreckovic");
		System.out.println("--------------------------------------------------------------------");
        
		userInput();

	}
	//Luhn's algorithm for calculating card-numbers of odd length.
	public boolean oddLengthValidation(List<Integer> numbers) {

		int sum = 0;

		for (int i = numbers.size() - 1; i > 0; i--) {
			if (i % 2 == 0) {
				int temp = numbers.get(i);
				temp *= 2;
				if (temp > 9) {
					temp -= 9;
					sum += temp;
				} else {
					sum += temp;
				}
			} else {
				sum += numbers.get(i);
			}
		}


		return sum % 10 == 0;
	}
	
	//Luhn's algorithm for calculating card-numbers of even length.
	public boolean evenLengthValidation(List<Integer> numbers) {

		int sum = 0;

		for (int i = 0; i < numbers.size(); i++) {
			if (i % 2 == 0) {
				int temp = numbers.get(i);
				temp *= 2;
				if (temp > 9) {
					temp -= 9;
					sum += temp;
				} else {
					sum += temp;
				}
			} else {
				sum += numbers.get(i);
			}
		}

		return sum % 10 == 0;

	}
	/*
	 * Collects user-input. An InputMismatchException try-catch block is 
	 * unnecessary here as the Scanner object's delimiter and nextInt()/hasNextInt() functions
	 * handle undesirable inputs such as letters and special characters. The subsequent function,
	 * listLengthChecker(ArrayList<Integer> list), further checks user-input.
	 * */
	public void userInput() {

		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("");
		
		System.out.println("Please input the card-number to be checked then press Enter: ");	
		

		while(scanner.hasNextInt()){	
			list.add(scanner.nextInt());
		}  
	
	
		listLengthChecker(list);
		scanner.close();
		
	}

	/* 
	 * Checks the length of the provided list. Depending on which condition is met,
	 * the list will either be passed to the even-length-version of Luhn's algorithm or the
	 * odd-length-version of Luhn's algorithm. Undesirable inputs are also handled here.
	 * */
	public void listLengthChecker(List<Integer> list) {
		
		if(list.size() < 13 || list.size() > 19) {
			System.out.println("Invalid input. Number must be 16 to 19 integers in length and only"
					+ " of integer type.");
			inputRepeater();
		} else if((list.size() % 2 == 0) && (evenLengthValidation(list) == true)) {
			System.out.println("The provided credit card number is valid!"
					+ "\nCredit card type: " + cardCompany(list));	
			inputRepeater();
		} else if((list.size() % 2 == 1) && (oddLengthValidation(list) == true)) {
			System.out.println("The provided credit card number is valid!"
					+ "\nCredit card type: " + cardCompany(list));
			inputRepeater();
		} else {
			System.out.println("The provided credit card number is not valid.");
			inputRepeater();
		}
	}
	
	//Allows the user to exit the application or to continue to check credit card numbers. 
	public void inputRepeater() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nPress Enter to try again or 'x' to quit: ");
		
		if(scanner.nextLine().equalsIgnoreCase("x")) {
			exitText();
			scanner.close();
			System.exit(0);
		} else {
			userInput();
			scanner.close();
		}
	}
	
	//Attempts to match user-provided numbers with known credit card companies.
	public String cardCompany(List<Integer> list) {
		
		if(list.get(0) == 3) {
			return "American Express";
		} else if (list.get(0) == 4) {
			return "Visa";
		} else if (list.get(0) == 5) {
			return "Mastercard";
		} else if (list.get(0) == 6) {
			return "Discover";
		} else {
		
		  return "Unknown";
		}
	}
	
	//Exit message prior to application-termination. 
	public void exitText() {
		System.out.println("\nThank you for using Credit Card Validator!");
	}
}
