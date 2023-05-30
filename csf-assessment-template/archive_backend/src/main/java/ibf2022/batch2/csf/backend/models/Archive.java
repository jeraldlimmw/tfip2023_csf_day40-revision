package ibf2022.batch2.csf.backend.models;

import java.util.List;

import org.joda.time.DateTime;

public class Archive {
    private String bundleId;
    private DateTime date;
    private String title;
    private String name;
    private String comments;
    private List<String> urls;
    
    public Archive() {

    }

    public Archive(DateTime date, String title, String name, String comments) {
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
    public DateTime getDate() {
        return date;
    }
    public void setDate(DateTime date) {
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

    
}
