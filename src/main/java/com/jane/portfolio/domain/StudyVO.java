package com.jane.portfolio.domain;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class StudyVO {
    private Long id;             
    private String title;        
    private String content;      
    private String keywords;   
    private Timestamp createdAt;
    private List<ImageVO> images;  
}
