import java.util.Random;

public class Processor {
    private double frequency;
    private int cores;
    private final String series;

    public Processor() {
        this.series = generateSerialNumber();
        setCores(8);
        setFrequency(2.5);
    }

    public Processor(String series, int cores, double frequency) {
        this.series = series;
        this.cores = cores;
        this.frequency = frequency;
    }

    // Default Processor - 8 cores with frequency of 2.5 GHz
    public Processor(String series) {
        this.series = series;
        setCores(8);
        setFrequency(2.5);
    }

    public Processor(int cores) {
        this.series = generateSerialNumber();
        this.cores = cores;
        setFrequency(3.4);
    }

    public String generateSerialNumber() {
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);

        return String.valueOf(rand.nextLong());
    }

    public double getFrequency() {
        return frequency;
    }

    void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public int getCores() {
        return cores;
    }

    void setCores(int cores) {
        this.cores = cores;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "frequency=" + frequency +
                ", cores=" + cores +
                ", series='" + series + '\'' +
                '}';
    }
}
