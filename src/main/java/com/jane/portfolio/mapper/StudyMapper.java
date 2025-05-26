package com.jane.portfolio.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.jane.portfolio.domain.ImageVO;
import com.jane.portfolio.domain.StudyVO;

@Mapper
public interface StudyMapper {

    //  insert 등록
    public void insert(StudyVO studyVO);

    //  Update 수정하기
    public void update(StudyVO studyVO);

    //  delete 삭제하기
    public void delete(Long id);

    // find all 전체 조회
    public List<StudyVO> findAll();

    //  get by id 아이디로 특정 포스팅 조회
    public Optional<StudyVO> getStudyById(Long id);

    // get by keyword 키워드로 조회하기
    public List<StudyVO> getStudyByKeyword(String keyword);

     // 사진 등록
    void insertImage(ImageVO imageVO);

    // 사진 조회
    List<ImageVO> findImagesByStudyId(Long studyId);

    // 사진 삭제
    void deleteImagesByStudyId(Long studyId);
}
