package okhttp.callback;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by yueguang on 16-12-2.
 */

public abstract class StatusCallBack extends Callback<Integer> {
    @Override
    public Integer parseNetworkResponse(Response response, int id) throws IOException {
        Integer result = 0;
        try {
            result = new JSONObject(response.body().string()).optInt("status", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
