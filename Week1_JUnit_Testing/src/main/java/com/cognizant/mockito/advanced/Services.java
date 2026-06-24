package com.cognizant.mockito.advanced;

// === Exercise 1 & 5: Repository and Service ===
interface Repository {
    String getData();
}

class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        return "Processed " + repository.getData();
    }
}

// === Exercise 2: RestClient and ApiService ===
interface RestClient {
    String getResponse();
}

class ApiService {
    private final RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String fetchData() {
        return "Fetched " + restClient.getResponse();
    }
}

// === Exercise 3: FileReader, FileWriter and FileService ===
interface FileReader {
    String read();
}

interface FileWriter {
    void write(String data);
}

class FileService {
    private final FileReader fileReader;
    private final FileWriter fileWriter;

    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public String processFile() {
        String content = fileReader.read();
        String processed = "Processed " + content;
        fileWriter.write(processed);
        return processed;
    }
}

// === Exercise 4: NetworkClient and NetworkService ===
interface NetworkClient {
    String connect();
}

class NetworkService {
    private final NetworkClient networkClient;

    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public String connectToServer() {
        return "Connected to " + networkClient.connect();
    }
}
