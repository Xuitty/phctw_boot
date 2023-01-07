package spring.tools;

import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

public class IdVerify {

	public boolean idCardVerification(String idNumber) {
		boolean r = false;
		Character[] first_check = new Character[26];
		Character[] number_check = new Character[10];
		int[] city = { 10, 11, 12, 13, 14, 15, 16, 17, 34, 18, 19, 20, 21, 22, 35, 23, 24, 25, 26, 27, 28, 29, 32, 30,
				31, 33 };
		for (int i = 0; i < 26; i++) {
			first_check[i] = (char) (65 + i);
		}
		for (int i = 0; i < 10; i++) {
			number_check[i] = (char) (48 + i);
//			System.out.println(number_check[i]);
		}
		
		idNumber = idNumber.toUpperCase();
//		System.out.println(idNumber);
		Character first = idNumber.charAt(0);
//		System.out.println(first);
		if (idNumber.length() != 10 || Arrays.binarySearch(first_check, first) < 0
				|| (idNumber.charAt(1) != '1' && idNumber.charAt(1) != '2')) {
			return r;
		}
		String city_number = String.valueOf(city[Arrays.binarySearch(first_check, first)]);
		for (int i = 1; i < 10; i++) {
//			System.out.println(Arrays.binarySearch(number_check, idNumber.charAt(i)));
			if (Arrays.binarySearch(number_check, idNumber.charAt(i)) < 0) {
				return r;
			}
		}
		int a = Integer.parseInt(String.valueOf((city_number.charAt(0))));
		int b = Integer.parseInt(String.valueOf((city_number.charAt(1))));
//		System.out.println(a);
//		System.out.println(b);
		int result_converted = a * 1 + b * 9 + (Integer.parseInt(String.valueOf((idNumber.charAt(1))))) * 8
				+ (Integer.parseInt(String.valueOf((idNumber.charAt(2))))) * 7
				+ (Integer.parseInt(String.valueOf((idNumber.charAt(3))))) * 6
				+ (Integer.parseInt(String.valueOf((idNumber.charAt(4))))) * 5
				+ (Integer.parseInt(String.valueOf((idNumber.charAt(5))))) * 4
				+ (Integer.parseInt(String.valueOf((idNumber.charAt(6))))) * 3
				+ (Integer.parseInt(String.valueOf((idNumber.charAt(7))))) * 2
				+ (Integer.parseInt(String.valueOf((idNumber.charAt(8))))) * 1
				+ (Integer.parseInt(String.valueOf((idNumber.charAt(9)))));
		if (result_converted % 10 == 0) {
			r = true;
			return r;
		} else {
			return r;
		}
	}
}
