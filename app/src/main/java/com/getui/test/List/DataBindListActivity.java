package com.getui.test.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.getui.test.R;
import com.getui.test.adapter.SearchAdapter;
import com.getui.test.bean.DataBindBean;

import org.mozilla.universalchardet.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 创建者     shaochengbao
 * 公司       深圳前瞻资讯股份有限公司
 * 创建时间   2016/12/23 18:25
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class DataBindListActivity extends Activity {

    @InjectView(R.id.recycler)
    RecyclerView mRecycler;
    private List<DataBindBean> mArticleList;
    private SearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databind_list);
        ButterKnife.inject(this);

        mArticleList = new ArrayList<>();
        mArticleList.add(new DataBindBean("zhansan","nv",true));
        mArticleList.add(new DataBindBean("lisi","nan",false));
        mArticleList.add(new DataBindBean("zhansan2","nv",true));
        mArticleList.add(new DataBindBean("lisi2","nan",false));
        mArticleList.add(new DataBindBean("zhansan3","nv",true));
        mArticleList.add(new DataBindBean("lisi3","nan",false));
        mArticleList.add(new DataBindBean("zhansan4","nv",true));
        mArticleList.add(new DataBindBean("lisi4","nan",false));

        initSearchRecycler();
    }

    /**
     * 初始化recycler
     */
    private void initSearchRecycler() {
        //设置布局管理器
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));//网格布局
        mAdapter=new SearchAdapter(R.layout.activity_databind, mArticleList);
        //设置Item动画
        mAdapter.openLoadAnimation(5);

        mRecycler.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                //点击了文章，携带文章的信息到下一个界面
            }
        });

        mRecycler.setAdapter(mAdapter);
    }
}
