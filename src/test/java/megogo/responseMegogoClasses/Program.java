
package megogo.responseMegogoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Program {

    @SerializedName("external_id")
    @Expose
    private Integer externalId;
    @SerializedName("object_id")
    @Expose
    private Object objectId;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("schedule_string")
    @Expose
    private Object scheduleString;
    @SerializedName("genre")
    @Expose
    private Genre genre;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("pictures")
    @Expose
    private Pictures pictures;
    @SerializedName("virtual_object_id")
    @Expose
    private String virtualObjectId;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("start_timestamp")
    @Expose
    private Integer startTimestamp;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("end_timestamp")
    @Expose
    private Integer endTimestamp;
    @SerializedName("grouped_programs")
    @Expose
    private Object groupedPrograms;
    @SerializedName("schedule_type")
    @Expose
    private String scheduleType;

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public Object getObjectId() {
        return objectId;
    }

    public void setObjectId(Object objectId) {
        this.objectId = objectId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getScheduleString() {
        return scheduleString;
    }

    public void setScheduleString(Object scheduleString) {
        this.scheduleString = scheduleString;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Pictures getPictures() {
        return pictures;
    }

    public void setPictures(Pictures pictures) {
        this.pictures = pictures;
    }

    public String getVirtualObjectId() {
        return virtualObjectId;
    }

    public void setVirtualObjectId(String virtualObjectId) {
        this.virtualObjectId = virtualObjectId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Integer getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Integer startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Integer endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public Object getGroupedPrograms() {
        return groupedPrograms;
    }

    public void setGroupedPrograms(Object groupedPrograms) {
        this.groupedPrograms = groupedPrograms;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

}
