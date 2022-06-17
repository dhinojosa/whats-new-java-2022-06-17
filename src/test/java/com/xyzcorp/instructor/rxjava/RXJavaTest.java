package com.xyzcorp.instructor.rxjava;

import com.xyzcorp.instructor.flow.MyPublisher;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RXJavaTest {
    @Test
    void testRXJava() {
        Observable<Integer> observable = Observable.just(1, 23, 50)
                                                   .map(x -> x * 2);
        Disposable done =
            observable
                .subscribe(System.out::println,
                    Throwable::printStackTrace,
                    () -> System.out.println("Done"));

        observable
            .doOnNext(n -> System.out.println("1" + Thread.currentThread()))
            .observeOn(Schedulers.newThread())
            .doOnNext(n -> System.out.println("2" + Thread.currentThread()))
            .filter(x -> x < 25)
                  .subscribe(System.out::println,
                      Throwable::printStackTrace,
                      () -> System.out.println("Done"));

    }

    @Test
    void testWithPublisher() {
        //Observable.fromPublisher(new MyPublisher(Executors.newFixedThreadPool(2)));
    }














}
