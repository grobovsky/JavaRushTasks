package com.javarush.task.task32.task3206;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ItemInvocationHandler<T> implements InvocationHandler {
    /*private T hand;

    public ItemInvocationHandler(T c){
        this.hand = c;
    }*/

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null; //method.invoke(hand, args);
    }
}
