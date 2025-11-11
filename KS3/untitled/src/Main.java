//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Processor customProcessor = new Processor("254354211111", 32, 3.4);
        Memory customMemory = new Memory(64);
        HardDisk customHardDisk = new HardDisk(2000, 7200, 10, 128);

        Computer customComputer = new Computer(customProcessor, customMemory, customHardDisk);
        System.out.println("Custom PC: \n" + "\t" + customComputer);

        Computer userComputer = new Computer(16, 32, 3000);
        System.out.println("User PC: \n" + "\t" +userComputer);
    }
}