
package megogo.responseMegogoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pictures {

    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("105x85")
    @Expose
    private String _105x85;
    @SerializedName("150x85")
    @Expose
    private String _150x85;
    @SerializedName("980x560")
    @Expose
    private String _980x560;
    @SerializedName("105x60")
    @Expose
    private String _105x60;
    @SerializedName("125x70")
    @Expose
    private String _125x70;
    @SerializedName("480x330")
    @Expose
    private String _480x330;
    @SerializedName("1600x520")
    @Expose
    private String _1600x520;
    @SerializedName("230x130")
    @Expose
    private String _230x130;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String get105x85() {
        return _105x85;
    }

    public void set105x85(String _105x85) {
        this._105x85 = _105x85;
    }

    public String get150x85() {
        return _150x85;
    }

    public void set150x85(String _150x85) {
        this._150x85 = _150x85;
    }

    public String get980x560() {
        return _980x560;
    }

    public void set980x560(String _980x560) {
        this._980x560 = _980x560;
    }

    public String get105x60() {
        return _105x60;
    }

    public void set105x60(String _105x60) {
        this._105x60 = _105x60;
    }

    public String get125x70() {
        return _125x70;
    }

    public void set125x70(String _125x70) {
        this._125x70 = _125x70;
    }

    public String get480x330() {
        return _480x330;
    }

    public void set480x330(String _480x330) {
        this._480x330 = _480x330;
    }

    public String get1600x520() {
        return _1600x520;
    }

    public void set1600x520(String _1600x520) {
        this._1600x520 = _1600x520;
    }

    public String get230x130() {
        return _230x130;
    }

    public void set230x130(String _230x130) {
        this._230x130 = _230x130;
    }

}
