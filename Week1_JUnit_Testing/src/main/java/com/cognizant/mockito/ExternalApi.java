package com.cognizant.mockito;

public interface ExternalApi {
    String getData();
    String getDataWithArg(String param);
    void doSomething();
    void doSomethingWithArg(String param);
}
