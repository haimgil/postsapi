package com.steps.postsapi.helpers;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IdsHelper {

    public static List<Long> commaSeparatedToList(String commaSeparatedStr){
        return Arrays.stream(commaSeparatedStr.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    public static String listToCommaSeparated(List<Long> idsList){
        return StringUtils.collectionToCommaDelimitedString(idsList);
    }
}
