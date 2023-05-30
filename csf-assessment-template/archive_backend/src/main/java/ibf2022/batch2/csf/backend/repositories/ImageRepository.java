package ibf2022.batch2.csf.backend.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

@Repository
public class ImageRepository {

	@Autowired
   	private AmazonS3 s3;

	//TODO: Task 3
	// You are free to change the parameter and the return type
	// Do not change the method's name
	public Object upload(/* any number of parameters here */
			String filename, long filesize, String contentType
			, File image) throws IOException {		

		Map<String, String> userData = new HashMap<>();
		userData.put("filename", filename);
		userData.put("upload-date", (new Date()).toString());
	
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);
		metadata.setContentLength(filesize);
		metadata.setUserMetadata(userData);
		
		String key = "photos/%s".formatted(UUID.randomUUID().toString().substring(0, 8));

		InputStream is = new FileInputStream(image);
	
		PutObjectRequest putReq = new PutObjectRequest("tfip-projects", 
				key, is, metadata);
		putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);
	
		PutObjectResult result = s3.putObject(putReq);
		System.out.printf(">>> result: %s\n", result);
	
		return s3.getUrl("tfip-projects", key).toString();
	}
}
