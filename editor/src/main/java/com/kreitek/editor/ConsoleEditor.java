package com.kreitek.editor;

import com.kreitek.editor.commands.CommandFactory;
import com.kreitek.editor.commands.CommandFactoryCaretaker;
import com.kreitek.editor.commands.Memento;
import com.kreitek.editor.commands.UndoCommand;
import com.kreitek.editor.formatos.FormatFactoryProducer;
import com.kreitek.editor.formatos.Formato;
import com.kreitek.editor.formatos.FormatoFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleEditor implements Editor {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    private final CommandFactory commandFactory = new CommandFactory();
    private ArrayList<String> documentLines = new ArrayList<>();
    CommandFactoryCaretaker caretaker = CommandFactoryCaretaker.getInstance();

    @Override
    public void run(String[] args) {
        boolean exit = false;
        while (!exit) {
            String commandLine = waitForNewCommand();
            try {
                Command command = commandFactory.getCommand(commandLine);
                if (!(command instanceof UndoCommand)){
                    caretaker.add(saveStateToMemento());
                    command.execute(documentLines);
                }else{
                    command.execute(documentLines);
                }
            } catch (BadCommandException e) {
                printErrorToConsole("Bad command");
            } catch (ExitException e) {
                exit = true;
            }
            try{
                FormatFactoryProducer formatFactoryProducer = new FormatFactoryProducer();
                FormatoFactory factory = formatFactoryProducer.getFactory(args[0]);
                Formato formato = factory.showDocumentLines(documentLines);
            }catch (BadFactoryException e){
                printErrorToConsole("Invalid command");
            }
            showHelp();
        }
    }

    private String waitForNewCommand() {
        printToConsole("Enter a command : ");
        Scanner scanner = new Scanner(System. in);
        return scanner.nextLine();
    }

    private void showHelp() {
        printLnToConsole("To add new line -> a \"your text\"");
        printLnToConsole("To update line  -> u [line number] \"your text\"");
        printLnToConsole("To delete line  -> d [line number]");
        printLnToConsole("To restore  -> undo");
    }

    private void printErrorToConsole(String message) {
        setTextColor(TEXT_RED);
        printToConsole(message);
        setTextColor(TEXT_RESET);
    }

    private void setTextColor(String color) {
        System.out.println(color);
    }

    private void printLnToConsole(String message) {
        System.out.println(message);
    }

    private void printToConsole(String message) {
        System.out.print(message);
    }

    public Memento saveStateToMemento(){
        Memento memento = new Memento();
        memento.setState(this.documentLines);
        return memento;
    }

}
