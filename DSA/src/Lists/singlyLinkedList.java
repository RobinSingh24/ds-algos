package Lists;

public class singlyLinkedList {

	// definition of singly linked list

	private listNode head;

	private static class listNode
	{
		private int data;
		private listNode next;

		public listNode(int data)
		{
			this.data = data;
			this.next = null;
		}

	}

	// display elements of linked list in order

	public void display() {

		listNode current = head ;
		while(current !=null)
		{
			System.out.print(current.data + "-->");
			current = current.next;
		}
		System.out.print("null\n");
	}

	// find length of linked list

	public int length() {

		listNode current = head;
		int count =0;
		while(current !=null)
		{
			count ++;
			current = current.next;
		}
		return count;
	}

	// insert at beginning of linked list

	public void insertFirst(int value)
	{
		listNode newnode = new listNode(value);
		newnode.next = head;
		head = newnode;
	}

	// insert at end of linked list

	public void insertEnd(int value) {

		listNode newnode = new listNode(value);
		listNode current = head;


		if(head==null)
		{
			head = newnode;
		}
		else
		{

			while(current.next!=null)
			{
				current = current.next;

			}
			current.next = newnode;
		}
	}

	//insert new node at a given position. Position indexing values from (1 till length+1), error on length+2

	public void insertPos(int position, int value)
	{
		listNode newnode = new listNode(value);
		if(position == 1)
		{
			newnode.next = head;
			head = newnode;
		}
		else
		{
			listNode previous =  head;

			int count = 1; //keeping track of current node

			while(count < position -1) // iterate to node after which we need to insert
			{
				previous = previous.next;
				count++;
			}

			listNode current = previous.next;
			previous.next = newnode;
			newnode.next = current;
		}
	}

	// delete from beginning of linked list

	public listNode deleteFirst() {

		if(head == null)  // no element in list
			return null;
		else
		{
			listNode temp = head;
			head = head.next;
			temp.next = null; // remove link to next node
			return temp;
		}
	}

	// delete from end of linked list

	public listNode deleteLast() {

		if(head==null) // no elements in list
			return null;

		else if (head.next == null) // only 1 element present in list
		{
			listNode temp = head;
			head = head.next;
			return temp;
		}

		else  // more than 1 element in list
		{
			listNode current = head ;
			listNode previous = null;

			while(current.next!=null)
			{
				previous = current;
				current = current.next;
			}

			previous.next = null; // break the chain
			return current;
		}
	}

	// delete node at a given position.Indexing from 1 till length
	// error at index length +1
	public void deletePos(int position)
	{
		if(position==1)
		{
			head = head.next;
		}
		else
		{
			listNode previous = head;
			int count =1;
			while(count<position-1)
			{
				previous = previous.next;
				count++;
			}
			previous.next = previous.next.next;

		}
	}

	public boolean find(int searchKey)  // Linear search in linked list
	{
		if (head == null) // no elements
			return false;

		listNode current = head;
		while (current != null) {
			if (current.data == searchKey)
				return true;

			current = current.next;
		}

		return false;
	}

	// reverse a singly linked list using 3 refs - previous, current and next

	public listNode reverse(listNode head)
	{
		if(head==null || head.next == null) // 0 or 1 node in list then return head
			return head;

		listNode current = head ;
		listNode previous = null ;
		listNode next = null ;

		while(current!=null)
		{
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}

		return previous;
	}

	// find middle node in linked list
	// slow pointer moves by 1 and fast pointer by 2

	public listNode findMiddle() {

		listNode slowPtr = head;
		listNode fastPtr = head;

		while(fastPtr!=null && fastPtr.next!=null)
		{
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
		}
		return slowPtr;
	}

	// return nth node from end of linked list
	// take two pointer - main pointer and ref pointer
	// increase ref pointer by value of n, then increment ref &  main ptr till  ref is null

	public listNode getNthNodeFromEnd(int n) {


		if(head == null)
			return head;

		int count =0;
		listNode mainPtr = head;
		listNode refPtr = head;

		while(count<n)
		{
			refPtr = refPtr.next;
			count++;
		}

		while(refPtr!=null)
		{
			mainPtr = mainPtr.next;
			refPtr = refPtr.next;
		}

		return mainPtr;
	}

	// linked list is sorted
	// just compare current with next node value to check for duplicates

