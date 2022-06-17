package com.xyzcorp.instructor.flow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MyPublisher implements Flow.Publisher<Long> {

    private final ExecutorService executorService;

    public MyPublisher(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super Long> subscriber) {
         subscriber.onSubscribe(new Flow.Subscription() {
             final AtomicBoolean done = new AtomicBoolean();
             final AtomicLong counter = new AtomicLong();
             final AtomicLong requests = new AtomicLong();
             final AtomicBoolean started = new AtomicBoolean();

             @Override
             public void request(long n) {
                 requests.addAndGet(n);
                 if (started.compareAndSet(false, true)) {
                     runLoop();
                 }
             }

             private void runLoop() {
                 executorService.submit(() -> {
                     while (!done.get()) {
                         if (requests.decrementAndGet() >= 0) {
                             subscriber.onNext(counter.incrementAndGet());
                         }
                     }
                 });
             }

             @Override
             public void cancel() {
                done.set(true);
             }
         });
    }
}
