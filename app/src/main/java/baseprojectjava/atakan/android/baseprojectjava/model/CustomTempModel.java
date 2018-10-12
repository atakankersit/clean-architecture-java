
package baseprojectjava.atakan.android.baseprojectjava.model;

import com.google.gson.annotations.SerializedName;

public class CustomTempModel {

    @SerializedName("key")
    private String Key;
    @SerializedName("one")
    private String One;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getOne() {
        return One;
    }

    public void setOne(String one) {
        One = one;
    }

}
