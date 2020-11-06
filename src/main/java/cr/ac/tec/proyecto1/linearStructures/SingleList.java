package cr.ac.tec.proyecto1.linearStructures;

public class SingleList<T> {




    private Node<T> first;
    private int size;

    /**
     * Simple linked list that is used to store the cards and that is used to make a stack that works as a deck for the cards
     */

    public SingleList(){

        this.first = null;
        this.size = 0;
    }

    /**
     * check if the list is empty
     * @return
     */
    public boolean isEmpty(){
        return this.first == null;
    }

    /**
     * Adds an element to the end
     * @param element element to add
     */
    public void add(T element){
        if(this.isEmpty()){
            this.first = new Node<T>(element);
        }else{
            Node<T> ref = this.first;
            while (ref.getNext() != null){
                ref = ref.getNext();
            }
            ref.setNext(new Node<T>(element));
        }
        size++;
    }


    /**
     * Adds an element to the start
     *
     */
    public void addToStart(T element){

        Node<T> newNode = new Node();

        newNode.setValue(element);

        if(this.isEmpty()){
            first = newNode;
        }else {
            newNode.setNext(first);
            this.first = newNode;
        }
        size++;
    }

    /**
     * get size of the linked list
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * print the list
     */
    public void showList(){

        Node<T> current = this.first;

        while (current.getNext()!=null){
            System.out.println(current.getValue());
            current = current.getNext();
        }
        System.out.println(current.getValue());
    }


    /**
     * add and element by reference
     * @param reference
     * @param element
     */
    public void addByReference(T reference,T element){

        Node<T> newNode = new Node<>();
        newNode.setValue(element);

        if(!this.isEmpty()){
            if(findElement(reference)){
                Node<T> aux = this.first;

                while (aux.getValue() != reference){
                    aux = aux.getNext();
                }
                Node next = aux.getNext();
                aux.setNext(newNode);
                newNode.setNext(next);

                size++;
            }
        }

    }


    /**
     * finds a given element
     * @param element
     * @return
     */
    public boolean findElement(T element){

        Node<T> aux = this.first;

        boolean found = false;

        while (aux != null && found != true) {
            if (element.equals(aux.getValue())) {
                found = true;
            } else {
                aux = aux.getNext();
            }
        }
        return found;
    }


    /**
     * adds a new element to the list, it can be added anywhere
     * @param position
     * @param element
     */
    public void addByPosition(int position, T element){

        if(position >= 0 && position <= this.size){
            Node<T> newNode = new Node<>();
            newNode.setValue(element);

            if(position == 0){
                newNode.setNext(this.first);
                this.first = newNode;
            }
            else{
                if(position == this.size){
                    Node<T> aux = this.first;
                    while (aux.getNext()!=null){
                        aux = aux.getNext();
                    }
                    aux.setNext(newNode);
                }
                else {
                    Node<T> aux = this.first;
                    for(int i = 0; i < (position-1);i++){
                        aux = aux.getNext();
                    }

                    Node<T> nextNode = aux.getNext();
                    aux.setNext(newNode);
                    newNode.setNext(nextNode);

                }
            }
            size++;
        }
    }

    /**
     * gets an element from the list from a position given
     * @param position
     * @return
     * @throws IndexOutOfBoundsException
     */
    public T getElement(int position) throws IndexOutOfBoundsException{
        if(position>=0 && position<this.size){
            if(position == 0){
                return this.first.getValue();
            }else{
                Node<T> aux = this.first;

                for(int i = 0; i < position; i++){
                    aux = aux.getNext();
                }
                return aux.getValue();
            }
        }else {
            throw new IndexOutOfBoundsException("Position is non existent on the list");
        }
    }

    /**
     * gets position of an element on the list
     * @param element
     * @return
     * @throws IndexOutOfBoundsException
     */
    public int getPosition(T element) throws IndexOutOfBoundsException{
        if(findElement(element)){
            Node aux = this.first;

            int counter = 0;
            while (element != aux.getValue()){
                counter++;
                aux = aux.getNext();
            }
            return counter;

        }else {
            throw new IndexOutOfBoundsException("Element does not exist on the list");
        }

    }

    /**
     * edit an element given the element and a new value
     * @param element
     * @param value
     */
    public void editByElement(T element, T value){
        if(findElement(element)){
            Node aux = this.first;

            while (aux.getValue()!= element){
                aux = aux.getNext();
            }
            aux.setValue(value);
        }
    }


    /**
     * edit the element given an index and the new element to replace
     * @param index
     * @param element
     */
    public void editByIndex(int index, T element){
        if (index>=0 && index<this.size){
            if (index == 0){
                this.first.setValue(element);
            }else {
                Node aux = this.first;
                for (int i = 0; i < index; i++){
                    aux = aux.getNext();
                }
                aux.setValue(element);
            }
        }
    }


    /**
     * delete an element given the element to delete
     * @param element
     */
    public void deleteByElement(T element){
        if(findElement(element)){
            if(this.first.getValue()==element){

                this.first = this.first.getNext();

            }else{
                Node aux = this.first;

                while (aux.getNext().getValue() != element){
                    aux = aux.getNext();
                }

                Node nextNode = aux.getNext().getNext();
                aux.setValue(nextNode);
            }
            this.size--;
        }
    }

    /**
     * Delete an element by the index
     * @param index
     */
    public void deleteByIndex(int index){
        if(index >= 0 && index<this.size){
            if (index == 0){
                this.first = this.first.getNext();
            }
            else {
                Node aux = this.first;
                for (int i = 0; i < index; i++){
                    aux = aux.getNext();
                }
                Node nextNode = aux.getNext();

                aux.setValue(nextNode.getNext());
            }
            this.size--;
        }
    }

    /**
     * delete the list
     */

    public void deleteList(){
        this.first = null;
        this.size = 0;
    }

}
