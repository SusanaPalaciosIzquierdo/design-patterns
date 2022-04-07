package com.kreitek.editor.commands;

import com.kreitek.editor.Command;

import java.util.ArrayList;

public class UndoCommand implements Command {

    public UndoCommand() {
    }

    @Override
    public void execute(ArrayList<String> documentLines) {
        documentLines.clear();
        Memento memento = CommandFactoryCaretaker.getInstance().get();
        if (memento!=null){
            documentLines.addAll(memento.getState());
        }
    }
}
