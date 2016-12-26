package com.getui.test.model;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * 创建者     shaochengbao
 * 公司       深圳前瞻资讯股份有限公司
 * 创建时间   2016/12/22 21:13
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class MyBindModel extends ViewDataBinding {
    protected MyBindModel(DataBindingComponent bindingComponent, View root, int localFieldCount) {
        super(bindingComponent, root, localFieldCount);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override
    public boolean setVariable(int variableId, Object value) {
        return false;
    }

    @Override
    protected void executeBindings() {

    }

    @Override
    public void invalidateAll() {

    }

    @Override
    public boolean hasPendingBindings() {
        return false;
    }
}
