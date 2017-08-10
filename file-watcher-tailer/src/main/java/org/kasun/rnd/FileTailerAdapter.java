package org.kasun.rnd;

import org.apache.commons.io.input.TailerListenerAdapter;

/**
 * Created by kasun on 8/10/17.
 */
public class FileTailerAdapter extends TailerListenerAdapter  {
        @Override
        public void handle(String line) {
            System.out.println(line);
        }
}
