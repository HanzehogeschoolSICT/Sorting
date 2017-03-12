package jlsa.sorting.util;

import java.util.Random;
import java.util.ArrayList;

/*
 * Thanks to @femkeh this static random data generator exists!
 */
public class RandomDataGenerator {
	
	public static ArrayList<Integer> randomData(int size) {
		Random rand = new Random();
		ArrayList<Integer> data = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			int nextInt = rand.nextInt(size) + 1;
			data.add(nextInt);
		}
		
		return data;
	}
	
}
