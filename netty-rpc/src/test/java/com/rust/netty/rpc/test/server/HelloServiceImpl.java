package com.rust.netty.rpc.test.server;

import com.rust.netty.rpc.server.RpcService;
import com.rust.netty.rpc.test.client.HelloService;
import com.rust.netty.rpc.test.client.Person;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    public HelloServiceImpl(){

    }

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}
