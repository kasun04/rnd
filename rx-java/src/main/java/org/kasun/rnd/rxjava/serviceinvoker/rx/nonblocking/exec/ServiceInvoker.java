package org.kasun.rnd.rxjava.serviceinvoker.rx.nonblocking.exec;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class ServiceInvoker {
    public static void main(String[] args) {

        System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] "
                           + "Initialization");
        new ServiceA().callAsync("<Client>Hello</Client>")
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return new ServiceB().callAsync(s);
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return new ServiceC().callAsync(s);
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed");
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] "
                                           + "Final Response : " + s);
                    }
                });
        System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] "
                           + "Parent");

    }
}
