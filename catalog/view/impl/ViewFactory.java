package catalog.view.impl;

import catalog.view.View;

/**
 * Created by Student on 31-May-16.
 */
public class ViewFactory {
    public static final String CONSOLE = "CONSOLE";

    private static ViewFactory instance;
    private ViewFactory(){};

    public static ViewFactory getInstance() {
        if (instance == null)
        {
            instance = new ViewFactory();
        }
        return instance;
    }

    public View getView(String type) {
        switch (type){
            case CONSOLE:
                return new ConsoleView();
            default: return new ConsoleView();
        }
    }
}
