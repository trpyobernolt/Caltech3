package edu.caltech.cs2.datastructures;

import edu.caltech.cs2.interfaces.IDeque;
import edu.caltech.cs2.interfaces.IQueue;
import edu.caltech.cs2.interfaces.IStack;
import java.util.Iterator;

public class ArrayDeque<E> implements IDeque<E>, IQueue<E>, IStack<E> {
    private static final int grow_factor = 2;
    private static final int default_capacity = 10;
    private E[] data;
    private int size;

    public ArrayDeque(){
        this(default_capacity);
    }

    public ArrayDeque(int initialCapacity){
        this.data =  (E[])new Object[initialCapacity];
    }

    private int capacity(){
        return this.data.length;
    }

    private void ensureCapacity(int size){
        if (this.capacity() < size){
            E[] newData = (E[])new Object[(int)(this.capacity()*grow_factor)];
            for (int i = 0; i < this.size; i++){
                newData[i] = this.data[i];
            }
            this.data = newData;
        }
    }

    @Override
    public void addFront(E e) {
        ensureCapacity(this.size + 1);
        for (int i = this.size; i > 0; i--){
            this.data[i] = this.data[i - 1];
        }
        this.data[0] = e;
        this.size ++;
    }

    @Override
    public void addBack(E e) {
        ensureCapacity(this.size + 1);
        this.data[this.size] = e;
        this.size++;
    }

    @Override
    public E removeFront() {
        E first_elem = this.data[0];
        E[] newData = (E[])new Object[this.capacity()];
        for (int i = 0; i < this.size - 1; i++) {
            newData[i] = this.data[i + 1];
        }
        this.data = newData;
        if (this.size != 0){
            this.size--;
        }
        return first_elem;
    }

    @Override
    public E removeBack() {
        if (this.size != 0) {
            if (this.size != 0) {
                this.size--;
                return this.data[this.size];
            }
        }
        return null;
    }

    @Override
    public boolean enqueue(E e) {
        ArrayDeque.this.addFront(e);
        return true;
    }

    @Override
    public E dequeue() {
        return ArrayDeque.this.removeBack();
    }

    @Override
    public boolean push(E e) {
        ArrayDeque.this.addBack(e);
        return true;
    }

    @Override
    public E pop() {
        return ArrayDeque.this.removeBack();
    }

    @Override
    public E peek() {
        return this.peekBack();
    }

    @Override
    public E peekFront() {
        if (this.size == 0) {
            return null;
        }
        else {
            return this.data[0];
        }
    }

    @Override
    public E peekBack() {
        if (this.size == 0) {
            return null;
        }
        else {
            return this.data[this.size - 1];
        }
    }

    public class ArrayDequeIterator implements Iterator<E> {
        private int idx;

        public ArrayDequeIterator() {
            this.idx = -1;
        }

        public boolean hasNext() {
            return idx < ArrayDeque.this.size - 1;
        }

        public E next() {
            this.idx++;
            return ArrayDeque.this.data[this.idx];
        }
    }

    @Override
    public Iterator<E> iterator(){
        return new ArrayDequeIterator();
    }

    @Override
    public int size() {
        return this.size;
    }

    public String toString(){
        if (this.size == 0) {
            return "[]";
        }
        String result = "[";
        for (int i = 0; i < this.size; i++) {
            result += this.data[i] + ", ";
        }
        result = result.substring(0, result.length() - 2);
        return result + "]";
    }
}

