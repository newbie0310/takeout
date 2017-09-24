package example.com.waimai.ui.fragment;

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
