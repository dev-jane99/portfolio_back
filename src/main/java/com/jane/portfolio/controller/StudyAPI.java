package com.jane.portfolio.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jane.portfolio.domain.ImageVO;
import com.jane.portfolio.domain.StudyVO;
import com.jane.portfolio.service.StudyService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study")
public class StudyAPI {

    private final StudyService studyService;

    private static final String UPLOAD_DIR = "uploads/";  // 로컬 경로 (src/main/resources/static/uploads 추천)

    // ✅ 등록
    @PostMapping
    public ResponseEntity<String> createStudy(
        @RequestPart("data") StudyVO studyVO,
        @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) throws IOException {

        if (images != null && !images.isEmpty()) {
            List<ImageVO> imageList = saveImages(images);
            studyVO.setImages(imageList);
        }

        studyService.insert(studyVO);
        return ResponseEntity.ok("등록 완료");
    }

    // ✅ 전체 조회
    @GetMapping
    public ResponseEntity<List<StudyVO>> getAllStudies() {
        return ResponseEntity.ok(studyService.findAll());
    }

    // ✅ 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<StudyVO> getStudyById(@PathVariable Long id) {
        Optional<StudyVO> study = studyService.getStudyById(id);
        return study.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ 키워드 검색
    @GetMapping("/search")
    public ResponseEntity<List<StudyVO>> searchByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(studyService.getStudyByKeyword(keyword));
    }

    // ✅ 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudy(
        @PathVariable Long id,
        @RequestPart("data") StudyVO studyVO,
        @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) throws IOException {

        studyVO.setId(id);

        if (images != null && !images.isEmpty()) {
            List<ImageVO> imageList = saveImages(images);
            studyVO.setImages(imageList);
        }

        studyService.update(studyVO);
        return ResponseEntity.ok("수정 완료");
    }

    // ✅ 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudy(@PathVariable Long id) {
        studyService.delete(id);
        return ResponseEntity.ok("삭제 완료");
    }

    // ✅ 이미지 저장 로직 (로컬 디스크에 저장 + URL 생성)
    private List<ImageVO> saveImages(List<MultipartFile> images) throws IOException {
        List<ImageVO> imageList = new ArrayList<>();

        for (MultipartFile file : images) {
            String originalFilename = file.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String uuid = UUID.randomUUID().toString();
            String savedName = uuid + ext;

            File target = new File(UPLOAD_DIR + savedName);
            file.transferTo(target);

            String imageUrl = "/uploads/" + savedName; // 프론트에서 접근할 URL
            ImageVO imageVO = new ImageVO();
            imageVO.setImageUrl(imageUrl);
            imageList.add(imageVO);
        }

        return imageList;
    }
}
