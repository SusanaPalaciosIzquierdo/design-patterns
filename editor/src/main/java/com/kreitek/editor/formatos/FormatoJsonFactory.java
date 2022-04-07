package com.kreitek.editor.formatos;

import java.util.ArrayList;

public class FormatoJsonFactory implements FormatoFactory {
    @Override
    public Formato showDocumentLines(ArrayList<String> lines) {
        if (lines.size() > 0){
            printLnToConsole("{");
            printLnToConsole(" \"doc\":[");
            for (int index = 0; index < lines.size(); index++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("  {\"line\":\"");
                stringBuilder.append(index);
                stringBuilder.append("\",\"text\":\"");
                stringBuilder.append(lines.get(index));
                stringBuilder.append("\"}");
                if(index < lines.size()-1){
                    stringBuilder.append(",");
                }
                printLnToConsole(stringBuilder.toString());
            }
            printLnToConsole(" ]");
            printLnToConsole("}");
        }
        return null;
    }

    private void printLnToConsole(String message) {
        System.out.println(message);
    }
}