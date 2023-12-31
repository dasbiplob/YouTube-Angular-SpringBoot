import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import {UploadVideoResponse} from "./upload-video/UploadVideoResponse";
import {VideoDto} from "./video-dto";


@Injectable({
  providedIn: 'root'
})
export class VideoService {

  private _videoUrl = "http://localhost:8080/api/videos";
  private _thumbnailUrl = "http://localhost:8080/api/videos/thumbnails";
  private _videoDetailsUrl = "http://localhost:8080/api/videos/";

  constructor(private httpClient: HttpClient) { }

  uploadVideo(file: File):Observable<UploadVideoResponse>{
      //HTTP Post Call to Upload The Video
      const formData = new FormData()
      formData.append('file', file, file.name)
      return this.httpClient.post<UploadVideoResponse>(this._videoUrl, formData);
  }

  uploadThumbnail(file: File, videoId: string):Observable<string>{
        //HTTP Post Call to Upload The Thumbnail
        const formData = new FormData()
        formData.append('file', file, file.name)
        formData.append('videoId', videoId)
        return this.httpClient.post(this._thumbnailUrl, formData,{
        responseType:'text'
        })
    }

    getVideoDetails(videoId: string): Observable<VideoDto>{
    return this.httpClient.get<VideoDto>(this._videoDetailsUrl + videoId);
    }

    saveVideo(videoMetaData: VideoDto): Observable<VideoDto>{
     return this.httpClient.put<VideoDto>(this._videoUrl, videoMetaData);
    }
}
