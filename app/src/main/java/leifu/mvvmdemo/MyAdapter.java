package leifu.mvvmdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import leifu.mvvmdemo.bean.NewsLatestBean;
import leifu.mvvmdemo.databinding.ItemNewslatestBinding;
import leifu.mvvmdemo.utils.ItemViewHolder;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/7 15:09
 * 描述:
 */

public abstract class MyAdapter<T> extends RecyclerView.Adapter<ItemViewHolder> {
    protected List<T> mDatas;
    protected int mLayoutId;
    protected int mVariableId;

    public MyAdapter(int layoutId, int variableId, List<T> datas) {
        mDatas = datas;
        mVariableId = variableId;
        mLayoutId = layoutId;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
//        GlideImgManager.loadImage(App.getInstance().getApplicationContext(), ((NewsLatestBean.StoriesBean) (mDatas.get(position))).getImages().get(0), (ImageView) holder.itemView.findViewById(R.id.iv_news));
        ((ItemNewslatestBinding) (holder.getmBinding())).setUrl(((NewsLatestBean.StoriesBean) (mDatas.get(position))).getImages().get(0));
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(ItemViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
