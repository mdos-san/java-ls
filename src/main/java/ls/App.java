package ls;

public class App {
    public static void main(String[] args) {
        LsParser parser = new LsParser();
        LsContext context = parser.parse(args);
        LsOperator operator = new LsOperator(context, new FileFactory(), new Printer());
        operator.run();
        System.out.print("\n");
    }
}
