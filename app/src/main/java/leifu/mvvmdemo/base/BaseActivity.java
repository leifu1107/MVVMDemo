package leifu.mvvmdemo.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import leifu.mvvmdemo.R;
import leifu.mvvmdemo.app.App;
import leifu.mvvmdemo.bean.MessageEvent;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 创建人: 雷富
 * 创建时间: 2018/1/29 17:12
 * 描述:
 */

public abstract class BaseActivity<VB extends ViewDataBinding> extends SupportActivity {
    protected Activity mContext;
    public TextView mCenterTitle;
    public LinearLayout mTitleLayout;
    public TextView mRightText;
    public ImageView mBtnBack;
    public ImmersionBar mImmersionBar;

    public VB mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        setContentView(getLayout());
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true);
        mImmersionBar.init();   //所有子类都将继承这些相同的属性
        EventBus.getDefault().register(this);
        mContext = this;
        App.getInstance().addActivity(this);
        mBinding = DataBindingUtil.setContentView(this, getLayout());
        initEventAndData();
    }

    /**
     * 设置布局标题头
     *
     * @param centerTitle 中间标题
     * @param rightText   布局最右边文字
     * @param bgColor     整个头布局背景颜色
     */
    public void setTitleText(String centerTitle, String rightText, int bgColor) {
        mCenterTitle = (TextView) findViewById(R.id.centerTitle);
        mTitleLayout = (LinearLayout) findViewById(R.id.titleLayout);
        mRightText = (TextView) findViewById(R.id.rightText);
        mBtnBack = (ImageView) findViewById(R.id.btnback);
        mCenterTitle.setText(centerTitle);
        mRightText.setText(rightText);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleLayout.setBackgroundResource(bgColor);
    }


    public void mStartActivity(Class<?> intentActivity, Bundle bundle) {
        Intent intent = new Intent(mContext, intentActivity);
        intent.putExtras(bundle);
        super.startActivity(intent);
    }

    public void mStartActivity(Class<?> intentActivity) {
        Intent intent = new Intent(mContext, intentActivity);
        super.startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态}
        }
        EventBus.getDefault().unregister(this);
        App.getInstance().removeActivity(this);
    }

    @Override
    public void finish() {
        App.getInstance().removeActivity(this);
        super.finish();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
    }
}
