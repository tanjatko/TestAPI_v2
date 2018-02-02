package megogo.responseLocalDateClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalTime {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}

