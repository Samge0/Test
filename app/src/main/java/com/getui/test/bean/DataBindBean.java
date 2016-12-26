package com.getui.test.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * 创建者     shaochengbao
 * 公司       深圳前瞻资讯股份有限公司
 * 创建时间   2016/12/22 21:00
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class DataBindBean extends BaseObservable {
    @Bindable
    public String name;
    @Bindable
    public String sex;
    @Bindable
    public String imgUrl;
    @Bindable
    public boolean isSex;

    public DataBindBean(String name, String sex, boolean b) {
        this.name = name;
        this.sex = sex;
        this.isSex= b;
    }
    public DataBindBean(String name, String sex, String imgUrl,boolean b) {
        this(name,sex,b);
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
        notifyPropertyChanged(BR.name);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex=sex;
        notifyPropertyChanged(BR.sex);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl=imgUrl;
        notifyPropertyChanged(BR.imgUrl);
    }
}
