package ls.mocks;

import java.util.ArrayList;
import java.util.List;

import ls.interfaces.IPrinter;

public class PrinterMock implements IPrinter {
    public List<String> printParam;

    public void reset() {
        this.printParam = new ArrayList<String>();
    }

    public void print(String str) {
        this.printParam.add(str);
    }
}