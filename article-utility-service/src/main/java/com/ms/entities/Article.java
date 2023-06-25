package com.ms.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "articles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Article {
    @Id
    private String id;
    private Integer author;
}