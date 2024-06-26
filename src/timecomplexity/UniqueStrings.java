package timecomplexity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class UniqueStrings {
    /**
     * Для подсчета числа уникальных строк в переданном массиве можно использовать множество (Set).
     * <p>
     * Если все строки в массиве не длиннее 30 символов, то можно считать,
     * что доступ к элементу в множестве (Set) выполняется за O(1), так как время доступа к элементу в Set
     * в худшем случае зависит от количества элементов в коллекции, а не от размера элементов.
     * <p>
     * Тогда алгоритм для подсчета числа уникальных строк будет иметь сложность O(n),
     * то есть время выполнения будет пропорционально количеству элементов в массиве строк.
     * <p>
     * O(1) - доступ к элементу множества на основе хэш-таблицы (HashSet) и его добавление туда, если его ещё не нашли.
     * O(n) - перебор по массиву строк.
     * O(1) * O(n) = O(n)
     * <p>
     * Получившийся размер множества и есть количество уникальных строк в переданном массиве.
     */
    public static int countUniqueStrings(String[] strings) {
        Set<String> uniqueStrings = new HashSet<>(Arrays.asList(strings));
        return uniqueStrings.size();
    }

    public static void main(String[] args) {
        String[] sampleArr = new String[]{
                "bruh", "nruh", "bruh", "lol", "omg"
        };
        int unique = countUniqueStrings(sampleArr);
        System.out.println("Уникальных строк: " + unique);
    }
}
