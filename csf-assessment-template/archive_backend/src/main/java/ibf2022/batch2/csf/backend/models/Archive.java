package ibf2022.batch2.csf.backend.models;

import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class Archive {
    private String bundleId;
    private String date;
    private String title;
    private String name;
    private String comments;
    private List<String> urls;
    
    public Archive() {

    }

    public Archive(String date, String title, String name, String comments) {
        this.date = date;
        this.title = title;
        this.name = name;
        this.comments = comments;
    }

    public String getBundleId() {
        return bundleId;
    }
    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public List<String> getUrls() {
        return urls;
    }
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "Archive [bundleId=" + bundleId + ", date=" + date + ", title=" + title + ", name=" + name
                + ", comments=" + comments + ", urls=" + urls + "]";
    }

    public JsonObject toJson() {
        JsonArrayBuilder ab = Json.createArrayBuilder();
        for (String u : urls) {
            ab.add(u);
        }

        return Json.createObjectBuilder()
                .add("bundleId", bundleId)
                .add("date", date)
                .add("name", name)
                .add("title", title)
                .add("comments", comments)
                .add("urls", ab)
                .build();
    }

    public static Archive create(Document d) {
        Archive a = new Archive();
        a.setBundleId(d.getString("bundleId")); 
        a.setDate(d.getString("date")); 
        a.setName(d.getString("name")); 
        a.setTitle(d.getString("title")); 
        a.setComments(d.getString("comments")); 
        a.setUrls(d.getList("urls", String.class));

        return a;
    }
}
