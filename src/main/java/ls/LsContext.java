package ls;

import java.util.ArrayList;
import java.util.List;

public class LsContext {
    public List<String> fileNames = new ArrayList<String>();
    public Boolean recursive = false;
    public Boolean hidden = false;
    public Boolean list = false;
    public Boolean reverse = false;
}