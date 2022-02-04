package app;

public class App {
    public static void main (String[] args) {
        DataAccessNOSL run = DataAccessNOSL.getInstance();
        run.insertDB();
//        run.printDB();
//        run.deleteDB();
    }
}
