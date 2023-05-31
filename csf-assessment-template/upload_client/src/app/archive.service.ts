import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";

Injectable()
export class ArchiveService {

    http = inject(HttpClient)
    bundleId!: string

    getArchiveByBundleId(id: string) {
        return this.http.get<any>(`/bundle/${id}`)
    }

    getBundles() {
        return this.http.get<any>('/bundles')
    }
}