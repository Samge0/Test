package com.getui.test.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.getui.test.DemoApplication;

/**
 * 创建者     shaochengbao
 * 公司       深圳前瞻资讯股份有限公司
 * 创建时间   2016/12/26 10:11
 * 描述	     数据绑定的utils
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class DataBindUtils {
    @BindingAdapter({"app:imgUrl"})
    public static void imageLoader(ImageView imageView, String url) {
        Glide.with(DemoApplication.getContext()).load(url).into(imageView);
//        ImageLoaderUtils.getInstance().displayImage(url, imageView);
    }
}
