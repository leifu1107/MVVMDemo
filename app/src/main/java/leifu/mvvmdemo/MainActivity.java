package leifu.mvvmdemo;

import android.support.v7.widget.LinearLayoutManager;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import leifu.mvvmdemo.app.Constants;
import leifu.mvvmdemo.base.BaseActivity;
import leifu.mvvmdemo.base.Convert;
import leifu.mvvmdemo.bean.NewsLatestBean;
import leifu.mvvmdemo.bean.User;
import leifu.mvvmdemo.databinding.ActivityMainBinding;
import leifu.mvvmdemo.utils.ItemViewHolder;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        User user = new User("名字", "18368180705");
        mBinding.setUser(user);
        mBinding.setMyhandle(new MyHandlers(mContext));
        mBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        OkGo.<String>get(Constants.HOST + "news/latest")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        NewsLatestBean newsLatestBean = Convert.fromJson(response.body(), NewsLatestBean.class);
                        MyAdapter<NewsLatestBean.StoriesBean> adapter = new MyAdapter<NewsLatestBean.StoriesBean>(R.layout.item_newslatest, BR.newslatestbean, newsLatestBean.getStories()) {
                            @Override
                            public void convert(ItemViewHolder holder, NewsLatestBean.StoriesBean storiesBean) {
                                holder.setBinding(BR.newslatestbean, storiesBean);
                                holder.setBinding(BR.myhandle, new MyHandlers(mContext, storiesBean));
                                mBinding.setUrl(storiesBean.getImages().get(0));

                            }
                        };

                        mBinding.mRecyclerView.setAdapter(adapter);
                    }
                });


    }
}
