package com.getui.test.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.getui.test.R;
import com.getui.test.bean.DataBindBean;

import java.util.List;


/**
 * 创建者     shaochengbao
 * 公司       深圳前瞻资讯股份有限公司
 * 创建时间   2016/10/13 15:13
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class SearchAdapter extends BaseQuickAdapter<DataBindBean,BaseViewHolder> {

    public SearchAdapter(int layoutResId, List<DataBindBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DataBindBean dataBindBean) {
        baseViewHolder.setText(R.id.name,dataBindBean.name)
                .setText(R.id.id_sex,dataBindBean.sex);

        if(dataBindBean.isSex){
            baseViewHolder.getView(R.id.group).setBackgroundColor(Color.parseColor("#0fff0f"));
        }else{
            baseViewHolder.getView(R.id.group).setBackgroundColor(Color.parseColor("#f0f0f0"));

        }
    }
}
