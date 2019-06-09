package com.example.tablayoutdemo;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tablayoutdemo.Adapter.CustomTabPagerAdapter;
import com.example.tablayoutdemo.Fragment.RecyclerViewFragment;
import com.example.tablayoutdemo.Model.Item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * setCustomView的方式来设置自定义的indicator
 */
public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    TabLayout tabLayout;

    List<Fragment> fragmentList = new ArrayList<>();

    List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.content_vp);
        initFragment();
    }

    public void initFragment() {
        String[] strs = {"好货", "立减", "福利"};
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(viewPager);
        init_List();
        fragmentList.add(RecyclerViewFragment.newInstace(itemList));
        fragmentList.add(RecyclerViewFragment.newInstace(itemList));
        fragmentList.add(RecyclerViewFragment.newInstace(itemList));
        CustomTabPagerAdapter customTabPagerAdapter = new CustomTabPagerAdapter(getSupportFragmentManager(), this, fragmentList);
        viewPager.setAdapter(customTabPagerAdapter);
        for (int i = 0; i < customTabPagerAdapter.getCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.tabitem);//给每一个tab设置view
            //Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/ARJULIAN.ttf");
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.text1);
            //textView.setTypeface(font);
            textView.setText(strs[i]);//设置tab上的文字
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.text1).setSelected(true);//第一个tab被选中
                textView.setTextSize(19);
                textView.setAlpha(0.9f);
            } else {
                tab.getCustomView().findViewById(R.id.text1).setSelected(false);//第一个tab被选中
                textView.setTextSize(15);
                textView.setAlpha(0.5f);
            }
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.text1).setSelected(true);
                TextView tv = (TextView) tab.getCustomView().findViewById(R.id.text1);
                tv.setTextSize(19);
                tv.setAlpha(0.9f);
                tv.invalidate();
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.text1).setSelected(false);
                TextView tv = (TextView) tab.getCustomView().findViewById(R.id.text1);
                tv.setTextSize(15);
                tv.setAlpha(0.5f);
                tv.invalidate();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setTabWidth(tabLayout,2);
    }

    public void init_List() {
        Item item1 = new Item();
        item1.setDescription("乘车码");
        item1.setImageId(R.drawable.y1);
        itemList.add(item1);
        Item item2 = new Item();
        item2.setDescription("转账");
        item2.setImageId(R.drawable.y7);
        itemList.add(item2);
        Item item3 = new Item();
        item3.setImageId(R.drawable.y3);
        item3.setDescription("信用卡还款");
        itemList.add(item3);
        Item item4 = new Item();
        item4.setDescription("QQ充值");
        item4.setImageId(R.drawable.y8);
        itemList.add(item4);
        Item item5 = new Item();
        item5.setDescription("公共缴费");
        item5.setImageId(R.drawable.y1);
        itemList.add(item5);
        Item item6 = new Item();
        item6.setImageId(R.drawable.y7);
        item6.setDescription("QQ信贷");
        itemList.add(item6);
        Item item7 = new Item();
        item7.setImageId(R.drawable.y3);
        item7.setDescription("微信理财");
        itemList.add(item7);
        Item item8 = new Item();
        item8.setImageId(R.drawable.y8);
        item8.setDescription("更多");
        itemList.add(item8);
    }

    // 设置自定义的tablayout的宽度，和字体长度一样长
    public static void setTabWidth(final TabLayout tabLayout, final int padding){
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距 注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = padding;
                        params.rightMargin = padding;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
