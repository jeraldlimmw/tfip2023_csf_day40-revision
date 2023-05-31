package ibf2022.batch2.csf.backend.models;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public class Bundle {
    private String bundleId;
    private String title;
    private String date;

    public JsonObjectBuilder toJsonOB() {
        return Json.createObjectBuilder()
                .add("bundleId", bundleId)
                .add("title", title)
                .add("date", date);
    }
}
