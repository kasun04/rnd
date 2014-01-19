package org.panorama.kasun;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class Reactor {
    private Map<Integer, EventHandler> registeredHandlers = new ConcurrentHashMap<Integer, EventHandler>();
    private Selector demultiplexer;

    public Reactor() throws Exception {
        demultiplexer = Selector.open();
    }

    public Selector getDemultiplexer() {
        return demultiplexer;
    }

    public void registerEventHandler(
            int eventType, EventHandler eventHandler) {
        registeredHandlers.put(eventType, eventHandler);
    }

    public void registerChannel(
            int eventType, SelectableChannel channel) throws Exception {
        channel.register(demultiplexer, eventType);
    }

    public void run() {
        try {
            while (true) { // Loop indefinitely
                demultiplexer.select();

                Set<SelectionKey> readyHandles =
                        demultiplexer.selectedKeys();
                Iterator<SelectionKey> handleIterator =
                        readyHandles.iterator();

                while (handleIterator.hasNext()) {
                    SelectionKey handle = handleIterator.next();

                    if (handle.isAcceptable()) {
                        EventHandler handler =
                                registeredHandlers.get(SelectionKey.OP_ACCEPT);
                        handler.handleEvent(handle);
                    }

                    if (handle.isReadable()) {
                        EventHandler handler =
                                registeredHandlers.get(SelectionKey.OP_READ);
                        handler.handleEvent(handle);
                        handleIterator.remove();
                    }

                    if (handle.isWritable()) {
                        EventHandler handler =
                                registeredHandlers.get(SelectionKey.OP_WRITE);
                        handler.handleEvent(handle);
                        handleIterator.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
