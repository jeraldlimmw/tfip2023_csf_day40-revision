package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.bson.Document;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Archive;
import ibf2022.batch2.csf.backend.models.Bundle;
import ibf2022.batch2.csf.backend.repositories.ArchiveRepository;
import ibf2022.batch2.csf.backend.services.UploadService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

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
		
		new DateTime();
		Archive a = new Archive(DateTime.now().toLocalDate().toString(), title, 
				name, comments.trim());
		a.setUrls(uploadSvc.UploadZip(file));

		a.setBundleId((String) archiveRepo.recordBundle(a));
		if (a.getBundleId().isEmpty()) {
			return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(Json.createObjectBuilder()
					.add("bundleId", a.getBundleId())
					.build().toString());
	}

	// TODO: Task 5
	@GetMapping(path="/bundle/{bundleId}")
	@ResponseBody
	public ResponseEntity<String> getBundle(@PathVariable String bundleId) {
		
		Document d = (Document) archiveRepo.getBundleByBundleId(bundleId);
		Archive a = Archive.create(d);

		if (Objects.isNull(a)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Json.createObjectBuilder()
						.add("error", "BundleId not found")
						.build().toString());
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(a.toJson().toString());
	}

	// TODO: Task 6
	@GetMapping(path="/bundles")
	@ResponseBody
	public ResponseEntity<String> getBundles() {
		
		List<Bundle> bundles = (List<Bundle>) archiveRepo.getBundles();

		JsonArrayBuilder ab = Json.createArrayBuilder();
		for (Bundle b : bundles) {
			ab.add(b.toJsonOB());
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(ab.build().toString());
	}
}
