package org.kasun.rnd.rxjava.serviceinvoker.rx.nonblocking.rxschedulers;

import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ServiceC {
    final static ExecutorService executor = new ScheduledThreadPoolExecutor(5);


    public Observable<String> callAsync(final String msg) {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] " + "ServiceC processing request... ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] " + "ServiceC : request processed.");
                subscriber.onNext("<ServiceC>" + msg + "</ServiceC>");
                subscriber.onCompleted();
            }
        });

        return observable;

    }
}
