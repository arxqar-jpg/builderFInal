package kz.smartcafe.facade.console;

import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine();
    }

    public int readNumber(String prompt, int max) {
        while (true) {
            String line = readLine(prompt);
            if (line == null) {
                return -1;
            }

            try {
                int number = Integer.parseInt(line.trim());
                if (number >= 0 && number <= max) {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number");
                continue;
            }

            System.out.println("Enter a number between 0 and " + max);
        }
    }
}
