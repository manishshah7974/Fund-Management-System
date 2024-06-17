package com.fintech.fundManagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import lombok.*;
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Investor {
    @Id
    private String id;
    private String name;
    private Map<String, Investment> investments; // Maps fund ID to Investment
}
