/*
 * Exercise 11: Implementing Dependency Injection
 * 
 * Scenario: Customer management app where service depends on repository.
 * Use constructor injection to manage dependencies.
 */

interface CustomerRepository {
    String findCustomerById(int id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    public String findCustomerById(int id) {
        // Simulating database lookup
        if (id == 1) return "John Doe";
        if (id == 2) return "Jane Smith";
        if (id == 3) return "Ishan Parmar";
        return null;
    }
}

class CustomerService {
    private CustomerRepository customerRepository;

    // Constructor Injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void getCustomer(int id) {
        String customerName = customerRepository.findCustomerById(id);
        if (customerName != null) {
            System.out.println("Customer found: " + customerName + " (ID: " + id + ")");
        } else {
            System.out.println("Customer with ID " + id + " not found.");
        }
    }
}

public class DependencyInjectionExample {
    public static void main(String[] args) {
        System.out.println("=== Dependency Injection Example ===\n");
        // Inject the repository implementation into the service
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);
        service.getCustomer(1);
        service.getCustomer(2);
        service.getCustomer(3);
        service.getCustomer(4); // Not found
    }
}
