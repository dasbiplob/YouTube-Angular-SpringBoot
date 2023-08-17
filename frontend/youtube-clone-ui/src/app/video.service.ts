import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  private _url = "http://localhost:8080/api/videos";

  constructor(private httpClient: HttpClient) { }

  uploadVideo(file: File){
      //HTTP Post Call to Upload The Video
      const formData = new FormData()
      formData.append('file', file, file.name)
      return this.httpClient.post(this._url, formData);
  }
}
