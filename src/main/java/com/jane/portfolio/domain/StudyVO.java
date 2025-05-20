package com.jane.portfolio.domain;

import java.security.Timestamp;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class StudyVO {
    private Long id;             
    private String title;        
    private String content;      
    private String keywords;     
    private String imageUrl;     
    private Timestamp createdAt;  
}
