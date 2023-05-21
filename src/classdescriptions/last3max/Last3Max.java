package classdescriptions.last3max;

import java.util.Arrays;

public class Last3Max {
	private Integer l1 = null;
	private Integer l2 = null;
	private Integer l3 = null;
	public void put(int num){
		if (l1 == null) {
			l1 = num;
			return;
		}
		if (l2 == null) {
			l2 = num;
			return;
		}
		if (l3 == null) {
			l3 = num;
			return;
		}
		l1 = l2;
		l2 = l3;
		l3 = num;
	}

	public int getLast3Max() throws Exception {
		if (l1 == null) throw new Exception("Пока не было передано чисел!");
		if (l2 == null){
			return l1;
		}
		if (l3 == null){
			return Math.max(l1, l2);
		}
		return Math.max(Math.max(l1, l2), l3);
	}

	public static void main(String[] args) throws Exception {
		Last3Max last3Max = new Last3Max();
		int[] sampleArray = new int[]{
				4, 5, 7, 8, 1, -6, 5
		};
		System.out.println(Arrays.toString(sampleArray));
		Arrays.stream(sampleArray).forEach(last3Max::put);
		System.out.println("Максимальное число из последних 3 переданных данному Last3Max: " +
		                   last3Max.getLast3Max());
	}
}
