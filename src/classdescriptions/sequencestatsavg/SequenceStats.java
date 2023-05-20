package classdescriptions.sequencestatsavg;

import java.util.Arrays;

public class SequenceStats {
	private int count = 0;
	private double sum = 0.0;

	public SequenceStats() {
	}

	public void put(double num) {
		count++;
		sum += num;
	}

	public int getCount() {
		return count;
	}

	public double getAvg() {
		if (count == 0) return 0;
		return sum / count;
	}

	public static void main(String[] args) {
		SequenceStats stats = new SequenceStats();
		int[] sampleArray = new int[]{
				4, 5, 7, 8, 1, -6, 5
		};
		System.out.println(Arrays.toString(sampleArray));
		Arrays.stream(sampleArray).forEach(stats::put);
		System.out.println("Количество элементов в массиве: " + stats.getCount());
		System.out.println("Среднее арифметическое элементов в массиве: " + stats.getAvg());

	}
}
