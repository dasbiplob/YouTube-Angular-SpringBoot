package com.example.youtubeclone.controller;

import com.example.youtubeclone.dto.UploadVideoResponse;
import com.example.youtubeclone.dto.VideoDto;
import com.example.youtubeclone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestParam ("file") MultipartFile file){
        return videoService.uploadVideo(file);
    }

    @PostMapping("/thumbnails")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail(@RequestParam ("file") MultipartFile file,
                                @RequestParam ("videoId") String videoId){
        return videoService.uploadThumbnail(file, videoId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetaData(@RequestBody VideoDto videoDto){
        return videoService.editVideo(videoDto);
    }

    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto getVideoDetails(@PathVariable String videoId){
        return videoService.getVideoDetails(videoId);
    }

}
