import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UploadService } from '../upload.service';
import { firstValueFrom, map } from 'rxjs';
import { ArchiveService } from '../archive.service';

@Component({
  selector: 'app-view1',
  templateUrl: './view1.component.html',
  styleUrls: ['./view1.component.css']
})
export class View1Component implements OnInit{

  router = inject(Router)
  fb = inject(FormBuilder)
  http = inject(HttpClient)
  uploadSvc = inject(UploadService)
  archiveSvc = inject(ArchiveService)

  @ViewChild('file')
  file!: ElementRef

  form!: FormGroup

  ngOnInit(): void {
      this.form = this.createForm()
  }

  createForm() {
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required ]),
      title: this.fb.control<string>('', [ Validators.required ]),
      comments: this.fb.control<string>(' '),
      file: this.fb.control<File | null>(null, [ Validators.required ]),
    })
  }

  upload() {
    const f: File = this.file.nativeElement.files[0]
    const data = this.form.value

    firstValueFrom(this.uploadSvc.upload(data['name'], data['title'], 
            data['comments'], f))
      .then(result => result.bundleId)
      .then(id => {
          this.form.reset()
          this.router.navigate(['/saved', id])
      })
      .catch(err => {
          alert(JSON.stringify)
      })
  }

}
