package org.kasun.rnd.rxjava.serviceinvoker.callback;

public class Tester {
    public static void main(String[] args) {

        final ServiceA serviceA = new ServiceA();
        final ServiceB serviceB = new ServiceB();
        final ServiceC serviceC = new ServiceC();

        System.out.println("Thread ID " + Thread.currentThread().getId());

        serviceA.callAsync("<clientReq>req</clientReq>", new Callback<String>() {
            @Override
            public void completed(String value) {
                System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] "
                                   + "Response from Service-A : " + value);

                serviceB.callAsync(value, new Callback<String>() {
                    @Override
                    public void completed(String value) {
                        System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] "
                                           + "Response from Service-B : " + value);
                        serviceC.callAsync(value, new Callback<String>() {
                            @Override
                            public void completed(String value) {
                                System.out.println("[ThreadID-" + Thread.currentThread().getId() + "] "
                                                   + "Response from Service-C : " + value);
                            }
                        });
                    }
                });
            }
        });
        System.out.println("Async req done. : " + "Thread ID " + Thread.currentThread().getId());
    }
}
