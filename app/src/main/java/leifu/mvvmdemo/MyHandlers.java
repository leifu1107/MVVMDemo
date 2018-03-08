package leifu.mvvmdemo;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import leifu.mvvmdemo.bean.NewsLatestBean;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/8 10:17
 * 描述:监听事件
 */

public class MyHandlers {

    private Context mContext;
    private NewsLatestBean.StoriesBean storiesBean;

    public MyHandlers(Context context) {
        mContext = context;
    }

    public MyHandlers(Context context, NewsLatestBean.StoriesBean storiesBean) {
        mContext = context;
        this.storiesBean = storiesBean;
    }

    public void onClickFriend(View view) {
        Toast.makeText(mContext, "onClickFriend", Toast.LENGTH_LONG).show();
    }

    public void onItemClickFriend(View view) {
        Toast.makeText(mContext, "item"+storiesBean.getTitle(), Toast.LENGTH_LONG).show();
    }
}
