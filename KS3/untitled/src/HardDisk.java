public class HardDisk {
    private double capacity; // GB
    private int rotationalSpeed; // RPM
    private int averageAccessTime; // ms
    private double cacheMemory; // MB

    // Default HDD
    public HardDisk() {
        setCapacity(1000); //GB
        setRotationalSpeed(5400); // RPM
        setAverageAccessTime(8); // ms
        setCacheMemory(128); // MB
    }

    public HardDisk(double capacity, int rotationalSpeed, int averageAccessTime, double cacheMemory) {
        this.capacity = capacity;
        this.rotationalSpeed = rotationalSpeed;
        this.averageAccessTime = averageAccessTime;
        this.cacheMemory = cacheMemory;
    }

    public HardDisk(double capacity) {
        this.capacity = capacity;
        setRotationalSpeed(5400); // RPM
        setAverageAccessTime(12); // ms
        setCacheMemory(64); // MB
    }

    public HardDisk(double cacheMemory, double capacity) {
        this.cacheMemory = cacheMemory;
        this.capacity = capacity;
        setRotationalSpeed(7200); // RPM
        setAverageAccessTime(12); // ms
    }

    public HardDisk(double capacity, int rotationalSpeed) {
        this.capacity = capacity;
        this.rotationalSpeed = rotationalSpeed;
        setAverageAccessTime(12); // ms
        setCacheMemory(128); // MB
    }

    public HardDisk(HardDisk hardDisk) {
        this.capacity = hardDisk.capacity;
        this.rotationalSpeed = hardDisk.rotationalSpeed;
        this.averageAccessTime = hardDisk.averageAccessTime;
        this.cacheMemory = hardDisk.cacheMemory;
    }

    public double getCacheMemory() {
        return cacheMemory;
    }

    void setCacheMemory(double cacheMemory) {
        this.cacheMemory = cacheMemory;
    }

    public int getAverageAccessTime() {
        return averageAccessTime;
    }

    void setAverageAccessTime(int averageAccessTime) {
        this.averageAccessTime = averageAccessTime;
    }

    public int getRotationalSpeed() {
        return rotationalSpeed;
    }

    void setRotationalSpeed(int rotationalSpeed) {
        this.rotationalSpeed = rotationalSpeed;
    }

    public double getCapacity() {
        return capacity;
    }

    void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "HardDisk{" +
                "capacity=" + capacity +
                ", rotationalSpeed=" + rotationalSpeed +
                ", averageAccessTime=" + averageAccessTime +
                ", cacheMemory=" + cacheMemory +
                '}';
    }
}
