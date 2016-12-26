package com.getui.test.ac;

import android.app.Activity;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.getui.test.DemoApplication;
import com.getui.test.R;
import com.getui.test.databinding.RxTextBind;

/**
 * 创建者     shaochengbao
 * 公司       深圳前瞻资讯股份有限公司
 * 创建时间   2016/12/26 16:53
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class RxTextActivity extends Activity {


    private RxTextBind mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=DataBindingUtil.setContentView(this, R.layout.activity_rx);

        mBinding.setClick(this);
    }

    public void bindClick(View view){
        switch (view.getId()) {
            case R.id.say_hello:
                Toast.makeText(DemoApplication.getContext(),"helloWord",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
