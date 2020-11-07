package cr.ac.tec.proyecto1.linearStructures;

public class BattleLog {
    public Node head;

    class Node {
        Object data;
        Node prev;
        Node next;

        Node(Object d) { data = d; }
    }

    public void push(Object new_data) {
        Node new_Node = new Node(new_data);


        new_Node.next = head;
        new_Node.prev = null;

        if (head != null)
            head.prev = new_Node;

        head = new_Node;
    }


    void append(Object new_data) {
        Node new_node = new Node(new_data);

        Node last = head;

        new_node.next = null;

        if (head == null) {
            new_node.prev = null;
            head = new_node;
            return;
        }

        while (last.next != null)
            last = last.next;


        last.next = new_node;

        new_node.prev = last;
    }

    public void printlist(Node node) {
        Node last = null;
        System.out.println("Primero a último");
        while (node != null) {
            System.out.print(node.data + " ");
            last = node;
            node = node.next;
        }
        System.out.println();
        System.out.println("Último a primero");
        while (last != null) {
            System.out.print(last.data + " ");
            last = last.prev;
        }
    }

    public Object getData(Node node) {
        return node.data;
    }
}



