package com.jane.portfolio.service;

import java.util.List;
import java.util.Optional;

import com.jane.portfolio.domain.StudyVO;

public interface StudyService {
    public void insert(StudyVO studyVO);

    public List<StudyVO> findAll();

    public Optional<StudyVO> getStudyById(Long id);

    public void update(StudyVO studyVO);

    public void delete(Long id);

    public List<StudyVO> getStudyByKeyword(String keyword);

}
