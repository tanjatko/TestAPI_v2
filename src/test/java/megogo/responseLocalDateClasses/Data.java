package megogo.responseLocalDateClasses;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

        @SerializedName("timestamp")
        @Expose
        private Integer timestamp;
        @SerializedName("time_local")
        @Expose
        private String timeLocal;
        @SerializedName("utc_offset")
        @Expose
        private Integer utcOffset;
        @SerializedName("timestamp_local")
        @Expose
        private Integer timestampLocal;
        @SerializedName("timestamp_gmt")
        @Expose
        private Integer timestampGmt;
        @SerializedName("timezone")
        @Expose
        private String timezone;

        public Integer getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Integer timestamp) {
            this.timestamp = timestamp;
        }

        public String getTimeLocal() {
            return timeLocal;
        }

        public void setTimeLocal(String timeLocal) {
            this.timeLocal = timeLocal;
        }

        public Integer getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(Integer utcOffset) {
            this.utcOffset = utcOffset;
        }

        public Integer getTimestampLocal() {
            return timestampLocal;
        }

        public void setTimestampLocal(Integer timestampLocal) {
            this.timestampLocal = timestampLocal;
        }

        public Integer getTimestampGmt() {
            return timestampGmt;
        }

        public void setTimestampGmt(Integer timestampGmt) {
            this.timestampGmt = timestampGmt;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

    }

