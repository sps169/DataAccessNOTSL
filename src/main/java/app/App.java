package app;

public class App {
    /**
     * Entrypoint for the app. Args are not used
     * @param args
     */
    public static void main (String[] args) {
        DataAccessNOSL run = DataAccessNOSL.getInstance();
        run.insertDB();
        run.printDB();
        run.deleteDB();
    }
}
