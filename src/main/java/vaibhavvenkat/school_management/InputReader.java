package vaibhavvenkat.school_management;
import java.io.IOException;
import java.util.Scanner;

public class InputReader {
    public String getInput(String prompt) {
        Scanner scnr = new Scanner(System.in);
        System.out.print(prompt);
        return scnr.nextLine();
    }
    public Integer getIntInput(String prompt) {
        Scanner scnr = new Scanner(System.in);
        System.out.print(prompt);
        try {
            return scnr.nextInt();
        } catch (Exception e) {
            return null;
        }
    }

}
