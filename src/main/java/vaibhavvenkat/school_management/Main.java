package vaibhavvenkat.school_management;

public class Main {
    public static void main(String[] args) {
        Operations opr = new Operations();
        while (true) {
            InputReader rdr = new InputReader();
            System.out.println();
            Integer in = rdr.getIntInput("""
                    Enter a choice:
                    1 (Create a student)
                    2 (Remove a student)
                    3 (Get all students)
                    4 (Change a student's grade)
                    5 (Change a student's lunch money)
                    6 (Get a student by their id)
                    EXIT (Exit the program)
                    >\s"""
            );
            System.out.println();
            if (in == null) {
                System.err.println("Enter a number");
                continue;
            }
            Options.OPT opt = new Options().getOpt(in);
            if (opt == Options.OPT.INVALID) {
                System.err.println("Invalid option");
                continue;
            }
            opr.getOperation(opt);
        }
    }
}