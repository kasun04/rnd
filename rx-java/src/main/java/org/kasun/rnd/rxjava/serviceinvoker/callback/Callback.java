package org.kasun.rnd.rxjava.serviceinvoker.callback;

public interface Callback<T> {
    void completed(T value);
}
