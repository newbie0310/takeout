package example.com.waimai.presenter;

import android.util.Log;

import com.google.gson.Gson;

import example.com.waimai.presenter.net.bean.HomeInfo;
import example.com.waimai.presenter.net.bean.ResponseInfo;
import example.com.waimai.ui.adapter.HomeRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by john1 on 2017/9/22.
 */

public class HomePresenter extends BasePresenter {

    private HomeRecyclerViewAdapter adapter;

    public HomePresenter(HomeRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void showError(String message) {

    }

    /**
     * 解析json
     * @param json
     */
    @Override
    public void parseJson(String json) {
        Log.i("",json);
        Gson gson = new Gson();
        //获取首页的数据
        HomeInfo homeInfo = gson.fromJson(json, HomeInfo.class);
        adapter.setData(homeInfo);
//        homeInfo.getBody();底部列表需要用到的数据
//        homeInfo.getHead();顶部轮播图需要用到的数据
    }

    /**
     * 触发网络请求
     */
    public void getHomeData(String latitude,String longitude){
        Call<ResponseInfo> homeInfo = responseInfoAPI.getHomeInfo(latitude,longitude);
        homeInfo.enqueue(new CallBackAdapter());
    }
}
