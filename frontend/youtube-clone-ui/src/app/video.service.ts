import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import {UploadVideoResponse} from "./upload-video/UploadVideoResponse";


@Injectable({
  providedIn: 'root'
})
export class VideoService {

  private _url = "http://localhost:8080/api/videos";

  constructor(private httpClient: HttpClient) { }

  uploadVideo(file: File):Observable<UploadVideoResponse>{
      //HTTP Post Call to Upload The Video
      const formData = new FormData()
      formData.append('file', file, file.name)
      return this.httpClient.post<UploadVideoResponse>(this._url, formData);
  }
}
