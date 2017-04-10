package LinkedList;
// Reality Check: implement size in this Linked List
// then, implement isEmpty()

public class rechekc {
    private Node head;           //instance of Node object
    private Node tail;

    // SLL constructor
    public rechekc(){   //default constructor for linked list
        head = null;
        tail = null;
    }
    
    // SLL methods
    public void addToFront(int data){   //method to add a node to the front
        head = new Node(data,head);
        if (tail == null) {
        tail = head;
    }
    }
    
    public void addToBack(int data) {
        if (head == null) {
            addToFront(data);
        } else {
        tail.next = new Node(data);
        tail = tail.next;
        }
    }

    public void removeFromFront() {
        if (head == tail) {
            tail = null;
        }
        head = head.next;
    }

    public void removeFromBack() {
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node cur = head;
            while (cur.next != tail) {
                cur = cur.next;
            }
            cur.next = null;
            tail = cur;
        }
    }
    public int getSize(){
        int n = 0;
        Node g = head;
        while(g.next != tail){
            n++;
        }
        return n;
    }
    public boolean isEmpty(){
        return head==tail && head == null;
    }
    
    public String toString(){       //method to print from Node.toString()
        String answer = "";
        Node current = head;
        while (current != null){
        // answer += current.toString() + " ";      //explicit call
            answer += current + " ";    //implicit call
            current = current.next;
        }
        return answer;
    }



    //create Node class
    private class Node{
        private int data;       //variable for Node object
        private Node next;      //reference variable for Node object

    // Node constructors
        private Node(int data,Node next){// full constructor of Node
            this.data = data;
            this.next = next;           
        }
        
        private Node(int data){     //overload constructor
            this(data,null);        //constructor chaining
        // this.data = data;
            // this.next = null;
        }
        
    // Node methods
        public String toString() {  //method to print
            return Integer.toString(data);
        // return "" + data
        }
    }


    
    //call to main
    public static void main(String[] args){
    
        rechekc age = new rechekc(); //create the linked list, head points to null
        System.out.println(age);
        
        age.addToFront(32);     //add first node
        System.out.println(age);
        
        age.addToFront(13);     //add second node
    age.addToFront(15);     //3rd
    age.addToFront(20);     //4th
        System.out.println(age);
        
    age.addToBack(25);
        age.addToBack(50);
        System.out.println(age);

    }
}