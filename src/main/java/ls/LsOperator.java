package ls;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import ls.interfaces.*;

public class LsOperator {
    private LsContext context;
    private IFileFactory ff;
    private IPrinter printer;

    public LsOperator(LsContext context, IFileFactory ff, IPrinter printer) {
        this.context = context;
        this.ff = ff;
        this.printer = printer;
    }

    public void run() {
        for (String name : this.context.fileNames) {
            File f = this.ff.getFile(name);
            walk(f);
        }

        if (this.context.fileNames.size() == 0)
            readDir(System.getProperty("user.dir"));
    }

    private void walk(File f) {
        if (canDisplay(f)) {
            if (f.isDirectory())
                readDir(f);
            else
                readFile(f);
        }
    }

    private Boolean canDisplay(File f) {
        if (f.exists() && (!f.isHidden() || this.context.hidden))
            return true;
        return false;
    }

    private static <T> T[] reverse(T[] arr) {
        List<T> list = Arrays.asList(arr);
        Collections.reverse(list);
        return list.toArray(arr);
    }

    public void readDir(String path) {
        readDir(this.ff.getFile(path));
    }

    public void readDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            files = (this.context.reverse) ? reverse(files) : files;

            this.printer.print("\n");
            this.printer.print(dir.getPath() + ":\n");

            loop(files, (File f) -> {
                if (canDisplay(f))
                    readFile(f);
            });

            if (this.context.recursive) {
                loop(files, (File f) -> {
                    if (f.isDirectory())
                        walk(f);
                });
            }
        }
    }

    private void readFile(File f) {
        if (canDisplay(f)) {
            if (this.context.list) {
                displayDetails(f);
            } else {
                this.printer.print(f.getName());
                this.printer.print(" ");
            }
        }
    }

    public void displayDetails(File f) {
        if (f.isDirectory())
            printer.print("d");
        else
            printer.print("-");
        if (f.canRead())
            printer.print("r");
        else
            printer.print("-");
        if (f.canWrite())
            printer.print("w");
        else
            printer.print("-");
        if (f.canExecute())
            printer.print("x");
        else
            printer.print("-");
        printer.print(" ");
        printer.print(Long.toString(f.length()));
        printer.print(" ");
        printer.print(new Date(f.lastModified()).toString());
        printer.print(" ");
        printer.print(f.getName());
        printer.print("\n");
    }

    private void loop(File[] files, Consumer<File> func) {
        for (File f : files) {
            func.accept(f);
        }
    }
}
