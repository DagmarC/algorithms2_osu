public class Memory {
    private final double capacity; // capacity of the memory is set only once

    public Memory() {
        this.capacity = 16; // GB
    }
    public Memory(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "capacity=" + capacity +
                '}';
    }
}
