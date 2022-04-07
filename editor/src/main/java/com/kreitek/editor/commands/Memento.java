package com.kreitek.editor.commands;

import java.util.ArrayList;
import java.util.List;

public class Memento {
    private List<String> documentLines = new ArrayList<>();

    public List<String> getState(){
        return documentLines;
    }

    public void setState(List<String> documentLines){
        this.documentLines.clear();
        this.documentLines.addAll(documentLines);
    }

}
