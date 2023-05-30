package linkedlist;

import java.util.Iterator;

@SuppressWarnings("unused")
public class SimpleLinkedList<T> implements Iterable<T> {
	protected ListItem head = null;
	protected ListItem tail = null;
	protected int size = 0;

	public SimpleLinkedList() {
	}
	// Добавляем в начало элемент
	public void addFirst(T value) {
		head = new ListItem(value, head);
		if (isEmpty()) {
			tail = head;
		}
		size++;
	}

	public void addLast(T value) {
		if (isEmpty()) {
			head = tail = new ListItem(value);
		} else {
			tail.next = new ListItem(value);
			tail = tail.next;
		}
		size++;
	}

	@SafeVarargs
	public final void addLast(T... values) {
		for (T value : values) {
			addLast(value);
		}
	}

	private void checkEmptyError() throws MyLinkedListException {
		if (isEmpty()) {
			throw new MyLinkedListException("Пустой список!");
		}
	}

	private ListItem getNode(int index) {
		ListItem curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr;
	}

	public T removeFirst() throws MyLinkedListException {
		checkEmptyError();
		T first = head.value;
		head = head.next;
		if (size == 1) {
			tail = null;
		}
		size--;
		return first;
	}

	public T removeLast() throws MyLinkedListException {
		checkEmptyError();
		T last = tail.value;
		if (size == 1) {
			head = tail = null;
		} else {
			tail = getNode(size - 2);
			tail.next = null;
		}
		size--;
		return last;
	}

	public T remove(int index) throws MyLinkedListException {
		checkEmptyError();
		if (index < 0 || index >= size) {
			throw new MyLinkedListException("index выходит за границы текущего списка!");
		}
		if (index == 0) {
			T first = head.value;
			removeFirst();
			return first;
		}
		ListItem prev = getNode(index - 1);
		T indexElement = prev.next.value;
		prev.next = prev.next.next;
		if (prev.next == null) {
			tail = prev;
		}
		size--;
		return indexElement;
	}

	public void clear() {
		if (isEmpty()) return;
		head = null;
		tail = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public T get(int index) {
		boolean isNotEmpty = !isEmpty();
		if (isNotEmpty) {
			return getNode(index).value;
		}
		return null;
	}

	public T getFirst() throws MyLinkedListException {
		checkEmptyError();
		return head.value;
	}

	public T getLast() throws MyLinkedListException {
		checkEmptyError();
		return tail.value;
	}
	/**
	 * Метод, который удаляет подряд идущие дубликаты из списка.
	 * */
	public void deleteDuplicates() {
		if (head == null) return;

		ListItem current = head;
		// Проходимся по связному списку
		while (current != null) {
			ListItem nextNode = current.next;

			// Если у следующей ноды такое же значение, как у текущей -> пропускаем её.
			while (nextNode != null && current.value == nextNode.value) {
				nextNode = nextNode.next;
				// Не забываем уменьшить размер,
				// так как эффективно связный список уменьшился на 1 элемент
				size--;
			}

			// Set the next node of the current node to the next unique node
			current.next = nextNode;

			// If we reached the end of the list, set the tail node
			if (nextNode == null) tail = current;

			// Move to the next unique node
			current = current.next;
		}
	}
	/**
	 * Метод, который удаляет каждый k-ый элемент из списка.
	 * */
	public void deleteEveryKthElement(int k){
		if (k <= 0) {
			return;
		}

		ListItem current = head;
		ListItem previous = null;
		int count = 1;

		while (current != null) {
			if (count == k) {
				if (previous != null) {
					previous.next = current.next;
				} else {
					head = current.next;
				}

				if (current.next == null) {
					tail = previous;
				}

				size--;
				count = 0;
			}

			count++;
			previous = current;
			current = current.next;
		}

		if (k == 1) {
			head = null;
			tail = null;
			size = 0;
		}

	}

	@Override
	public Iterator<T> iterator() {
		class LinkedListIterator implements Iterator<T> {
			ListItem curr = head;

			@Override
			public boolean hasNext() {
				return curr != null;
			}

			@Override
			public T next() {
				T value = curr.value;
				curr = curr.next;
				return value;
			}
		}

		return new LinkedListIterator();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (T element : this) {
			sb.append(element).append(", ");
		}
		if (this.isEmpty()) {
			sb.append(']');
			return sb.toString();
		}
		sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}

	public static class MyLinkedListException extends Exception {
		public MyLinkedListException(String message) {
			super(message);
		}
	}

	protected class ListItem {
		public T value;
		public ListItem next;

		public ListItem(T value, ListItem next) {
			this.value = value;
			this.next = next;
		}

		public ListItem(T value) {
			this(value, null);
		}
	}
}
