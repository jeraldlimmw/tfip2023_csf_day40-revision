package ibf2022.batch2.csf.backend.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.repositories.ImageRepository;

@Service
public class UploadService {

    @Autowired
    private ImageRepository imgRepo;
    
    public List<String> UploadZip(MultipartFile file) throws IOException {

        List<String> urls = new LinkedList<>();
        
        //convert MultipartFile to File
        ZipInputStream zis = new ZipInputStream(file.getInputStream());
        ZipEntry zipEntry = zis.getNextEntry();
        
        while (zipEntry != null) {
            if (!zipEntry.isDirectory()) {
                String filename = zipEntry.getName();
                System.out.println(filename);
                long filesize = zipEntry.getSize();
                String[] nameArr = filename.split("[.]");
                String contentType = "/image/" + nameArr[nameArr.length-1];
                
                File image = new File(filename);
                try (FileOutputStream fos = new FileOutputStream(image)) {
                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
                urls.add((String) imgRepo.upload(filename, filesize, contentType, image));
                System.out.println(">>> urls: " + urls);
            }
            zis.closeEntry();
            zipEntry = zis.getNextEntry();
        }
        zis.close();
         
        return urls;
    }

}
