package com.jane.portfolio.service;

import java.util.List;
import java.util.Optional;

import com.jane.portfolio.domain.StudyVO;

public interface StudyService {
    public void insert(StudyVO studyVO);

    public List<StudyVO> findAll();

    public Optional<StudyVO> getStudyBy(Long id);

    
}