	public void removeDuplicateFromSorted() {

		if(head == null)
			return;

		listNode current = head;

		while(current!=null && current.next!=null)
		{
			if(current.data == current.next.data)
			{
				current.next = current.next.next;
			}
			else
			{
				current = current.next;
			}
		}
	}

	// insert node in sorted list

	public void insertedInSortedList(int value) {

		listNode newNode = new listNode(value);

		if(head ==null)
		{
			insertFirst(value);
		}

		listNode temp = null;
		listNode current= head;

		while(current!=null && current.data < newNode.data)
		{
			temp = current;
			current= current.next;
		}

		newNode.next = current;
		temp.next = newNode;
	}

	//Floyd’s cycle finding algorithm or Hare-Tortoise algorithm 
	// fast pointer and slow pointer will coincide if loop exists

	public boolean containsLoop() {

		listNode fastPtr = head;
		listNode slowPtr = head;

		while(fastPtr != null && fastPtr.next != null) {
			fastPtr = fastPtr.next.next;
			slowPtr = slowPtr.next;

			if(fastPtr == slowPtr) {
				return true;
			}
		}
		return false;
	}

	// find starting node of loop inside linked list 

	public listNode startNodeInListLoop()
	{
		listNode slowPtr = head;
		listNode fastPtr = head;

		while(fastPtr !=null && fastPtr.next != null)
		{
			fastPtr = fastPtr.next.next;
			slowPtr = slowPtr.next;

			if(fastPtr == slowPtr) {
				break;
			}
		}	
		if(fastPtr==null || fastPtr.next==null)
			return new listNode(0);
		else
		{
			slowPtr = head;

			while(slowPtr != fastPtr)
			{
				slowPtr = slowPtr.next;
				fastPtr = fastPtr.next;
			}
		}

		return slowPtr;
	}


	// Remove loop present inside linked list

	public void removeListLoop()
	{
		listNode slowPtr = head;
		listNode fastPtr = head;

		while(fastPtr !=null && fastPtr.next != null)
		{
			fastPtr = fastPtr.next.next;
			slowPtr = slowPtr.next;

			if(fastPtr == slowPtr) {
				break;
			}
		}	
		if(fastPtr!=null || fastPtr.next!=null)
		{
			slowPtr = head;

			while(slowPtr.next != fastPtr.next)
			{
				slowPtr = slowPtr.next;
				fastPtr = fastPtr.next;
			}
			fastPtr.next = null;
		}

	}
	
	// Merge two sorted linked list
	
	public listNode mergeSortedLists(listNode head1, listNode head2)
	{
		listNode dummy = new listNode(0);
		listNode tail = dummy;
		
		while(head1!=null && head2!=null)
		{
			if(head1.data<=head2.data)
			{
				tail.next = head1;
				head1 = head1.next;
			}
			else
			{
				tail.next = head2;
				head2 = head2.next;
			}
			tail = tail.next;
		}
		if(head1==null)
			tail.next = head2;
		else
			tail.next = head1;
	
	
	return dummy.next;
	}





	public static void main(String [] args)
	{
		
		singlyLinkedList sl = new singlyLinkedList();
		singlyLinkedList sl2 = new singlyLinkedList();
		
		sl.insertFirst(2);
		sl.insertFirst(1);
		sl.insertEnd(3);
		sl.insertEnd(24);
		sl.insertPos(1, 1);
		sl.insertedInSortedList(25);
		sl.display();
		
		sl2.insertFirst(4);
		sl2.insertEnd(23);
		sl2.insertEnd(27);
		sl2.insertEnd(50);
		sl2.display();
		
		// merge two sorted lists
		
//		listNode temp = sl.mergeSortedLists(sl.head, sl2.head);
//		sl.head = temp;
//		sl.display();


		// creating a loop

//		listNode temp = sl.head;
//				while(temp.next!=null)
//					temp = temp.next;
//				
//				temp.next = sl.head.next.next.next.next; 
//
//		temp = sl.startNodeInListLoop();
//		System.out.println(temp.data);
//		
//		sl.removeListLoop();
	


		//		sl.removeDuplicateFromSorted();
		//		sl.display();

		//		sl.deleteFirst();
		//
		//		sl.display();
		//
		//		sl.deletePos(3);
		//		sl.display();
		//		System.out.println(sl.find(10));
		//
		//		sl.head = sl.reverse(sl.head);
		//
		//		sl.display();
		//		System.out.println(sl.findMiddle().data);
		//
		//		System.out.println(sl.getNthNodeFromEnd(3).data);










	}

}
