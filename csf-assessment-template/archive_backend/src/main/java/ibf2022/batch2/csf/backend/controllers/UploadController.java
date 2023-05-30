package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Archive;
import ibf2022.batch2.csf.backend.repositories.ArchiveRepository;
import ibf2022.batch2.csf.backend.services.UploadService;
import jakarta.json.Json;

@Controller
@RequestMapping
@CrossOrigin(origins="*")
public class UploadController {

	@Autowired
	private UploadService uploadSvc;

	@Autowired
	private ArchiveRepository archiveRepo;

	// TODO: Task 2, Task 3, Task 4
	@PostMapping(path = "/upload", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public ResponseEntity<String> uploadZip (@RequestPart String name
			, @RequestPart String title, @RequestPart String comments
			, @RequestPart MultipartFile file) throws IOException {
		
		System.out.printf(">>>> in Controller.uploadZip(), name: " + name);

		new DateTime();
		Archive a = new Archive(DateTime.now(), title, name, comments);
		a.setUrls(uploadSvc.UploadZip(file));

		a.setBundleId((String) archiveRepo.recordBundle(a));
		if (a.getBundleId().length() < 1) {
			return ResponseEntity.status(HttpStatusCode.valueOf(500))
					.contentType(MediaType.APPLICATION_JSON)
					.build();
		}
		return ResponseEntity.status(HttpStatus.CREATED)
		.contentType(MediaType.APPLICATION_JSON)
		.body(Json.createObjectBuilder()
			.add("bundleId", a.getBundleId())
			.build().toString());
	}

	// TODO: Task 5
	

	// TODO: Task 6

}
