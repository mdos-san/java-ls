package ls;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LsParser {
    public LsContext parse(String[] args) {
        LsContext context = new LsContext();
        List<String> l = Arrays.asList(args);
        context.fileNames = l.stream().filter(x -> x.length() > 0 && x.charAt(0) != '-').collect(Collectors.toList());
        List<String> options = l.stream().filter(x -> x.length() > 0 && x.charAt(0) == '-').collect(Collectors.toList());

        for (String option : options) {
            loadOption(context, option);
        }
        if (context.reverse)
            context.fileNames.sort(Comparator.reverseOrder());
        else
            context.fileNames.sort(Comparator.naturalOrder());
        if (context.recursive && context.fileNames.size() == 0)
            context.fileNames.add(".");
        return context;
    }

    public void loadOption(LsContext context, String option) {
        if (option.contains("a"))
            context.hidden = true;
        if (option.contains("R"))
            context.recursive = true;
        if (option.contains("r"))
            context.reverse = true;
        if (option.contains("l"))
            context.list = true;
    }
}
