package com.example.youtubeclone.service;

import com.example.youtubeclone.dto.UploadVideoResponse;
import com.example.youtubeclone.dto.VideoDto;
import com.example.youtubeclone.model.Video;
import com.example.youtubeclone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
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
     *
     * @return
     */
    public UploadVideoResponse uploadVideo(MultipartFile multipartFile){
        String videoUrl= s3Service.uploadFile(multipartFile);

       var video = new Video();
       video.setVideoUrl(videoUrl);

        var savedVideo = videoRepository.save(video);
        return new UploadVideoResponse(savedVideo.getId(),savedVideo.getVideoUrl());
    }


    public VideoDto editVideo(VideoDto videoDto) {
        //Find the video by Id
       var saveVideo = getVideoById(videoDto.getId());

        //Map the videoDTO fields to Video
        saveVideo.setTitle(videoDto.getTitle());
        saveVideo.setDescription(videoDto.getDescription());
        saveVideo.setTags(videoDto.getTags());
        saveVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
        saveVideo.setVideoStatus(videoDto.getVideoStatus());

        //save the video to the database
        videoRepository.save(saveVideo);
        return videoDto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        var saveVideo = getVideoById(videoId);
        String thumbNailUrl = s3Service.uploadFile(file);
        saveVideo.setThumbnailUrl(thumbNailUrl);
        videoRepository.save(saveVideo);
        return thumbNailUrl;
    }

    Video getVideoById(String videoId){
        return videoRepository.findById(videoId)
                .orElseThrow(()-> new IllegalArgumentException("Can't find the value by Id" + videoId));
    }
}
