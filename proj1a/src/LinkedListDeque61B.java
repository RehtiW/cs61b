import java.sql.Array;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements  Deque61B<T>{
    private class Node{
        T val;
        Node prev;
        Node next;
        Node(Node prev,Node next,T val){
            this.val=val;
            this.prev=prev;
            this.next=next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque61B(){
        sentinel=new Node(null,null,null);
        sentinel.prev=sentinel;
        sentinel.next=sentinel;
        size=0;
    }
    @Override
    public void addFirst(T val){
        Node first=new Node(sentinel,sentinel.next,val);
        sentinel.next.prev=first;
        sentinel.next=first;
        size+=1;
    }
    @Override
    public void addLast(T val){
        Node last = new Node(sentinel.prev,sentinel,val);
        sentinel.prev.next=last;
        sentinel.prev= last;
        size+=1;

    }

    @Override
    public List<T> toList() {
        List <T> returnList = new ArrayList<>();
        Node curr=sentinel.next;
        while(curr!=sentinel){
            returnList.add(curr.val);
            curr=curr.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(isEmpty()){
            return null;
        }
        T returnVal=sentinel.next.val;
        sentinel.next=sentinel.next.next;
        sentinel.next.prev=sentinel;
        size --;
        return returnVal;
    }

    @Override
    public T removeLast() {
        if(isEmpty()){
            return null;
        }
        T returnVal= sentinel.prev.val;
        sentinel.prev=sentinel.prev.prev;
        sentinel.prev.next=sentinel;
        size --;
        return returnVal;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= this.size){
            return null;
        }
        Node curr=sentinel.next;
        T returnVal=null;
        while(curr!=sentinel){
            if(index==0){
                returnVal=curr.val;
            }
            curr=curr.next;
            index--;
        }
        return returnVal;

    }

    @Override
    public T getRecursive(int index) {
        return getRecursive(index,sentinel.next);
    }

    private T getRecursive(int index, Node p){
        if(index < 0 || index >= this.size ){
            return null;
        }
        if(index == 0){
            return p.val;
        }else{
            return getRecursive(index-1,p.next);
        }
    }


}