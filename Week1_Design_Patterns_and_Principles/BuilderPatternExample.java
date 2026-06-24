/*
 * Exercise 3: Implementing the Builder Pattern
 * 
 * Scenario: Developing a system to create complex objects such as a Computer 
 * with multiple optional parts.
 * 
 * The Builder Pattern separates the construction of a complex object from its 
 * representation, allowing the same construction process to create different 
 * representations.
 */

// Product class: Computer
class Computer {
    // Required attributes
    private String CPU;
    private String RAM;
    // Optional attributes
    private String storage;
    private String GPU;
    private String operatingSystem;
    private boolean isBluetoothEnabled;
    private boolean isWifiEnabled;
    
    // Private constructor that takes a Builder
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
        this.operatingSystem = builder.operatingSystem;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
        this.isWifiEnabled = builder.isWifiEnabled;
    }
    
    // Getters
    public String getCPU() { return CPU; }
    public String getRAM() { return RAM; }
    public String getStorage() { return storage; }
    public String getGPU() { return GPU; }
    public String getOperatingSystem() { return operatingSystem; }
    public boolean isBluetoothEnabled() { return isBluetoothEnabled; }
    public boolean isWifiEnabled() { return isWifiEnabled; }
    
    @Override
    public String toString() {
        return "Computer Configuration:" +
               "\n  CPU: " + CPU +
               "\n  RAM: " + RAM +
               "\n  Storage: " + storage +
               "\n  GPU: " + GPU +
               "\n  OS: " + operatingSystem +
               "\n  Bluetooth: " + (isBluetoothEnabled ? "Enabled" : "Disabled") +
               "\n  WiFi: " + (isWifiEnabled ? "Enabled" : "Disabled");
    }
    
    // Static nested Builder class
    public static class Builder {
        // Required attributes
        private String CPU;
        private String RAM;
        // Optional attributes
        private String storage = "256GB SSD";
        private String GPU = "Integrated";
        private String operatingSystem = "Windows 11";
        private boolean isBluetoothEnabled = false;
        private boolean isWifiEnabled = true;
        
        // Builder constructor with required parameters
        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }
        
        // Methods to set optional attributes
        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }
        
        public Builder setOperatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }
        
        public Builder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }
        
        public Builder setWifiEnabled(boolean isWifiEnabled) {
            this.isWifiEnabled = isWifiEnabled;
            return this;
        }
        
        // Build method to return the Computer object
        public Computer build() {
            return new Computer(this);
        }
    }
}

// Test class
public class BuilderPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Example ===\n");
        
        // Build a basic office computer
        Computer officePC = new Computer.Builder("Intel i5", "8GB")
                .setStorage("512GB SSD")
                .setOperatingSystem("Windows 11")
                .build();
        System.out.println("--- Office PC ---");
        System.out.println(officePC);
        
        System.out.println();
        
        // Build a high-end gaming computer
        Computer gamingPC = new Computer.Builder("Intel i9", "32GB")
                .setStorage("2TB NVMe SSD")
                .setGPU("NVIDIA RTX 4090")
                .setOperatingSystem("Windows 11 Pro")
                .setBluetoothEnabled(true)
                .setWifiEnabled(true)
                .build();
        System.out.println("--- Gaming PC ---");
        System.out.println(gamingPC);
        
        System.out.println();
        
        // Build a minimal server
        Computer server = new Computer.Builder("AMD EPYC", "128GB")
                .setStorage("4TB NVMe RAID")
                .setOperatingSystem("Ubuntu Server 22.04")
                .setWifiEnabled(false)
                .build();
        System.out.println("--- Server ---");
        System.out.println(server);
    }
}
