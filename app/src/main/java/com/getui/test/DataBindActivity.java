package com.getui.test;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.getui.test.bean.DataBindBean;
import com.getui.test.databinding.DataBind;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     shaochengbao
 * 公司       深圳前瞻资讯股份有限公司
 * 创建时间   2016/12/22 20:54
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class DataBindActivity extends Activity {

    private DataBind mBinding;
    private boolean mImgClick;
    private List<DataBindBean> mList;
    private int i;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=DataBindingUtil.setContentView(this, R.layout.activity_databind);
        mList=new ArrayList<>();
        mList.add(new DataBindBean("samge1--", "男", "http://4493bz.1985t.com/uploads/allimg/150127/4-15012G52133.jpg", true));
        mList.add(new DataBindBean("samge2--", "女", "http://n.7k7kimg.cn/2013/1101/1383291711366.gif", false));
        mList.add(new DataBindBean("samge3--", "未知", "http://4493bz.1985t.com/uploads/allimg/150127/4-15012G52133.jpg", true));
        mList.add(new DataBindBean("", "男女", "http://n.7k7kimg.cn/2013/1101/1383291711366.gif", false));
        mList.add(new DataBindBean("samge5--", "", "http://4493bz.1985t.com/uploads/allimg/150127/4-15012G52133.jpg", true));
        mList.add(new DataBindBean("samge6--", "xxxxx", null, false));
        mBinding.setList((ArrayList<DataBindBean>) mList);
        mBinding.setDataBean(mList.get(i++ % (mList.size())));
        mBinding.setClick(this);
    }

    public void bindClick(View view) {
        switch (view.getId()) {
            case R.id.group:
                mBinding.setIndex(i++ % (mList.size()));

                break;
            case R.id.name:
                mList.get((i-1) % (mList.size())).setName("samge" + System.currentTimeMillis());
                break;
            case R.id.id_sex:
                mList.get((i-1) % (mList.size())).setSex("男" + System.currentTimeMillis());

                break;
            case R.id.img:
                if (mImgClick) {
                    mList.get((i-1) % (mList.size())).setImgUrl("http://4493bz.1985t.com/uploads/allimg/150127/4-15012G52133.jpg");
                } else {
                    mList.get((i-1) % (mList.size())).setImgUrl("http://n.7k7kimg.cn/2013/1101/1383291711366.gif");
                }

                mImgClick=!mImgClick;
                break;

            default:
                break;
        }
    }
}
