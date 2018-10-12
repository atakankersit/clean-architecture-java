package baseprojectjava.atakan.android.baseprojectjava.network;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class ResponseStatus<R> {


    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILURE = 2;
    public static final int STATUS_SERVICE_FAILURE = 3;

    R body;
    int Status;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            STATUS_SUCCESS,
            STATUS_FAILURE,
            STATUS_SERVICE_FAILURE
    })
    public @interface NetworkResonseStatus {}

    public R getBody() {
        return body;
    }

    public void setBody(R body) {
        this.body = body;
    }

    @NetworkResonseStatus
    public int getStatus() {
        return Status;
    }

    public void setStatus(@NetworkResonseStatus int status) {
        Status = status;
    }
}
