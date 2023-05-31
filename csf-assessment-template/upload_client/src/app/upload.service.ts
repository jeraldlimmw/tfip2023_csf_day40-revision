import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class UploadService{

    http = inject(HttpClient)

    upload(name: string, title: string, comments: string, file: File): Observable<any> {
        const formData = new FormData()
        formData.set('name', name)
        formData.set('title', title)
        !!comments ? formData.set('comments', comments) : formData.set('comments', '')
        formData.set('file', file)
        
        return this.http.post<any>('/upload', formData)
    }
}