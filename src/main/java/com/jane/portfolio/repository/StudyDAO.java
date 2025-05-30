package com.jane.portfolio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jane.portfolio.domain.ImageVO;
import com.jane.portfolio.domain.StudyVO;
import com.jane.portfolio.mapper.StudyMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudyDAO {

    private final StudyMapper studyMapper;

    // 등록 new post
    public void insert(StudyVO studyVO) {
        studyMapper.insert(studyVO);
        if (studyVO.getImages() != null && !studyVO.getImages().isEmpty()) {
            for (ImageVO image : studyVO.getImages()) {
                image.setStudyId(studyVO.getId()); 
                studyMapper.insertImage(image);
            }
        }
    }

    // 전체 조회 find all
    public List<StudyVO> findAll() {
        return studyMapper.findAll();
    }

    // 아이디로 조회 find by id
    public Optional<StudyVO> getStudyById(Long id) {
        return studyMapper.getStudyById(id);
    }

    // 수정 update
    public void update(StudyVO studyVO) {
        studyMapper.update(studyVO);
        
        studyMapper.deleteImagesByStudyId(studyVO.getId());

        if (studyVO.getImages() != null && !studyVO.getImages().isEmpty()) {
            for (ImageVO image : studyVO.getImages()) {
                image.setStudyId(studyVO.getId());
                studyMapper.insertImage(image);
            }
        }
    }

    // 삭제 delete
    public void delete(Long id) {
        studyMapper.delete(id);
    }

    // 키워드로 조회 search by keyword
    public List<StudyVO> getStudyByKeyword(String keyword) {
        return studyMapper.getStudyByKeyword(keyword);
    }
}
