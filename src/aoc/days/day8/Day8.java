package aoc.days.day8;

import aoc.days.Day;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Day8 extends Day {
    private List<Layer> layers;
    private final int width = 25;
    private final int height = 6;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        new Day8();
    }

    @Override
    protected void part1() {
        Layer bestLayer = layers.get(0);
        for (Layer layer : layers) {
            if (layer.leastZeros() < bestLayer.leastZeros()) {
                bestLayer = layer;
            }
        }
        System.out.println(bestLayer.checksum());
    }

    @Override
    protected void part2() {
        int[][] image = new int[height][];
        for (int row = 0; row < height; row++) {
            int[] pixelRow = new int[width];
            for (int pos = 0; pos < width; pos++) {
                pixelRow[pos] = getColor(row, pos);
            }
            image[row] = pixelRow;
        }
        printImage(image);
    }

    private void printImage(int[][] image) {
        for (int row = 0; row < image.length; row++) {
            for (int pos = 0; pos < image[row].length; pos++) {
                if (image[row][pos] == 0) {
                    System.out.print(ANSI_BLACK + "\u2588" + ANSI_RESET);
                } else {
                    System.out.print(ANSI_RED + "\u2588" + ANSI_RESET);
                }
            }
            System.out.println();
        }
    }

    private int getColor(int row, int pos) {
        for (Layer layer : layers) {
            int color = layer.get(row, pos);
            if (color != 2) {
                return color;
            }
        }
        throw new InputMismatchException("Transparent pixel?");
    }

    @Override
    protected void setup() {
        layers = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (char c : lines.get(0).toCharArray()) {
            values.add(Integer.parseInt(Character.toString(c)));
        }
        for (int layer = 0; layer < values.size(); layer += width * height) {
            int[][] pixels = new int[height][];
            for (int row = 0; row < height; row++) {
                int[] pixelRow = new int[width];
                for (int pos = 0; pos < width; pos++) {
                    pixelRow[pos] = values.get(layer + row * width + pos);
                }
                pixels[row] = pixelRow;
            }
            layers.add(new Layer(pixels));
        }
    }
}
