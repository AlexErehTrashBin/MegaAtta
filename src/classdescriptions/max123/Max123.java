package classdescriptions.max123;


import java.util.Arrays;

public class Max123 {
	private Integer max1 = null;
	private Integer max2 = null;
	private Integer max3 = null;
	public void put(int num){
		if (max1 == null) {
			max1 = num;
			return;
		}
		else if (max2 == null) {
			if (num < max1) {
				max2 = num;
			}
			else if (num > max1){
				max2 = max1;
				max1 = num;
			}
			return;
		}
		else if (max3 == null) {
			if (num == max1 || num == max2) return;
			if (num > max1){
				max3 = max2;
				max2 = max1;
				max1 = num;
			}
			else if (num > max2){
				max3 = max2;
				max1 = num;
			}
			return;
		}
		if (num > max1){
			max3 = max2;
			max2 = max1;
			max1 = num;
		}
		else if (num > max2 && num < max1){
			max3 = max2;
			max2 = num;
		}
		else if (num > max3 && num < max2) {
			max3 = num;
		}

	}

	public int getMax1(){
		return max1 != null ? max1 : -1;
	}

	public int getMax2(){
		return max2 != null ? max2 : -1;
	}

	public int getMax3(){
		return max3 != null ? max3 : -1;
	}

	public static void main(String[] args) {
		Max123 max123 = new Max123();
		int[] sampleArray = new int[]{
				4, 5, 7, 8, 1, 7, 5
		};
		System.out.println(Arrays.toString(sampleArray));
		Arrays.stream(sampleArray).forEach(max123::put);
		System.out.println("Первое по величине: " + max123.getMax1());
		System.out.println("Второе по величине: " + max123.getMax2());
		System.out.println("Третье по величине: " + max123.getMax3());
	}
}
