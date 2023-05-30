import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http'
import { Routes, RouterModule } from '@angular/router'

import { AppComponent } from './app.component';
import { View0Component } from './components/view0.component';
import { View1Component } from './components/view1.component';
import { View2Component } from './components/view2.component';
import { UploadService } from './upload.service';

const appRoutes: Routes = [
  {path: '', component: View0Component},
  {path: 'zipupload', component: View1Component},
  {path: 'saved', component: View2Component},
  {path: '**', redirectTo: '/', pathMatch:'full'} 
]

@NgModule({
  declarations: [
    AppComponent,
    View0Component,
    View1Component,
    View2Component
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, HttpClientModule
    , RouterModule.forRoot(appRoutes, {useHash : true})
  ],
  providers: [UploadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
