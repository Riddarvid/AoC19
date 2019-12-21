package aoc.utils.intcode;

import java.util.LinkedList;
import java.util.Scanner;

public class Communicator implements Runnable, Controller {
    private long[] memory;
    private LinkedList<Integer> requests;
    private LinkedList<Integer> outputs;

    public Communicator(long[] memory) {
        this.memory = memory;
        requests = new LinkedList<>();
        outputs = new LinkedList<>();
    }

    @Override
    public void run() {
        new IntcodeComputer(this, memory).execute();
    }

    @Override
    public long getInput() {
        synchronized (this) {
            while (requests.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int request = requests.getFirst();
            requests.removeFirst();
            return request;
        }
    }

    @Override
    public void output(long val) {
        synchronized (this) {
            outputs.addLast((int)val);
            notifyAll();
        }
    }

    public void makeRequest(int request) {
        synchronized (this) {
            requests.addLast(request);
            notifyAll();
        }
    }

    public int getOutput() {
        synchronized (this) {
            while (outputs.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int output = outputs.getFirst();
            outputs.removeFirst();
            return output;
        }
    }
}