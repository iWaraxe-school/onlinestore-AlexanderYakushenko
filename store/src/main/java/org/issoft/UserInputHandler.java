package org.issoft;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserInputHandler {
    private Scanner scanner;

    public UserInputHandler() {
        this.scanner = new Scanner(new InputStreamReader(System.in));
    }

    public String getNextInput() {
        return scanner.nextLine();
    }
}
