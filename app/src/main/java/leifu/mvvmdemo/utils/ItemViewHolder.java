package leifu.mvvmdemo.utils;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/7 15:08
 * 描述:
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding mBinding;

    public ItemViewHolder(View v) {
        super(v);
        mBinding = DataBindingUtil.bind(v);
    }

    public ItemViewHolder setBinding(int variableId , Object object){

        mBinding.setVariable(variableId , object);
        mBinding.executePendingBindings();
        return this;
    }

    public ViewDataBinding getmBinding() {
        return mBinding;
    }
}
