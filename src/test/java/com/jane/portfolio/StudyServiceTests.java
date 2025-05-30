package com.jane.portfolio;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.jane.portfolio.domain.StudyVO;
import com.jane.portfolio.service.StudyService;

@SpringBootTest
@Transactional
@Rollback
class StudyServiceTests {

    @Autowired
    private StudyService studyService;

    @Test
    void testInsertStudy() {
        StudyVO study = new StudyVO();
        study.setTitle("테스트 제목");
        study.setContent("테스트 내용");
        study.setKeywords("테스트, 스터디");

        studyService.insert(study);

        assertNotNull(study.getId());
    }

}
 
