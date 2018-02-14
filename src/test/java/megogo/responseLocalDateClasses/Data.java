package megogo.responseLocalDateClasses;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

        private Integer timestamp;
        private String timeLocal;
        private Integer utcOffset;
        private Integer timestampLocal;
        private Integer timestampGmt;
        private String timezone;

        public Integer getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Integer timestamp) {
            this.timestamp = timestamp;
        }
        @JsonGetter("time_local")
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

