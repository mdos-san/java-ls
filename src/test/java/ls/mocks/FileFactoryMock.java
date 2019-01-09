package ls.mocks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ls.interfaces.IFileFactory;

public class FileFactoryMock implements IFileFactory {
    public List<String> getFileParam;

    public void reset() {
        this.getFileParam = new ArrayList<String>();
    }

    public File getFile(String path) {
        this.getFileParam.add(path);
        return new FileMock(path);
    }

    public File getDirectory(String path, boolean withFiles) {
        return new FileMock(path, true, withFiles);
    }
}