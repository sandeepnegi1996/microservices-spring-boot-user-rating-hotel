package com.lcwd.user.service.UserService.practice;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PracticeJava {
    public static void main(String[] args) {

        System.out.println("hello world rom practice java file");


        // takes input  and return a boolean
        Predicate<Integer> isEven = num -> num % 2 == 0;

        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 5 even? " + isEven.test(5));


        // List of integer
        List<Integer> intergers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // if we check here the filter method implementation we can see it takes Predicate as input
        //
        List<Integer> evenList =  intergers.stream()
                                            .filter(n -> n % 2==0)
                                            .toList();

        System.out.println("Even Numbers: " + evenList);



        // consumer
        // takes input and return nothing

        Consumer<Integer> printNumber = n -> System.out.println("number : "+n);

        // accept is the method in the consumer interface which takes input and return nothing
        // consumer will be used to do something with the values

//        printNumber.accept(10);


        intergers.forEach(n -> System.out.println("Number: " + n));

        //todo
        // todo : realworld example usecases for the predicate , consumer, producer
        // todo : what is method reference

        //supplier
        // takes nothing and return something
        // often called as producer

        Supplier<UUID> uuidSupplier = () -> UUID.randomUUID();
        System.out.println("UUID from the supplier :  " +uuidSupplier.get());




    }
}
