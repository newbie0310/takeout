package example.com.waimai.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import example.com.waimai.R;
import example.com.waimai.presenter.net.bean.HomeInfo;
import example.com.waimai.presenter.net.bean.Promotion;

/**
 * Created by john1 on 2017/9/21.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public HomeRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    public static final int ITEM_HEAD = 0;
    public static final int ITEM_SELER = 1;
    public static final int ITEM_DIV = 2;
    private HomeInfo data;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEAD) {
            //头条目
            View view = View.inflate(mContext, R.layout.item_title, null);
            TitleViewHolder titleViewHolder = new TitleViewHolder(view);
            return titleViewHolder;
        } else if (viewType == ITEM_SELER) {
            //商家条目
            View view = View.inflate(mContext, R.layout.item_seller, null);
            SellerViewHolder sellserViewHolder = new SellerViewHolder(view);
            return sellserViewHolder;
        } else {
            //分割线条目
            View view = View.inflate(mContext, R.layout.item_division, null);
            DivisionViewHolder divisionViewHolder = new DivisionViewHolder(view);
            return divisionViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //给holder中的所有的控件绑定数据
        if (position == 0){
            //头
        }else if(data.getBody().get(position-1).type == 0){
            //一般条目，指定商家
        }
    }

    @Override
    public int getItemCount() {
        //依赖于此方法中返回的条目总数、集合
        if (data != null && data.getBody() != null && data.getHead() != null && data.getBody().size() > 0) {
            return data.getBody().size() + 1;
        }
        return 0;
    }


    public void setData(HomeInfo data) {
        this.data = data;
        //通知数据适配器刷新
        notifyDataSetChanged();
    }

    /**
     * 通过索引获取服务器返回的type值,判断条目类型的状态码
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_HEAD;//返回头部view的状态码
        } else if (data.getBody().get(position - 1).type == 0) {
            return ITEM_SELER;//返回一般条目的状态码
        } else {
            return ITEM_DIV;//返回分割线条目的状态码
        }
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.slider)
        SliderLayout slider;

        public TitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            ArrayList<Promotion> promotionList = data.getHead().getPromotionList();
            for (int i = 0; i < promotionList.size(); i++) {
                TextSliderView textSliderView = new TextSliderView(mContext);
                // initialize a SliderLayout
                textSliderView
                        .description(promotionList.get(i).getInfo())
                        .image(promotionList.get(i).getPic())
                        .setScaleType(BaseSliderView.ScaleType.Fit);
                slider.addSlider(textSliderView);
            }
            slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            slider.setCustomAnimation(new DescriptionAnimation());
            slider.setDuration(4000);
        }
    }

    private class SellerViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.slider)
        SliderLayout slider;
        @InjectView(R.id.tvCount)
        TextView tvCount;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.ratingBar)
        RatingBar ratingBar;

        public SellerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    private class DivisionViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_division_title)
        TextView tvDivisionTitle;
        @InjectView(R.id.tv1)
        TextView tv1;
        @InjectView(R.id.tv2)
        TextView tv2;
        @InjectView(R.id.tv3)
        TextView tv3;
        @InjectView(R.id.tv4)
        TextView tv4;
        @InjectView(R.id.tv5)
        TextView tv5;
        @InjectView(R.id.tv6)
        TextView tv6;
        public DivisionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
