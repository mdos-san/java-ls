package ls;

import java.io.File;
import ls.interfaces.IFileFactory;

public class FileFactory implements IFileFactory {
    public File getFile(String pathname) {
        return new File(pathname);
    }
}