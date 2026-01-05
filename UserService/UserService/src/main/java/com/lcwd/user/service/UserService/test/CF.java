package com.lcwd.user.service.UserService.test;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CF {


    public static String task1() {
        System.out.println(Thread.currentThread());
        return "task1";
    }

    public static String task2() {
        System.out.println(Thread.currentThread());
        return "task2";
    }

    public static void main(String[] args) {

        System.out.println("hello world ");

//        CompletableFuture<String> task1 =  CompletableFuture.supplyAsync(()-> {
//            try {
//                Thread.sleep(3000);
//                System.out.println("Daemon Thread "+Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//            return "TASK 1";
//
//        });
//
//        CompletableFuture<String> task2 =  CompletableFuture.supplyAsync(()-> {
//            try {
//                Thread.sleep(2000);
//                System.out.println("Daemon Thread "+Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//            return "TASK 2";
//
//        });
//        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(task1,task2);
//           completableFuture.join();

//

        CF.task1();
        CF.task2();

        System.out.println(Thread.currentThread().getName());
    }
}
