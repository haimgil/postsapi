package com.steps.postsapi.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IdsHelper {

    public static List<String> commaSeparatedToList(String commaSeparatedStr){
        return Arrays.stream(commaSeparatedStr.split(","))
                .collect(Collectors.toList());
    }

    public static String listToCommaSeparated(List<String> strList){
        return String.join(",",strList);
    }
}
