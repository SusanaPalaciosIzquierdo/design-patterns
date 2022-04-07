package com.kreitek.editor.formatos;

import java.util.ArrayList;

public class FormatoTextoFactory implements FormatoFactory {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    @Override
    public Formato showDocumentLines(ArrayList<String> lines) {
        if (lines.size() > 0){
            setTextColor(TEXT_YELLOW);
            printLnToConsole("START DOCUMENT ==>");
            for (int index = 0; index < lines.size(); index++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[");
                stringBuilder.append(index);
                stringBuilder.append("] ");
                stringBuilder.append(lines.get(index));
                printLnToConsole(stringBuilder.toString());
            }
            printLnToConsole("<== END DOCUMENT");
            setTextColor(TEXT_RESET);
        }
        return null;
    }

    private void setTextColor(String color) {
        System.out.println(color);
    }

    private void printLnToConsole(String message) {
        System.out.println(message);
    }

}
