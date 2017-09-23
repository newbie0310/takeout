package example.com.waimai.presenter.net;

import example.com.waimai.presenter.net.bean.ResponseInfo;
import example.com.waimai.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by john1 on 2017/9/22.
 */

public interface ResponseInfoAPI {
    @GET(Constant.HOME_URL)
    Call<ResponseInfo> getHomeInfo(@Query("latitude") String latitude, @Query("longitude") String longitude);
}
