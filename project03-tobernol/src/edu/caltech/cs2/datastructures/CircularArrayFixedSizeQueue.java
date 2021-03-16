package edu.caltech.cs2.datastructures;

import edu.caltech.cs2.interfaces.IFixedSizeQueue;

import java.util.Iterator;

public class CircularArrayFixedSizeQueue<E> implements IFixedSizeQueue<E> {
    private int front;
    private int back;
    private E[] data;

    public CircularArrayFixedSizeQueue(int capacity){
        this.data =  (E[])new Object[capacity];
        this.front = this.back = 0;
    }

    @Override
    public boolean isFull() {
        if (this.capacity() == this.size()) {
            return true;
        }
        return false;
    }

    @Override
    public int capacity() {
        return (this.data.length);
    }

    @Override
    public boolean enqueue(E e) {
        if (this.isFull()) {
            return false;
        }
        else if (this.size() == 0){
            this.front = 0;
            this.back = 0;
            this.data[this.back] = e;
            return true;
        }
        else {
            if (this.back == this.capacity() - 1){
                this.back = -1;
            }
            this.back++;
            this.data[this.back] = e;
            return true;
        }
    }

    @Override
    public E dequeue() {
        if (this.size() == 0){
            return null;
        }
        E data = this.data[this.front];
        this.data[this.front] = null;
        if (this.front == this.back) {
            return data;
        }
        if (this.front == this.capacity() - 1){
            this.front = 0;
            }
        else {
            this.front++;
        }
        return data;
    }

    @Override
    public E peek() {
        if (this.back == this.front && this.data[this.front] == null){
            return null;
        }
        else{
            return this.data[this.front];
        }
    }

    @Override
    public int size() {
        if (this.back == this.front && this.data[this.front] == null) {
            return 0;
        }
        if (this.back == this.front && this.data[this.front] != null) {
            return 1;
        }
        else if (this.back > this.front) {
            return (this.back - this.front + 1);
        }
        else {
            return (this.capacity() - (this.front - this.back - 1));
        }
    }

    public class CircularArrayFixedSizedQueueIterator implements Iterator<E> {
        private int idx;

        public CircularArrayFixedSizedQueueIterator() {
            this.idx = CircularArrayFixedSizeQueue.this.front;
        }

        public boolean hasNext() {
            return idx != CircularArrayFixedSizeQueue.this.back;
        }

        public E next() {
            if (idx == CircularArrayFixedSizeQueue.this.size()) {
                idx = 0;
            } else {
                this.idx++;
            }
            return CircularArrayFixedSizeQueue.this.data[this.idx];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularArrayFixedSizedQueueIterator();
    }

    public String toString() {
        if (this.size() == 0) {
            return "[]";
        }
        String string = "";
        if (this.front == this.back) {
            string += this.data[this.front];
        }
        if (this.front > this.back) {
            for (int i = this.back; i < this.back + this.size(); i++) {
                string += this.data[this.back + i];
                if (this.back + i != this.front){
                    string += ", ";
                }
            }
            return string;
        }
        if (this.back > this.front) {
            for (int i = this.front; i < this.size() + this.front; i++){
                int looper = i % (this.capacity());
                string += this.data[looper];
                if (looper != this.back){
                    string += ", ";
                }
            }
        }
        return "[" + string + "]";
        }
    }
