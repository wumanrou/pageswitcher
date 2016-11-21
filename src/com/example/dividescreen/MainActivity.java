package com.example.dividescreen;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private ViewPager mViewPager;//存放多个页面的控件
	private PagerTabStrip mTab;//页面选项卡
	private LinearLayout mImgs;//存放底部图片的线性布局
	private int[] layouts=new int[]{R.layout.framelayouttest,R.layout.texteditor,R.layout.textvieweffect};//多个页面的布局文件
	private String[] titles=new String[]{"霓虹灯","文字","文字效果"};
	private ImageView[] mImgViews=new ImageView[layouts.length];
	private List<View>views=new ArrayList<View>();//存放所有页面的集合
	private List<String>pagerTitles=new ArrayList<String>();//存放所有标题的集合

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置屏幕为全屏显示
		setContentView(R.layout.activity_main);
		mImgs=(LinearLayout)findViewById(R.id.mImgs);//根据ID获取相应的控件
		mViewPager=(ViewPager)findViewById(R.id.mViewPager);
		mTab=(PagerTabStrip)findViewById(R.id.mTabr);
		//设置选项卡之间的边距，默认情况下在一个页面中可以看见多个选项卡
		mTab.setTextSpacing(300);
		init();//执行初始化操作
		mViewPager.setAdapter(new MyPagerAdapter());//为ViewPager控件添加适配器
		initImg();//初始化显示的图片
		//为ViewPager控件添加页面变换事件监听器
		mViewPager.setOnPageChangeListener(new MyPageChangeListener());
	}
    //自定义页面变换时间监听器
	private class MyPageChangeListener implements OnPageChangeListener{
		public void onPageScrollStateChanged(int arg0){
	}
		public void onPageScrolled(int arg0,float arg1,int arg2){
	}
		public void onPageSelected(int selected){//显示的页面发生变化
			resetImg();//重置底部显示的图片
        //将当前页面对应的图片设置为红色
		mImgViews[selected].setImageResource(R.drawable.choosed);
	}
}
	//自定义PageAdapter类，该类用于封装需要切换的多个页面
	private class MyPagerAdapter extends PagerAdapter{
		public int getCount(){//该方法返回所包含页面的个数
			return views.size();
		}
		public boolean isViewFromObject(View arg0,Object arg1){
			return arg0==arg1;
		}
		public CharSequence getPageTitle(int position){//该方法用于返回页面的标题
			return pagerTitles.get(position);
		}
		//该方法用于初始化指定的页面
		public Object instantiateItem(ViewGroup container,int position){
			((ViewPager)container).addView(views.get(position));
			return views.get(position);
		}
		//该方法用于销毁指定的页面
		public void destroyItem(ViewGroup container,int position,Object object){
			((ViewPager)container).removeView(views.get(position));
		}
	}
	public void init(){//该方法用于初始化需要显示的页面，并将其添加到集合中
		for(int i=0;i<layouts.length;i++){
			View view=getLayoutInflater().inflate(layouts[i], null);
			views.add(view);
			pagerTitles.add(titles[i]);
		}
	}
	public void initImg(){//该方法用于初始化底部图片，并将图片添加到水平线性布局中
		for(int i=0;i<mImgViews.length;i++){
			mImgViews[i]=new ImageView(MainActivity.this);
			if(i==0){//默认情况下第一张图被选中
				mImgViews[i].setImageResource(R.drawable.choosed);
			}else{
			   mImgViews[i].setImageResource(R.drawable.unchoosed);
			}
			mImgViews[i].setPadding(20, 0, 0, 0);
			mImgViews[i].setId(i);
			mImgViews[i].setOnClickListener(mOnClickListener);
			mImgs.addView(mImgViews[i]);
		}
	}
	//声明一个匿名的单击事件处理器变量，用于处理底部图片的单击事件
	//主要是切换图片的显示，被单击的图片显示为红色，其他的显示为黄色
	private OnClickListener mOnClickListener=new OnClickListener(){
		public void onClick(View v){
			resetImg();//重置图片的显示，将所有的图片都设置为黄色
			//将被单击的图片设置为红色
			((ImageView)v).setImageResource(R.drawable.choosed);
			//切换页面的显示，根据单机的图片显示对应的页面
			mViewPager.setCurrentItem(v.getId());
		}
	};
	public void resetImg(){//该方法用于将所有的ImageView都显示为未选中状态的图片
		for(int i=0;i<mImgViews.length;i++){
			mImgViews[i].setImageResource(R.drawable.unchoosed);
		}
	}

}
