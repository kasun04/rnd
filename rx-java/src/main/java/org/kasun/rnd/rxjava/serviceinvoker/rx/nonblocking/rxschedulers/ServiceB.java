package org.kasun.rnd.rxjava.serviceinvoker.rx.nonblocking.rxschedulers;

import rx.Observable;
import rx.Subscriber;


public class ServiceB {

    public Observable<String> callAsync(final String msg) {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] " + "ServiceB processing request... ");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] " + "ServiceB : request processed.");

                subscriber.onNext("<ServiceB>" + msg + "</ServiceB>");
                subscriber.onCompleted();
            }
        });

        return observable;

    }
}
