package example.com.waimai.ui.fragment;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import example.com.waimai.R;
import example.com.waimai.presenter.HomePresenter;
import example.com.waimai.ui.adapter.HomeRecyclerViewAdapter;

/**
 * Created by john1 on 2017/9/20.
 */

public class HomeFragment extends BaseFragment {
    private View view;
    private RecyclerView mRvHome;
    /**
     * 昌平区北七家宏福创业园修正大厦
     */
    private TextView mHomeTvAddress;
    private LinearLayout mLlTitleSearch;
    private LinearLayout mLlTitleContainer;
    private int sumY;
    private float duration = 300.0f;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        sumY = 0;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //首页的数据适配器
        HomeRecyclerViewAdapter homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(getContext());
        mRvHome.setAdapter(homeRecyclerViewAdapter);
        mRvHome.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        mRvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //滚动状态发生改变的时候调用
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //滚动的时候调用
                //累加dy的结果，计算一共滚动了多少像素，决定顶部title的背景颜色
                sumY += dy;
                int bgColor = 0X553190E8;
                if (sumY <= 0){
                    //没有移动
                    bgColor = 0X553190E8;
                }else if (sumY >= 300){
                    //移动到了颜色渐变最大值
                    bgColor = 0XFF3190E8;
                }else {
                    //移动过程中颜色的渐变
                    bgColor = (int) argbEvaluator.evaluate(sumY/duration,0X553190E8,0XFF3190E8);
                }
                mLlTitleContainer.setBackgroundColor(bgColor);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        //网络请求
        HomePresenter homePresenter = new HomePresenter(homeRecyclerViewAdapter);
        homePresenter.getHomeData("","");
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        mRvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        mHomeTvAddress = (TextView) view.findViewById(R.id.home_tv_address);
        mLlTitleSearch = (LinearLayout) view.findViewById(R.id.ll_title_search);
        mLlTitleContainer = (LinearLayout) view.findViewById(R.id.ll_title_container);
    }
}
