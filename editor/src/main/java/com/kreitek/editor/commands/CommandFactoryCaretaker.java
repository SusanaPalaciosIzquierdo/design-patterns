package com.kreitek.editor.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandFactoryCaretaker {
    private static volatile CommandFactoryCaretaker instance = null;
    private Memento memento = new Memento();
    private List<Memento> mementoList = new ArrayList<>();

    private CommandFactoryCaretaker(){
        if (instance != null) {
            throw new RuntimeException("Usage getInstance() method to create");
        }
    }

    public static CommandFactoryCaretaker getInstance() {
        if (instance == null) {
            synchronized(Memento.class) {
                if (instance == null) {
                    instance = new CommandFactoryCaretaker();
                }
            }
        }
        return instance;
    }

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get() {
        if (mementoList.size() > 0) {
            Memento memento = mementoList.get(mementoList.size() -1);
            mementoList.remove(mementoList.size()-1);
            return memento;
        }
        return null;
    }

}
