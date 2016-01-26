package org.kasun.rnd.rxjava.serviceinvoker.rx.nonblocking.exec;

import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ServiceC implements Runnable {
    final static ExecutorService executor = new ScheduledThreadPoolExecutor(5);

    private String request;
    private Subscriber<? super String> serviceSubscriber;

    public Observable<String> callAsync(String msg) {
        request = msg;
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                serviceSubscriber = subscriber;
            }
        });
        executor.submit(this);
        return observable;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] " + "ServiceC processing request... ");

        serviceSubscriber.onNext("<ServiceC>" + request + "</ServiceC>");
        serviceSubscriber.onCompleted();
    }
}
