import { Component, OnInit, inject } from '@angular/core';
import { Archive } from '../models';
import { ArchiveService } from '../archive.service';
import { lastValueFrom } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view2',
  templateUrl: './view2.component.html',
  styleUrls: ['./view2.component.css']
})
export class View2Component implements OnInit{

  archiveSvc = inject(ArchiveService)
  activatedRoute = inject(ActivatedRoute)

  archive!: Archive

  ngOnInit(): void {
    const bundleId = this.activatedRoute.snapshot.params['bundleId']
    
    lastValueFrom(this.archiveSvc.getArchiveByBundleId(bundleId))
        .then(result => { 
          this.archive = result
          console.info(this.archive)
        })
        .catch(err => alert(err.error))
  }
}
