package vaibhavvenkat.school_management;

public class Options {
    public enum OPT {
        CREATE,
        DELETE,
        GET_ALL,
        CHANGE_GRADE,
        CHANGE_MONEY,
        GET_BY_ID,
        INVALID;
    }
    public OPT getOpt(int val) {
        return switch (val) {
            case 1 -> OPT.CREATE;
            case 2 -> OPT.DELETE;
            case 3 -> OPT.GET_ALL;
            case 4 -> OPT.CHANGE_GRADE;
            case 5 -> OPT.CHANGE_MONEY;
            case 6 -> OPT.GET_BY_ID;
            default -> OPT.INVALID;
        };
    }
}
