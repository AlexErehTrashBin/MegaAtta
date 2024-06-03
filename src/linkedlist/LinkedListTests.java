package linkedlist;

public class LinkedListTests {
    public static void main(String[] args) {
        IntegerSimpleLinkedList list = new IntegerSimpleLinkedList();
        list.addLast(2, 3, 4, 5);
        System.out.println(list);
        list.duplicatePowerOfTwoElementsTask();
        System.out.println(list);
        list.removeElementTask(2);
        System.out.println(list);
        list.removeElementTask(4);
        System.out.println(list);

        list.removeElementTask(3);
        list.removeElementTask(5);
        System.out.println(list);
    }
}
