package com.matheusqz.blogAPI.models;

import java.util.List;

public record RequestPost(String title,
        String content,
        String category,
        List<String> tags) {

}
