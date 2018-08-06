/*  
 Program #2
 Brandon Baniqued 
 cssc0886
*/

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedLinkedListPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {

	private int currentSize;
	private Node<E> head = null;
	
	private class Node<E> {

		public E data;
		public Node<E> next;

		public Node(E d) {

			data = d;
			

		}

	}
	
	// Inserts a new object into the priority queue. Returns true if
	// the insertion is successful. If the PQ is full, the insertion
	// is aborted, and the method returns false.
	public boolean insert(E object) {

		Node<E> newNode = new Node<E>(object);
		
        if (isEmpty()) {
            head = newNode;
        }
        else {
            newNode.next = head;
            head = newNode;
        }

        currentSize++;
        return true;

	}

	// Removes the object of highest priority that has been in the
	// PQ the longest, and returns it. Returns null if the PQ is empty.
    public E remove() {

    		if (isEmpty()) {
    			return null;
    		}
    		
        Node<E> current = head, currentBest = head;
        Node<E> previous = null, previousBest = null;

        while (current != null) {
            if (current.data.compareTo(currentBest.data) <= 0) {
                previousBest = previous;
                currentBest = current;

            }
            previous = current;
            current = current.next;
        }

        if (previousBest == null) {
            head = head.next;
        }
        else {
            previousBest.next = currentBest.next;
        }
        currentSize--;
        return currentBest.data;
    }

	// Deletes all instances of the parameter obj from the PQ if found, and
	// returns true. Returns false if no match to the parameter obj is found.
	public boolean delete(E obj) {

		Node<E> temp = head, prev = null;

		while (temp != null && temp.data.compareTo(obj) == 0) {

			head = head.next;
			temp = head;
			
			currentSize--;

		}

		while (temp != null) {

			while (temp != null && temp.data.compareTo(obj) != 0) {

				prev = temp;
				temp = temp.next;
				
			}

			if (temp == null)
				return false;

			prev.next = temp.next;

			temp = prev.next;

			
		}

		return true;

	}

	// Returns the object of highest priority that has been in the
	// PQ the longest, but does NOT remove it.
	// Returns null if the PQ is empty.
	public E peek() {

		if (isEmpty()) {
            return null;
        }

        Node<E> current = head, currentBest = head;
        

        while (current != null) {
        	
            if (current.data.compareTo(currentBest.data) <= 0) {
                currentBest = current;
            }
            current = current.next; 
           
        }

        return currentBest.data;

	}

	// Returns true if the priority queue contains the specified element
	// false otherwise.
	public boolean contains(E obj) {

		Node<E> current = head;

		while (current != null) {

			if (obj.compareTo(current.data) == 0) {
				
				return true;
			}
			current = current.next;
		}
		return false;
	}

	// Returns the number of objects currently in the PQ.
	public int size() {

		return currentSize;

	}

	// Returns the PQ to an empty state.
	public void clear() {

		
		head = null;
		currentSize = 0;

	}

	// Returns true if the PQ is empty, otherwise false
	public boolean isEmpty() {

		return currentSize < 1;

	}

	// Returns true if the PQ is full, otherwise false. List based
	// implementations should always return false.
	public boolean isFull() {

		return false;

	}

	// Returns an iterator of the objects in the PQ, in no particular
	// order.

	public Iterator<E> iterator() {
		return new IteratorHelper();
	}

	class IteratorHelper implements Iterator<E> {
		Node<E> iterPtr;

		public IteratorHelper() {

			iterPtr = head;

		}

		public boolean hasNext() {

			return iterPtr != null;
			
		}

		public E next() {

			if (!hasNext())
				throw new NoSuchElementException();
			E temp = iterPtr.data;
			iterPtr = iterPtr.next;
			return temp;

		}

		public void remove() {

			throw new UnsupportedOperationException();

		}
	}

}
