package aoc.utils;

public class InstructionPointer {
    private int position;

    public InstructionPointer() {
        position = 0;
    }

    public void modify(int val) {
        position += val;
    }

    public void set(int val) {
        position = val;
    }

    public int getPosition() {
        return position;
    }
}
