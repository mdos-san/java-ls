package ls.mocks;

import java.io.File;

public class FileMock extends File {
    private static final long serialVersionUID = 301077366599181567L;
    private String path;
    private boolean readable;
    private boolean writable;
    private boolean executable;
    private boolean directory;
    private boolean real;
    private boolean haveFiles;

    public FileMock(String path) {
        super(path);
        this.path = path;
        this.readable = false;
        this.writable = false;
        this.executable = false;
        this.directory = false;
        this.real = true;
    }

    public FileMock(String path, boolean dir, boolean haveFiles) {
        super(path);
        this.path = path;
        this.readable = false;
        this.writable = false;
        this.executable = false;
        this.directory = dir;
        this.real = true;
        this.haveFiles = haveFiles;
    }

    public String getName() {
        return path;
    }

    public boolean setReadable(boolean v) {
        this.readable = v;
        return v;
    }

    public boolean setWritable(boolean v) {
        this.writable = v;
        return v;
    }

    public boolean setExecutable(boolean v) {
        this.executable = v;
        return v;
    }

    public boolean canRead() {
        return this.readable;
    }

    public boolean canWrite() {
        return this.writable;
    }

    public boolean canExecute() {
        return this.executable;
    }

    public boolean isDirectory() {
        return this.directory;
    }

    public FileMock[] listFiles() {
        FileMock[] files;

        if (haveFiles) {
            files = new FileMock[2];
            files[0] = new FileMock("File1");
            files[1] = new FileMock("Folder1", true, false);
        } else {
            files = new FileMock[0];
        }
        return files;
    }

    public boolean exists() {
        return this.real;
    }
}