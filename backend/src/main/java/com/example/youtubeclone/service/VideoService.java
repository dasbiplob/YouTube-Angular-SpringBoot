package com.example.youtubeclone.service;

import com.example.youtubeclone.model.Video;
import com.example.youtubeclone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService{

    private final S3Service s3Service;
    private final VideoRepository videoRepository;
    /**
     * Will call the S3Service from this service class
     * i.e: Upload the file to AWS S3
     * Save Videos Data to Mongodb                    Database
     */
    public void uploadVideo(MultipartFile multipartFile){
        String videoUrl= s3Service.uploadFile(multipartFile);

       var video = new Video();
       video.setVideoUrl(videoUrl);

        videoRepository.save(video);
    }
}



