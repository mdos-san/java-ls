package ls;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import ls.mocks.*;

public class LsOperatorTest {
    LsOperator operator;
    LsContext context1;
    LsContext noArgs;
    LsContext detail;
    LsContext reverse;
    LsContext recur;
    FileFactoryMock ff;
    PrinterMock printer = new PrinterMock();

    public LsOperatorTest() {
        this.noArgs = new LsContext();
        this.context1 = new LsContext();
        this.context1.fileNames = Arrays.asList("file1", "file2");
        this.detail = new LsContext();
        this.detail.list = true;
        this.reverse = new LsContext();
        this.reverse.reverse = true;
        this.recur = new LsContext();
        this.recur.recursive = true;
        this.ff = new FileFactoryMock();
    }

    @Before
    public void Init() {
        this.ff.reset();
        this.printer.reset();
        this.operator = new LsOperator(this.context1, this.ff, this.printer);
    }

    @Test
    public void EachFileIsReaded() {
        this.operator.run();
        assertEquals("file1", this.ff.getFileParam.get(0));
        assertEquals("file2", this.ff.getFileParam.get(1));
        assertEquals(2, this.ff.getFileParam.size());
    }

    @Test
    public void RunTestCase1() {
        this.operator.run();
        assertEquals("file1 file2 ", String.join("", this.printer.printParam));
    }

    @Test
    public void DisplayDetailTestCase1() {
        this.operator = new LsOperator(this.detail, this.ff, this.printer);
        File f = this.ff.getFile("test");
        this.operator.displayDetails(f);
        assertEquals("---- 0 Thu Jan 01 01:00:00 CET 1970 test\n", String.join("", this.printer.printParam));
    }

    @Test
    public void DisplayDetailTestCase2() {
        this.operator = new LsOperator(this.detail, this.ff, this.printer);
        File f = this.ff.getFile("test");
        f.setReadable(true);
        f.setWritable(true);
        f.setExecutable(true);
        this.operator.displayDetails(f);
        assertEquals("-rwx 0 Thu Jan 01 01:00:00 CET 1970 test\n", String.join("", this.printer.printParam));
    }

    @Test
    public void DisplayDetailTestCase3() {
        this.operator = new LsOperator(this.detail, this.ff, this.printer);
        File f = this.ff.getDirectory("test", false);
        this.operator.displayDetails(f);
        assertEquals("d--- 0 Thu Jan 01 01:00:00 CET 1970 test\n", String.join("", this.printer.printParam));
    }

    @Test
    public void ReadDirTestCase1() {
        this.operator = new LsOperator(this.noArgs, this.ff, this.printer);
        File dir = this.ff.getDirectory("dir", true);
        this.operator.readDir(dir);
        assertEquals("\ndir:\nFile1 Folder1 ", String.join("", this.printer.printParam));
    }

    @Test
    public void ReadDirTestCase2() {
        this.operator = new LsOperator(this.reverse, this.ff, this.printer);
        File dir = this.ff.getDirectory("dir", true);
        this.operator.readDir(dir);
        assertEquals("\ndir:\nFolder1 File1 ", String.join("", this.printer.printParam));
    }

    @Test
    public void ReadDirTestCase3() {
        this.operator = new LsOperator(this.recur, this.ff, this.printer);
        File dir = this.ff.getDirectory("dir", true);
        this.operator.readDir(dir);
        assertEquals("\ndir:\nFile1 Folder1 \nFolder1:\n", String.join("", this.printer.printParam));
    }
}