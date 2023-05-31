import { Component, OnInit, inject } from '@angular/core';
import { Bundle } from '../models';
import { ArchiveService } from '../archive.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-view0',
  templateUrl: './view0.component.html',
  styleUrls: ['./view0.component.css']
})
export class View0Component implements OnInit{
  bundles$!: Observable<Bundle[]>

  archiveSvc = inject(ArchiveService)

  ngOnInit(): void {
      this.bundles$ = this.archiveSvc.getBundles()
  }

}
