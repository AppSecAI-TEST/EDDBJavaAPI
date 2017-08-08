package ua.gwm.elite_dangerous.eddb_java_api.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EDDBJavaAPIUtils {

    public static final Gson GSON = new Gson();

    public static String populationToString(long population) {
        String toStr = String.valueOf(population);
        int length = toStr.length();
        if (length > 9) {
            toStr = toStr.substring(0, length-9) + "млрд.";
        } else if (length > 6) {
            toStr = toStr.substring(0, length-6) + "мил.";
        } else if (length > 3) {
            toStr = toStr.substring(0, length-3) + "тыс.";
        }
        return toStr;
    }

    public static Pair<String, Integer> getMultiWordParam(String[] args, int start_position) {
        String first_word = args[start_position];
        StringBuilder builder = new StringBuilder(first_word);
        boolean quotation = first_word.startsWith("\"");
        int to_skip = 1;
        if (quotation && first_word.endsWith("\"")) {
            return new Pair<String, Integer>(first_word.substring(1, first_word.length() - 1), to_skip);
        }
        for (int i = start_position + 1; i < args.length; i++) {
            String arg = args[i];
            if (quotation) {
                builder.append(' ').append(arg);
                to_skip++;
                if (arg.endsWith("\"")) {
                    break;
                }
            } else if (!arg.startsWith("--")) {
                builder.append(' ').append(arg);
                to_skip++;
            } else {
                break;
            }
        }
        String result = builder.toString();
        return new Pair<String, Integer>(quotation ? result.substring(1, result.length() - 1) : result, to_skip);
    }

    public static List<String> splitCSV(String csv) {
        ArrayList<String> list = new ArrayList<String>();
        StringBuilder result = new StringBuilder();
        boolean quotation = false;
        for (char ch : csv.toCharArray()) {
            if (ch == '\"') {
                quotation = !quotation;
            } else if (ch == ',' && !quotation) {
                list.add(result.toString());
                result = new StringBuilder();
            } else {
                result.append(ch);
            }
        }
        list.add(result.toString());
        return list;
    }
}
