package lat.hackademy.micro.utils;

import java.util.Random;

public class Utils {

    //Generar una clase y ponerla si se va a usar en diferentes clases
	public static String GeneratingRandomString(int targetStringLength) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
	
	public static Long GeneratingRandomLong() {
		Random random = new Random();

		return random.nextLong();
	}
}
