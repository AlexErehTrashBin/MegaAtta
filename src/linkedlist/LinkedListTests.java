package linkedlist;

public class LinkedListTests {
	public static void main(String[] args) {
		IntegerSimpleLinkedList list = new IntegerSimpleLinkedList();
		list.addLast(2, 3, 4, 5);
		System.out.println(list);
		list.duplicatePowerOfTwoElements();
		System.out.println(list);
		list.removeElement(2);
		System.out.println(list);
		list.removeElement(4);
		System.out.println(list);

		list.removeElement(3);
		list.removeElement(5);
		System.out.println(list);
	}
}
