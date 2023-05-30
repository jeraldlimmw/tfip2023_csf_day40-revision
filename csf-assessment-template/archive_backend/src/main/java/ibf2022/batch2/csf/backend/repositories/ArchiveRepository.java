package ibf2022.batch2.csf.backend.repositories;

import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.csf.backend.models.Archive;

@Repository
public class ArchiveRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	//TODO: Task 4
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	/* 
	db.archive.insert({
		bundleId: "id",
		date: date,
    	title: "title",
    	name: "name",
     	comments: "comments",
    	urls: ["url1", "url2"]
	})
	*/
	public Object recordBundle(Archive a) {
		Document doc = new Document();
		doc.put("bundleId", UUID.randomUUID().toString().substring(0, 8));
		doc.put("date", a.getDate().toString());
		doc.put("title", a.getTitle());
		doc.put("name", a.getName());
		doc.put("comments", a.getComments());
		doc.put("urls", a.getUrls().toArray());
		mongoTemplate.insert(doc, "archives");

		return doc.get("bundleId");
	}

	//TODO: Task 5
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundleByBundleId(/* any number of parameters here */) {
		return null;
	}

	//TODO: Task 6
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundles(/* any number of parameters here */) {
		return null;
	}


}
