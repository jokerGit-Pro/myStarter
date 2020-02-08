package com.joker.jokerspringbootstarterautoconfigurer;

public class HelloService {

    HelloPoperties helloPoperties;

    public HelloPoperties getHelloPoperties() {
        return helloPoperties;
    }

    public void setHelloPoperties(HelloPoperties helloPoperties) {
        this.helloPoperties = helloPoperties;
    }




    public String sayHello(String name){

        return helloPoperties.getPrefix()+name+helloPoperties.getSuffix();
    }
}
