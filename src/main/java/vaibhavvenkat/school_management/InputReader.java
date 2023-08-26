package vaibhavvenkat.school_management;
import java.io.IOException;
import java.util.Scanner;

public class InputReader {
    public String getInput(String prompt) {
        Scanner scnr = new Scanner(System.in);
        System.out.print(prompt);
        String in = scnr.nextLine();
        if (in.equals("EXIT")) {
            System.exit(0);
        }
        return in;
    }
    public Integer getIntInput(String prompt) {
        Scanner scnr = new Scanner(System.in);
        System.out.print(prompt);
        try {
            return scnr.nextInt();
        } catch (Exception e) {
            if (scnr.nextLine().equals("EXIT")) {
                System.exit(0);
            }
            return null;
        }
    }

}
