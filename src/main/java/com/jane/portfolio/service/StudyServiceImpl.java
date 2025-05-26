package com.jane.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jane.portfolio.domain.StudyVO;
import com.jane.portfolio.repository.StudyDAO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final StudyDAO studyDAO;

    // 공부 등록
    @Override
    public void insert(StudyVO studyVO) {
        studyDAO.insert(studyVO);
    }

    // 전체 조회
    @Override
    public List<StudyVO> findAll() {
        return studyDAO.findAll();
    }

    // ID로 조회
    @Override
    public Optional<StudyVO> getStudyById(Long id) {
        return studyDAO.getStudyById(id);
    }

    // 수정
    @Override
    public void update(StudyVO studyVO) {
        studyDAO.update(studyVO);
    }

    // 삭제
    @Override
    public void delete(Long id) {
        studyDAO.delete(id);
    }

    // 키워드로 조회
    @Override
    public List<StudyVO> getStudyByKeyword(String keyword) {
        return studyDAO.getStudyByKeyword(keyword);
    }
}
