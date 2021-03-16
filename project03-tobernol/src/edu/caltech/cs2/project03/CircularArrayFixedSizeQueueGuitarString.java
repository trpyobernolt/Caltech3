package edu.caltech.cs2.project03;

import edu.caltech.cs2.datastructures.CircularArrayFixedSizeQueue;
import edu.caltech.cs2.interfaces.IFixedSizeQueue;
import edu.caltech.cs2.interfaces.IQueue;

import java.util.Random;


public class CircularArrayFixedSizeQueueGuitarString {
    private IFixedSizeQueue<Double> guitarString;
    private static final Double energyDecay = 0.996;
    private static final int samplingRate = 44100;
    private static Random random;


    public CircularArrayFixedSizeQueueGuitarString(double frequency) {
        int spacing = (int)Math.ceil(samplingRate / frequency);
        this.guitarString = new CircularArrayFixedSizeQueue<>(spacing);
        for (int i = 0; i < spacing; i++) {
            this.guitarString.enqueue(0.0);
        }
        this.random = new Random();
    }

    public int length() {
        return this.guitarString.size();
    }

    public void pluck() {
        for (int i = 0; i < this.length(); i++) {
            Double randDouble = random.nextDouble() * (0.5 - -0.5) - 0.5;
            this.guitarString.dequeue();
            this.guitarString.enqueue(randDouble);
        }
    }

    public void tic() {
            Double val1 = this.guitarString.peek();
            this.guitarString.dequeue();
            Double val2 = this.guitarString.peek();
            Double step = ((val1 + val2) / 2) * energyDecay;
            this.guitarString.enqueue(step);
    }

    public double sample() {
        return this.guitarString.peek();
    }
}
