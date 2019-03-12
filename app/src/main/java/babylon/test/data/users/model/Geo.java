package babylon.test.data.users.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Vlad Sabau on 10.03.19.
 */
@Parcel(value = Parcel.Serialization.BEAN)
public class Geo {

    @SerializedName("lat")
    @Expose
    private String lat;

    @SerializedName("lgn")
    @Expose
    private String lgn;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLgn() {
        return lgn;
    }

    public void setLgn(String lgn) {
        this.lgn = lgn;
    }
}
