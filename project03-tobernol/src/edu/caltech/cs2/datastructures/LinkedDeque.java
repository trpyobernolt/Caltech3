package edu.caltech.cs2.datastructures;

import edu.caltech.cs2.interfaces.IDeque;
import edu.caltech.cs2.interfaces.IQueue;
import edu.caltech.cs2.interfaces.IStack;
import org.w3c.dom.Node;

import java.util.Iterator;

public class LinkedDeque<E> implements IDeque<E>, IQueue<E>, IStack<E> {
    private DequeNode<E> head;
    private int size;
    private DequeNode<E> tail;

    private class DequeNode<E>{
        E data;
        DequeNode<E> next;
        DequeNode<E> previous;
        public DequeNode(E data) {
            this.data = data;
        }
    }

    @Override
    public void addFront(E e) {
        DequeNode<E> curr = this.head;
        DequeNode newElem = new DequeNode(e);
        if (this.head == null) {
            this.head = newElem;
            this.tail = newElem;
        }
        else {
            newElem.next = curr;
            curr.previous = newElem;
            this.head = newElem;
        }
        this.size++;
    }

    @Override
    public void addBack(E e) {
        DequeNode<E> curr = this.tail;
        DequeNode newElem = new DequeNode<>(e);
        if (this.head == null) {
            this.head = newElem;
            this.tail = newElem;
        }
        else {
            newElem.previous = curr;
            curr.next = newElem;
            this.tail = newElem;
        }
        this.size++;
    }

    @Override
    public E removeFront() {
        if (this.head == null) {
            return null;
        }
        E val = this.head.data;
        if (this.head.next != null){
            this.head = this.head.next;
            this.head.previous = null;
        }
        else {
            this.head = null;
            this.tail = null;
        }
        this.size--;
        return val;
    }

    @Override
    public E removeBack() {
        if (this.tail == null) {
            return null;
        }
        E val = this.tail.data;
        if (this.tail.previous != null) {
            this.tail = this.tail.previous;
            this.tail.next = null;
        }
        else {
            this.tail = null;
            this.head = null;
        }
        this.size--;
        return val;
    }

    @Override
    public boolean enqueue(E e) {
        LinkedDeque.this.addFront(e);
        return true;
    }

    @Override
    public E dequeue() {
        return LinkedDeque.this.removeBack();
    }

    @Override
    public boolean push(E e) {
        LinkedDeque.this.addBack(e);
        return true;
    }

    @Override
    public E pop() {
        return LinkedDeque.this.removeBack();
    }

    @Override
    public E peek() {
        return this.peekBack();
    }

    @Override
    public E peekFront() {
        if (this.head != null){
            return this.head.data;
        }
        return null;
    }

    @Override
    public E peekBack() {
        if (this.tail != null) {
            return this.tail.data;
        }
        return null;
    }

    public class LinkedDequeIterator implements Iterator<E> {
        private DequeNode curr;

        public LinkedDequeIterator() {
            this.curr = LinkedDeque.this.head;
        }

        public boolean hasNext() {
            return curr != null;
        }

        public E next() {
            E data = (E)curr.data;
            curr = curr.next;
            return data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedDequeIterator();
    }

    @Override
    public int size() {
        return this.size;
    }

    public String toString(){
        if (this.head == null || this.tail == null){
            return "[]";
        }
        DequeNode curr = this.head;
        String result = "";
        while (curr != null && curr.next != null){
            result += curr.data + ", ";
            curr = curr.next;
        }
        return "[" + result + curr.data +  "]";
    }
}
