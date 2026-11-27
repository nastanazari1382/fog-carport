package entities;

public class Carport {
    private int width;
    private int length;

    public Carport(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Carport{width=" + width + ", length=" + length + "}";
    }
}
