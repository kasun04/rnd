package org.kasun.rnd.rxjava.basic;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;

public class Tester {
    public static void main(String[] args) throws Exception {
        basicRx4();
    }


    /* Observable.create and subscriber */
    public static void basicRx1() throws Exception {
        Observable<Integer> observableString = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                for (int i = 1; i < 10; i++) {
                    observer.onNext(i);
                }
                observer.onCompleted();
            }
        });
        System.out.println("Observable Created, values emitted, waiting for 3s.");
        Thread.sleep(3000);

        Subscriber<Integer> mySubscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Observer completed!");

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error!");

            }

            @Override
            public void onNext(Integer i) {
                System.out.println("Observer has received : " + i);

            }
        };
        observableString.subscribe(mySubscriber);
    }


    /* Observerbale.just and subscriber */
    public static void basicRx2() throws Exception {
        Observable<String> breakingNewsObservable = Observable.just("[Breaking news]US election polls");

        Thread.sleep(4000);
        Subscriber<String> newsSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Breaking news received!");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error!");

            }

            @Override
            public void onNext(String s) {
                System.out.println("News : " + s);

            }
        };
        breakingNewsObservable.subscribe(newsSubscriber);
    }

    /* Observerble.from with filter and map */
    public static void basicRx3() throws Exception {
        List<String> newsList = new ArrayList<String>();
        newsList.add("US-Election GoP polls");
        newsList.add("US-China trade");
        newsList.add("US-Weather alert.");
        newsList.add("US-Sports NFL.");
        newsList.add("International-Sports Cricket.");
        newsList.add("International-India election.");

        Observable<String> newsObservable = Observable.from(newsList)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.startsWith("US-");
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s.toUpperCase();
                    }
                });
        newsObservable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Completed!");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("News received : " + s);
            }
        });
    }

    public static void basicRx3Lambda() throws Exception {
        List<String> newsList = new ArrayList<String>();
        newsList.add("US-Election GoP polls.");
        newsList.add("US-China trade");
        newsList.add("US-Weather alert.");
        newsList.add("US-Sports NFL.");
        newsList.add("International-Sports Cricket.");
        newsList.add("International-India election.");

        Observable<String> newsObservable = Observable.from(newsList)
                                                        .filter(s -> s.startsWith("US-"))
                                                        .map(String::toUpperCase);
        newsObservable
                .subscribe(s -> System.out.println("News " + s));
    }



    public static void basicRx4() throws Exception {
        callLocationAPI("2.2", "2.1")
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return callWeatherAPI(s);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("All API calls completed!");

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("Data received : " + s);

                    }
                });
    }

    public static Observable<String> callLocationAPI(String latitude, String longitude) {
        System.out.println("Calling Geo-Location API...");
        delay(1000);
        return Observable.just("Boston, Ma");
    }

    public static Observable<String> callWeatherAPI(String location) {
        System.out.println("Calling Weather API...");
        delay(1500);
        return Observable.just(location + " : Saturday 12:00 PM : Light Rain Showers");
    }

    public static void delay(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}