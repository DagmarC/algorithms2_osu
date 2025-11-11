public class Computer {
    public Processor cpu;
    public Memory memory;
    public HardDisk hdd;

    // Default PC
    public Computer() {
        this.cpu = new Processor();
        this.hdd = new HardDisk();
        this.memory = new Memory();
    }

    // Typical User preferences
    public Computer(int cores, double memCapacity, double hddCapacity) {
        this.cpu = new Processor(cores);
        this.hdd = new HardDisk(hddCapacity);
        this.memory = new Memory(memCapacity);
    }

    // All the units can be separately created and specified as wanted.
    public Computer(Processor cpu, Memory memory, HardDisk hdd) {
        this.cpu = cpu;
        this.memory = memory;
        this.hdd = hdd;
    }

    public Computer(double hddCapacity) {
        this.hdd = new HardDisk(hddCapacity);
        this.cpu = new Processor();
        this.memory = new Memory();
    }

    public Computer(int cores) {
        this.cpu = new Processor(cores);
        this.memory = new Memory();
        this.hdd = new HardDisk();
    }

    public Computer(double memCapacity, double hddCapacity) {
        this.memory = new Memory(memCapacity);
        this.hdd = new HardDisk(hddCapacity);
        this.cpu = new Processor(16);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu=" + cpu +
                ", memory=" + memory +
                ", hdd=" + hdd +
                '}';
    }
}
