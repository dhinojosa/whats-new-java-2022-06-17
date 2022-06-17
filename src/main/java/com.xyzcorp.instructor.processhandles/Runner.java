package com.xyzcorp.instructor.processhandles;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        System.out.printf("Process Starting on %s%n",
            ProcessHandle.current().pid());
        Thread.sleep(100000);
    }
}
