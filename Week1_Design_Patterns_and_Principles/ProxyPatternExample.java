/*
 * Exercise 6: Implementing the Proxy Pattern
 * 
 * Scenario: Developing an image viewer application that loads images from a 
 * remote server. Use the Proxy Pattern to add lazy initialization and caching.
 * 
 * The Proxy Pattern provides a surrogate or placeholder for another object 
 * to control access to it. It is useful for lazy initialization, access control, 
 * logging, and caching.
 */

// Subject Interface
interface Image {
    void display();
}

// Real Subject: RealImage (loads from remote server)
class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromServer();
    }
    
    private void loadImageFromServer() {
        System.out.println("Loading image from remote server: " + filename);
        // Simulate network delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Image loaded successfully: " + filename);
    }
    
    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// Proxy Class: ProxyImage (with lazy initialization and caching)
class ProxyImage implements Image {
    private String filename;
    private RealImage realImage;  // Cached reference
    
    public ProxyImage(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void display() {
        // Lazy initialization: load image only when first requested
        if (realImage == null) {
            System.out.println("ProxyImage: First access - loading image...");
            realImage = new RealImage(filename);
        } else {
            System.out.println("ProxyImage: Using cached image...");
        }
        realImage.display();
    }
}

// Test class
public class ProxyPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Proxy Pattern Example ===\n");
        
        // Create proxy images (no loading happens yet - lazy initialization)
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");
        
        System.out.println("Images created but not yet loaded.\n");
        
        // First access - image will be loaded from server
        System.out.println("--- First access to image1 ---");
        image1.display();
        
        System.out.println();
        
        // Second access - image will be served from cache
        System.out.println("--- Second access to image1 (cached) ---");
        image1.display();
        
        System.out.println();
        
        // First access to image2
        System.out.println("--- First access to image2 ---");
        image2.display();
    }
}
