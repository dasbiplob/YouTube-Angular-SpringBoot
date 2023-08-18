import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import {UploadVideoResponse} from "./upload-video/UploadVideoResponse";


@Injectable({
  providedIn: 'root'
})
export class VideoService {

  private _videoUrl = "http://localhost:8080/api/videos";
  private _thumbnailUrl = "http://localhost:8080/api/videos/thumbnails";

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
}
