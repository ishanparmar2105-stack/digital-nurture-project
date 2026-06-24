/*
 * Exercise 2: Implementing the Factory Method Pattern
 * 
 * Scenario: Developing a document management system that needs to create 
 * different types of documents (Word, PDF, Excel).
 * 
 * The Factory Method Pattern defines an interface for creating an object, 
 * but lets subclasses alter the type of objects that will be created.
 */

// Abstract Document interface
abstract class Document {
    public abstract void open();
    public abstract void save();
    public abstract void close();
}

// Concrete Document: WordDocument
class WordDocument extends Document {
    @Override
    public void open() {
        System.out.println("Opening Word Document...");
    }
    
    @Override
    public void save() {
        System.out.println("Saving Word Document...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Word Document...");
    }
}

// Concrete Document: PdfDocument
class PdfDocument extends Document {
    @Override
    public void open() {
        System.out.println("Opening PDF Document...");
    }
    
    @Override
    public void save() {
        System.out.println("Saving PDF Document...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing PDF Document...");
    }
}

// Concrete Document: ExcelDocument
class ExcelDocument extends Document {
    @Override
    public void open() {
        System.out.println("Opening Excel Document...");
    }
    
    @Override
    public void save() {
        System.out.println("Saving Excel Document...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Excel Document...");
    }
}

// Abstract Factory class
abstract class DocumentFactory {
    public abstract Document createDocument();
}

// Concrete Factory: WordDocumentFactory
class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

// Concrete Factory: PdfDocumentFactory
class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

// Concrete Factory: ExcelDocumentFactory
class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// Test class
public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Example ===\n");
        
        // Create Word Document using Factory
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();
        
        System.out.println();
        
        // Create PDF Document using Factory
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.save();
        pdfDoc.close();
        
        System.out.println();
        
        // Create Excel Document using Factory
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
        excelDoc.save();
        excelDoc.close();
    }
}
