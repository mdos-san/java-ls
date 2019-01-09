package ls;

import ls.interfaces.IPrinter;

public class Printer implements IPrinter {
    public void print(String str) {
        System.out.print(str);
    }
}