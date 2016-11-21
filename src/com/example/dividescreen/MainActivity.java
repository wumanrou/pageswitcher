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
	private ViewPager mViewPager;//��Ŷ��ҳ��Ŀؼ�
	private PagerTabStrip mTab;//ҳ��ѡ�
	private LinearLayout mImgs;//��ŵײ�ͼƬ�����Բ���
	private int[] layouts=new int[]{R.layout.framelayouttest,R.layout.texteditor,R.layout.textvieweffect};//���ҳ��Ĳ����ļ�
	private String[] titles=new String[]{"�޺��","����","����Ч��"};
	private ImageView[] mImgViews=new ImageView[layouts.length];
	private List<View>views=new ArrayList<View>();//�������ҳ��ļ���
	private List<String>pagerTitles=new ArrayList<String>();//������б���ļ���

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//������ĻΪȫ����ʾ
		setContentView(R.layout.activity_main);
		mImgs=(LinearLayout)findViewById(R.id.mImgs);//����ID��ȡ��Ӧ�Ŀؼ�
		mViewPager=(ViewPager)findViewById(R.id.mViewPager);
		mTab=(PagerTabStrip)findViewById(R.id.mTabr);
		//����ѡ�֮��ı߾࣬Ĭ���������һ��ҳ���п��Կ������ѡ�
		mTab.setTextSpacing(300);
		init();//ִ�г�ʼ������
		mViewPager.setAdapter(new MyPagerAdapter());//ΪViewPager�ؼ����������
		initImg();//��ʼ����ʾ��ͼƬ
		//ΪViewPager�ؼ����ҳ��任�¼�������
		mViewPager.setOnPageChangeListener(new MyPageChangeListener());
	}
    //�Զ���ҳ��任ʱ�������
	private class MyPageChangeListener implements OnPageChangeListener{
		public void onPageScrollStateChanged(int arg0){
	}
		public void onPageScrolled(int arg0,float arg1,int arg2){
	}
		public void onPageSelected(int selected){//��ʾ��ҳ�淢���仯
			resetImg();//���õײ���ʾ��ͼƬ
        //����ǰҳ���Ӧ��ͼƬ����Ϊ��ɫ
		mImgViews[selected].setImageResource(R.drawable.choosed);
	}
}
	//�Զ���PageAdapter�࣬�������ڷ�װ��Ҫ�л��Ķ��ҳ��
	private class MyPagerAdapter extends PagerAdapter{
		public int getCount(){//�÷�������������ҳ��ĸ���
			return views.size();
		}
		public boolean isViewFromObject(View arg0,Object arg1){
			return arg0==arg1;
		}
		public CharSequence getPageTitle(int position){//�÷������ڷ���ҳ��ı���
			return pagerTitles.get(position);
		}
		//�÷������ڳ�ʼ��ָ����ҳ��
		public Object instantiateItem(ViewGroup container,int position){
			((ViewPager)container).addView(views.get(position));
			return views.get(position);
		}
		//�÷�����������ָ����ҳ��
		public void destroyItem(ViewGroup container,int position,Object object){
			((ViewPager)container).removeView(views.get(position));
		}
	}
	public void init(){//�÷������ڳ�ʼ����Ҫ��ʾ��ҳ�棬��������ӵ�������
		for(int i=0;i<layouts.length;i++){
			View view=getLayoutInflater().inflate(layouts[i], null);
			views.add(view);
			pagerTitles.add(titles[i]);
		}
	}
	public void initImg(){//�÷������ڳ�ʼ���ײ�ͼƬ������ͼƬ��ӵ�ˮƽ���Բ�����
		for(int i=0;i<mImgViews.length;i++){
			mImgViews[i]=new ImageView(MainActivity.this);
			if(i==0){//Ĭ������µ�һ��ͼ��ѡ��
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
	//����һ�������ĵ����¼����������������ڴ���ײ�ͼƬ�ĵ����¼�
	//��Ҫ���л�ͼƬ����ʾ����������ͼƬ��ʾΪ��ɫ����������ʾΪ��ɫ
	private OnClickListener mOnClickListener=new OnClickListener(){
		public void onClick(View v){
			resetImg();//����ͼƬ����ʾ�������е�ͼƬ������Ϊ��ɫ
			//����������ͼƬ����Ϊ��ɫ
			((ImageView)v).setImageResource(R.drawable.choosed);
			//�л�ҳ�����ʾ�����ݵ�����ͼƬ��ʾ��Ӧ��ҳ��
			mViewPager.setCurrentItem(v.getId());
		}
	};
	public void resetImg(){//�÷������ڽ����е�ImageView����ʾΪδѡ��״̬��ͼƬ
		for(int i=0;i<mImgViews.length;i++){
			mImgViews[i].setImageResource(R.drawable.unchoosed);
		}
	}

}
