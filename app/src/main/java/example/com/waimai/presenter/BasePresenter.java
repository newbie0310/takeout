package example.com.waimai.presenter;

import java.util.HashMap;

import example.com.waimai.presenter.net.ResponseInfoAPI;
import example.com.waimai.presenter.net.bean.ResponseInfo;
import example.com.waimai.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by john1 on 2017/9/22.
 */

public abstract class BasePresenter {

    public ResponseInfoAPI responseInfoAPI;
    private HashMap<String, String> errorMap;

    //网络请求
    public BasePresenter() {
        errorMap = new HashMap<>();
        errorMap.put("1","服务器数据没有更新");
        errorMap.put("2","服务器忙");
        errorMap.put("3","服务器挂了");
        //1.指定网络请求的主机名称
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        responseInfoAPI = retrofit.create(ResponseInfoAPI.class);
    }
    class CallBackAdapter implements Callback<ResponseInfo>{

        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
            //请求成功,获取服务器返回的所有json
            ResponseInfo body = response.body();
            if (body.getCode().equals("0")){
                //请求成功
                String json = body.getData();
                //使用抽象方法对json进行解析，让子类进行实现
                parseJson(json);
            }
            else {
                //请求失败
                String errMessage  = errorMap.get(body.getCode());
                onFailure(call,new RuntimeException(errMessage));
            }
        }

        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {
            //请求失败
            String message = t.getMessage();
            showError(message);
        }
    }

    protected abstract void showError(String message);
    public abstract void parseJson(String json);
}
