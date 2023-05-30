package linkedlist;

public class IntegerSimpleLinkedList extends SimpleLinkedList<Integer> {

	private static boolean isPowerOfTwo(int number) {
		return (number & (number - 1)) == 0;
	}

	public void duplicatePowerOfTwoElementsTask() {
		ListItem currentNode = head;
		while (currentNode != null) {
			boolean added = false;
			if (isPowerOfTwo(currentNode.value)) {
				ListItem newNode = new ListItem(currentNode.value, currentNode.next);
				currentNode.next = newNode;
				size++;
				if (newNode.next == null) {
					tail = newNode;
				}
				added = true;
			}
			if (added) currentNode = currentNode.next;
			currentNode = currentNode.next;
		}
	}
}
