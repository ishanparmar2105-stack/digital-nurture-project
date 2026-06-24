package com.cognizant.mockito;

public class MyService {
    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchDataWithArg(String param) {
        return externalApi.getDataWithArg(param);
    }

    public void performAction() {
        externalApi.doSomething();
    }

    public void performActionWithArg(String param) {
        externalApi.doSomethingWithArg(param);
    }
}
