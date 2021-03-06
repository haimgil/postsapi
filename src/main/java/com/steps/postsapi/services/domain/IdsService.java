package com.steps.postsapi.services.domain;

import com.steps.postsapi.persistence.Post;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class IdsService {

    public void generateUniqueId(Post post){
        post.setId(generatePositiveUniqueId());
    }

    public static List<Long> commaSeparatedToList(String commaSeparatedStr){
        return Arrays.stream(commaSeparatedStr.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    private Long generatePositiveUniqueId() {
        long id = -1;
        do {
            id = UUID.randomUUID().getMostSignificantBits();
        } while (id < 0);
        return id;
    }
}
