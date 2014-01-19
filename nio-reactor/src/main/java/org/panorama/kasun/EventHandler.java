package org.panorama.kasun;

import java.nio.channels.SelectionKey;

public interface EventHandler {

    public void handleEvent(SelectionKey handle) throws Exception;
}
