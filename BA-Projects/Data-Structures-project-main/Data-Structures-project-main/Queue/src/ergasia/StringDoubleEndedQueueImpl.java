package ergasia;
import java.io.PrintStream; 
import java.util.NoSuchElementException;

    class Node<T> {	                            // Orismos klasis komvou me generics
	private T data;                             
	private Node next;                          // Metavlites dedomenon komvou
	private Node prev;                          //  kai anfora se next kai prev 
	                                            //  stoixeia ton komvon
	public Node(T data){   		                // kataskeuastis komvou me arxikopoiisi ton metavliton
		this.data = data;
		this.next=null;
		this.prev=null;
	}
	public void printData() {System.out.println(this.data);}    // ektiposi ton stoixeion toy komvou
	
	public T getData() {return data;}                           // epistrofi dedomenon komvou
	
	public Node<T> getNext(){return next;}                      // epistrofi tis anaforas tou komvou ston epomeno
	
	public Node<T> getPrev(){return prev;}                      // epistrofi tis anaforas tou komvou ston proigoymeno
	
	public void setNext(Node<T> next) {this.next = next;}       // thetei tin anafora next tou komvou ston epomeno komvo
	
	public void setPrev(Node<T> prev) {this.prev = prev;}       //  thetei tin anafora prev tou komvou ston proigoumeno komvo
}

public class StringDoubleEndedQueueImpl<T> implements StringDoubleEndedQueue<T> {  // ilopoiisi tis klasis StringDoubleEndedQueueImpl
	
	private Node<T> head=null;                         // Metavlites head kai tail me arxikopoiisi se null.  
	private Node<T> tail=null;
	private int size;                                  // Metavliti size gia to plithos ton stoixeion tis ouras
	
	public StringDoubleEndedQueueImpl (){              // Kataskeuastis ouras me arxiko size=0. 
		size = 0;
	}
	
	public boolean isEmpty(){	                      // Methodos isEmpty (boolean) einai true an i oura einai adeia 	
		return head == null;
	}
	/**
	 * insert a String item at the front of the queue
	 */
	public void addFirst(T data) {                   // Metodos eisagogis eisodou neou komvou stin arxi tis listas
		Node<T> node = new Node<>(data);
		size++;
		if(this.isEmpty()==true) {
			head = node;
			tail = node;
		}
		else {
			node.setNext(head);
			head.setPrev(node);
			head = node;
			}
		}
	/**
	 * remove and return the item at the front of the queue
	 * @return String from the front of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public T removeFirst() throws NoSuchElementException{              // Metodos afairesis komvou apo tin arxi tis listas
		if(isEmpty() == true) throw new NoSuchElementException(); 
		
		T data = head.getData();
		size--;
		if(head==tail) {head = tail= null;}
		else {
			head = head.getNext();
			head.setPrev(null);
		}
		//System.out.println("deleted: "+data);
		return data;
	}

	/**
	 * insert a String item at the end of the queue
	 */
	public void addLast(T data) {                        // Metodos eisagogis eisodou neou komvou sto telos tis listas
		Node<T> node = new Node<>(data);
		size++;
		if(this.isEmpty()==true) {
			head = node;
			tail = node;			
		}
		else {
			node.setPrev(tail);
			tail.setNext(node);			
			tail = node;	
		}		
	}
	/**
	 * remove and return the item at the end of the queue
	 * @return String from the end of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public T removeLast() throws NoSuchElementException{             // Metodos afairesis komvou apo to telos tis listas
		if(isEmpty()==true) throw new NoSuchElementException();
		T data = tail.getData();		
		size--;
		if(head==tail) {head=tail=null;}
		else {
			tail = tail.getPrev();
			tail.setNext(null);
		}
		System.out.println("deleted: "+data);
		return data;
	}
	
	/**
	 * return without removing the item at the front of the queue
	 * @return String from the front of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public T getFirst() throws NoSuchElementException{                     // Metodos epistrofis tou protou stoixeiou tis listas
		if(isEmpty()==true) throw new NoSuchElementException();
		
		T data = head.getData();
		//System.out.println("First data of list is : " + data);
		return data;
		}
	
	/**
	 * return without removing the item at the end of the queue
	 * @return String from the end of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public T getLast() throws NoSuchElementException{                  // Metodos epistrofis tou teleutaiou stoixeiou tis listas
		if(isEmpty()==true) throw new NoSuchElementException();
		T data = tail.getData();
		//System.out.println("Last data of list is : " + data);
		return data;
	}
	
	
	/**
	 * print the String items of the queue, starting from the front, 
     	 * to the print stream given as argument. For example, to 
         * print the elements to the
	 * standard output, pass System.out as parameter. E.g.,
	 * printQueue(System.out);
	 */
	public void printQueue(PrintStream stream){               // Ektiposi stoixeion ouras
	if (isEmpty()) {
		System.out.println("The Queue is Empty! ");
	}
	
	else {
		Node<T> h=head;
		while(h!=null) {
			stream.println(h.getData());
			h=h.getNext();			
			}
		}
	}

	/**
	 * return the size of the queue, 0 if empty
	 * @return number of elements in the queue
	 */
	public int size() {                                                    // ektiposi plithous komvov/stoixeion ouras.
		//System.out.println("The elements in the queue are: " + size);
		return size;
	}
}

	