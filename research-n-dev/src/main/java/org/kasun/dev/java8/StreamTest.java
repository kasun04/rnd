package org.kasun.dev.java8;

import java.util.Arrays;
import java.util.Random;

public class StreamTest {
    public static void main(String[] args) {

        final String payload = "MESSAGE_100L\n" +
                               "MESSAGE_101R\n" +
                               "MESSAGE_102L\n" +
                               "MESSAGE_103J\n" +
                               "MESSAGE_104F\n" +
                               "MESSAGE_1059\n" +
                               "MESSAGE_106M\n" +
                               "MESSAGE_107D\n" +
                               "MESSAGE_108Q\n" +
                               "MESSAGE_1095\n" +
                               "MESSAGE_110K\n" +
                               "MESSAGE_1117\n" +
                               "MESSAGE_1122\n" +
                               "MESSAGE_113R\n" +
                               "MESSAGE_114B\n" +
                               "MESSAGE_115R\n" +
                               "MESSAGE_116D\n" +
                               "MESSAGE_1172\n" +
                               "MESSAGE_118N\n" +
                               "MESSAGE_119P\n" +
                               "MESSAGE_1207\n";


        String[] messages = payload.split("\n");


        Arrays.stream(messages).parallel()
                .map(s -> s.replace("MESSAGE_", "PAYLOAD_"))
                .map(s->s.replaceFirst("1", "1000"))
                .reduce((x,y)-> x + y)
                .ifPresent(System.out::println);
    }
}
