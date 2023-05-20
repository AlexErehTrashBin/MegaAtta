package classdescriptions.sequencestatsminmax;

import java.util.Arrays;

public class SequenceStats {
	private int overallPutCounter = 0;
	private int overallSum = 0;
	private int max = -1;
	private int min = -1;

	public SequenceStats() {
	}

	public void put(int num){
		if (overallPutCounter == 0){
			max = num;
			min = num;
			overallSum = num;
			overallPutCounter++;
			return;
		}
		if (num > max) max = num;
		if (num < min) min = num;
		overallSum += num;
		overallPutCounter++;
	}
	public int getMax() throws Exception{
		if (overallPutCounter == 0){
			throw new Exception("Пока не было передано ни одного числа!");
		}
		return max;
	}

	public int getMin() throws Exception{
		if (overallPutCounter == 0){
			throw new Exception("Пока не было передано ни одного числа!");
		}
		return min;
	}

	public int getSum() {
		return overallSum;
	}

	/**
	 * Пример работы с классом
	 * */
	public static void main(String[] args) throws Exception {
		SequenceStats stats = new SequenceStats();
		int[] sampleArray = new int[]{
				4, 5, 7, 8, 1, -6, 5
		};
		Arrays.stream(sampleArray).forEach(stats::put);
		System.out.println("Сумма всех элементов массива: " + stats.getSum());
		System.out.println("Минимальное значение среди всех элементов массива: " + stats.getMin());
		System.out.println("Максимальное значение среди всех элементов массива: " + stats.getMax());
	}
}
