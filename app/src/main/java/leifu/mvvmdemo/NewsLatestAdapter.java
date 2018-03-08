package leifu.mvvmdemo;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import leifu.mvvmdemo.bean.NewsLatestBean;
import leifu.mvvmdemo.utils.GlideImgManager;


/**
 * 创建人: 雷富
 * 创建时间: 2018/2/2 14:18
 * 描述:
 */

public class NewsLatestAdapter extends BaseQuickAdapter<NewsLatestBean.StoriesBean,BaseViewHolder> {

    public NewsLatestAdapter(@LayoutRes int layoutResId, @Nullable List<NewsLatestBean.StoriesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsLatestBean.StoriesBean item) {
        helper.setText(R.id.tv_news, item.getTitle());
        GlideImgManager.loadImage(mContext,item.getImages().get(0), (ImageView) helper.getView(R.id.iv_news));
    }
}
