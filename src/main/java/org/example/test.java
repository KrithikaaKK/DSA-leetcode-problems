package org.example;

import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.Callable;


public class test {

    static void print(){
        System.out.println(" 45");
    }

    public static void main(String[] args) throws InterruptedException {
        Optional<String> op = Optional.of("Heloo");
        System.out.println(op.isPresent());

    }

}