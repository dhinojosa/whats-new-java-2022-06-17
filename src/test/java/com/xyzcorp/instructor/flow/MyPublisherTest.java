package com.xyzcorp.instructor.flow;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class MyPublisherTest {
    @Test
    void testFlow() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        MyPublisher myPublisher = new MyPublisher(executorService);
        myPublisher.subscribe(new Flow.Subscriber<Long>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(1000);
            }

            @Override
            public void onNext(Long item) {
                System.out.format("S1 On Next: %d\n", item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.format("S1 On Error %s\n", throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("S1 On Complete\n");
            }
        });

        myPublisher.subscribe(new Flow.Subscriber<Long>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(10);
            }

            @Override
            public void onNext(Long item) {
                System.out.format("S2 On Next: %d\n", item);
                if (item == 10L) subscription.request(100);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.format("S2 On Error %s\n", throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("S2 On Complete\n");
            }
        });

    }
}
