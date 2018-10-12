
package baseprojectjava.atakan.android.baseprojectjava.base;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class BaseError {

    @SerializedName("code")
    private int mCode;
    @SerializedName("developerMessage")
    private String mDeveloperMessage;
    @SerializedName("logRef")
    private String mLogRef;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("type")
    private String mType;

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public String getDeveloperMessage() {
        return mDeveloperMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        mDeveloperMessage = developerMessage;
    }

    public String getLogRef() {
        return mLogRef;
    }

    public void setLogRef(String logRef) {
        mLogRef = logRef;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
