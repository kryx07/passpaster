package com.krystiankowalik.passpaster;

import com.google.common.eventbus.EventBus;

public class EventBusProvider {

    //final private static Logger logger = Logger.getLogger(EventBusProvider.class);

    private static EventBus eventBus;

    public static EventBus getInstance() {

        if (eventBus == null) {
            //logger.debug("New Instance of eventBus needed");
            eventBus = new EventBus();
        }
        return eventBus;
    }

    public static void registerEventBus(Class clazz) {
        getInstance().register(clazz);
    }

}