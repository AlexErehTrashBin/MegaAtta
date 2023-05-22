package timecomplexity;

import java.util.HashMap;
import java.util.Map;

public class CountNumberEncounters {
	// Цикл по всем элементам массива arr происходит за n операций, где n - количество элементов в массиве.
	// На каждой итерации этого цикла происходит проверка наличия ключа в TreeMap за O(log(n))
	// (свойство красно-чёрных деревьев, на которых построен TreeMap).
	// Далее в той же итерации идёт добавление ключа в HashMap за O(log(n))
	// Временная сложность алгоритма получается O(log(n))*O(n) = O(n*log(n)).
	public static int getEncounters(Integer[] arr, Integer number){
		Map<Integer, Integer> dictionary = new HashMap<>();
		for (Integer element : arr) {
			if (!dictionary.containsKey(element)) {
				dictionary.put(element, 1);
				continue;
			}
			int currentEncountersValue = dictionary.get(element);
			dictionary.put(element, currentEncountersValue + 1);
		}
		boolean existsInMap = dictionary.containsKey(number);
		if (!existsInMap) return -1;
		return dictionary.get(number);
	}

	public static void main(String[] args) {
		Integer[] sampleArr = new Integer[]{
				4, 6, 3, 5, 3, 5, 2, 4, 8, 3
		};
		int num = 3;
		int encounters = getEncounters(sampleArr, num);
		System.out.println("Вхождений числа '" + num + "': " + encounters);
	}
}
