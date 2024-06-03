package trees;

public class TreeTests {
    public static void main(String[] args) throws Exception {
        SimpleTree<Integer> tree1 = new SimpleTree<>(Integer::parseInt);
        tree1.fromBracketNotation("8 (6 (4 (5), 6), 5 (, 5 (2, 8)))");
        boolean result1 = tree1.checkForDuplicatesTask();
        System.out.println(result1);

        SimpleTree<Integer> tree2 = new SimpleTree<>(Integer::parseInt);
        tree2.fromBracketNotation("11 (12 (4 (5), 6), 7 (, 8 (9, 10)))");
        boolean result2 = tree2.checkForDuplicatesTask();
        System.out.println(result2);
    }
}
