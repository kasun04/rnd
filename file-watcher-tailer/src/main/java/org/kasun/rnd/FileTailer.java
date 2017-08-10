package org.kasun.rnd;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.Executor;

/**
 * Created by kasun on 8/10/17.
 */
public class FileTailer {

    public static void main(String[] args) throws Exception {

        TailerListener listener = new FileTailerAdapter();
        Tailer tailer = new Tailer(new File("src/main/resources/records.txt"), listener, 500);
        tailer.run();

    }
}