package org.kasun.rnd.rxjava.serviceinvoker.rx.nonblocking.rxschedulers;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class ServiceA {

    public Observable<String> callAsync(final String msg) {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] " + "ServiceA : processing request... ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] " + "ServiceA : request processed.");

                subscriber.onNext("<ServiceA>" + msg + "</ServiceA>");
                subscriber.onCompleted();
            }
        });

        return observable;
    }

}
