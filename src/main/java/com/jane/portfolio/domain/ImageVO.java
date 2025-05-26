package com.jane.portfolio.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ImageVO {
    private Long id;
    private Long studyId;    
    private String imageUrl; 
}
