package ibf2022.batch2.csf.backend.repositories;

import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.csf.backend.models.Archive;
import ibf2022.batch2.csf.backend.models.Bundle;

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
	/* 
	db.archives.find({bundleId: bundleId})
	*/
	public Object getBundleByBundleId(/* any number of parameters here */ String bundleId) {
		Query query = Query.query(Criteria.where("bundleId").is(bundleId));
		return mongoTemplate.findOne(query, Document.class, "archives");
	}

	//TODO: Task 6
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	/*
	db.archives.aggregate([
		{$project: {_id: 0, bundleId: 1, title: 1, date:1}}
	])
	*/
	
	public Object getBundles(/* any number of parameters here */) {
		ProjectionOperation pOp = Aggregation.project("bundleId", "title", "date")
				.andExclude("_id");

		Aggregation pipeline = Aggregation.newAggregation(pOp);
		AggregationResults<Bundle> results = mongoTemplate.aggregate(
				pipeline, "archives", Bundle.class);
		
		return results.getMappedResults();
	}


}
