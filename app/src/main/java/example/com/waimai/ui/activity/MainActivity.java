package example.com.waimai.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import example.com.waimai.R;
import example.com.waimai.ui.fragment.HomeFragment;
import example.com.waimai.ui.fragment.MoreFragment;
import example.com.waimai.ui.fragment.OrderFragment;
import example.com.waimai.ui.fragment.UserFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.main_fragment_container)
    FrameLayout mainFragmentContainer;

    @InjectView(R.id.main_bottome_switcher_container)
    LinearLayout mainButtomSwitcherContainer;

    private ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initFragment();
        initClick();
        View childView = mainButtomSwitcherContainer.getChildAt(0);
        onClick(childView);
    }

    private void initClick() {
        int childCount = mainButtomSwitcherContainer.getChildCount();
        for (int i = 0;i < childCount;i++){
            FrameLayout frameLayout = (FrameLayout) mainButtomSwitcherContainer.getChildAt(i);
            frameLayout.setOnClickListener(this);
        }
    }

    private void initFragment(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new UserFragment());
        fragmentList.add(new MoreFragment());

    }

    @Override
    public void onClick(View view) {
        //viewd到底是那个孩子节点
        int index = mainButtomSwitcherContainer.indexOfChild(view);
        //注册点击事件要做的业务
        //通过递归FragmentLayout,循环遍历其所有的孩子节点，将是否可用的状态，都设置为false
        changeUI(index);
        changeFragment(index);
    }

    private void changeFragment(int index){
        //获取fragment管理者对象
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,fragmentList.get(index)).commit();
    }

    /**
     * @param index 指定此索引位置的控件，以及其孩子节点上的控件都不可用
     */
    private void changeUI(int index) {
        for (int i = 0;i < mainButtomSwitcherContainer.getChildCount();i++){
            View view = mainButtomSwitcherContainer.getChildAt(i);
            if (i == index){
                setEnable(view,false);
            }else {
                setEnable(view,true);
            }
        }
    }

    /**
     * 将view设置为b（true可用，false不可用），将view中所有的孩子设置为b（true可用，false不可用）
     * @param view
     * @param b
     */
    private void setEnable(View view,boolean b){
        //将view设置为不可用
        view.setEnabled(b);
        //处理view的孩子节点,ViewGroup容器。只有容器才有孩子节点
        if (view instanceof ViewGroup){
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0;i < childCount;i++){
                View childView = ((ViewGroup) view).getChildAt(i);
                setEnable(childView,b);
            }
        }
    }
}
