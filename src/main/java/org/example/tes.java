package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
 class tes {

     void print() {

//public class tes {
//
//    Document document = Jsoup.connect("https://main.un.org/securitycouncil/en/content/list-updates-unsc-consolidated-list").get();
//
//
//    public tes() throws IOException {
//        System.out.println(document.html());
//    }
//
//    public static void main(String[] args) throws IOException {
//        tes tes = new tes();
//
//    }
//}
     }
 }

interface A {
    default void greet() {
        System.out.println("Hello from A");
    }
}

interface B {
    default void greet() {
        System.out.println("Hello from B");
    }
}

class C implements A, B {
    // ❌ Compiler error — which greet() to use?

    @Override
    public void greet(){
        System.out.println("");
      //  A.super.greet();
    }
}