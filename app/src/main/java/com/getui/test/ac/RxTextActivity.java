package com.getui.test.ac;

import android.app.Activity;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.getui.test.DemoApplication;
import com.getui.test.R;
import com.getui.test.databinding.RxTextBind;
import com.jakewharton.rxbinding.view.RxView;
import com.socks.library.KLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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


    private static final String TAG="RxTextActivity";
    private RxTextBind mBinding;
    private ArrayList<String> mList;
    private List<String> mArrayList;
    private long mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=DataBindingUtil.setContentView(this, R.layout.activity_rx);
        mBinding.setClick(this);

        initData();

        initListener();
    }

    private void initListener() {
        //对RxBinding进行测试
        RxView.clicks(mBinding.rxBind)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewAttachEvent -> {
                    KLog.d(TAG, "RxBinding++time== " + ((System.currentTimeMillis() - mTime) / 1000) +" 秒");
                    Toast.makeText(DemoApplication.getContext(), "RxBind",Toast.LENGTH_SHORT).show();
                    mTime=System.currentTimeMillis();
                });
    }

    private void initData() {
        mList=new ArrayList<>();
        mList.add("a");
        mList.add("b");
        mList.add("c");
    }

    public void bindClick(View view) {
        switch (view.getId()) {
            case R.id.say_hello:
                //                Toast.makeText(DemoApplication.getContext(),"helloWord",Toast.LENGTH_SHORT).show();

                /*被观察的对象--observable*/
                Observable<Integer> observable=Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        /*被观察对象执行的动作*/
                        subscriber.onNext(99 * 3);//动作1
                        subscriber.onNext(2 * 8);//动作2
                        subscriber.onNext(8);//动作3
                        subscriber.onCompleted();//动作结束
                    }
                });


                /*订阅对象，即是：被观察者数据变化时，这订阅对象中会受到被观察者的一系列变化动作*/
                Subscriber subscriber=new Subscriber<Integer>() {
                    int i=1;

                    @Override
                    public void onCompleted() {
                        //被观察者的动作执行完毕
                    }

                    @Override
                    public void onError(Throwable e) {
                        //执行错误

                    }

                    @Override
                    public void onNext(Integer integer) {
                        //这里接收被观察者 --observable 的onNext()方法所执行的每一步动作,,,,这里选择将这些动作打印出来
                        Toast.makeText(DemoApplication.getContext(), "步骤" + i + " 结果====" + integer, Toast.LENGTH_SHORT).show();
                        i++;
                    }
                };


                //进行事件的订阅
                observable.subscribe(subscriber);//完成订阅
                //                subscriber.unsubscribe();//结束后应该取消订阅。防止内存泄漏


                break;

            case R.id.just:

                //使用just方法，just里传入什么 ，则在订阅者的onNext()方法中传出什么
                Observable.just(mList).subscribe(new Subscriber<ArrayList<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<String> list) {
                        //这里直接获取被观察者中所观察的list对象
                        Toast.makeText(DemoApplication.getContext(), "just方法直接获取传入的对象==" + list.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.from:
                Observable.from(mList).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        //这里直接获取被观察者中list中的每一个item
                        Toast.makeText(DemoApplication.getContext(), "mList的第 " + (mList.indexOf(s) + 1) + " 个孩子是" + s, Toast.LENGTH_SHORT).show();

                    }
                });
                break;

            case R.id.simply:
                Observable.just("lambda表达式").subscribe(s -> Toast.makeText(DemoApplication.getContext(), s, Toast.LENGTH_SHORT).show());
                /*Observable.just("lambda表达式").subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(DemoApplication.getContext(), s, Toast.LENGTH_SHORT).show();

                    }
                });*/
                break;

            /*操作符,可以在被观察者传入的数据基础上再对数据进行变换操作*/
            case R.id.map:
               /* Observable.just("操作符（这是原始数据）--")
                        .map(new Func1<String, String>() {
                            @Override
                            public String call(String s) {
                                return s + "start（分割线）--这是使用操作符增加的内容end";
                            }
                        })
                        .subscribe(s -> Toast.makeText(DemoApplication.getContext(), s, Toast.LENGTH_SHORT).show());*/

                /*使用lambda表达式来执行，使语句更简洁*/
                Observable.just("原始数据")
                        .map(s -> "操作符添加头+++" + s)
                        .map(s -> s + "+++添加尾部")
                        .map(s -> {
                            Log.d("LAMBDA", s);
                            return "\n" + s + "+++可以多行,使用{}大括号即可";
                        })
                        .subscribe(s -> Toast.makeText(DemoApplication.getContext(), s, Toast.LENGTH_SHORT).show());
                break;

            case R.id.just_moren:
                Observable.just("参数1", "参数2", "参数3")
                        .subscribe(new Action1<String>() {
                            int y=1;

                            @Override
                            public void call(String s) {
                                Toast.makeText(DemoApplication.getContext(), String.valueOf(y) + "、" + s, Toast.LENGTH_SHORT).show();
                                y++;
                            }
                        });
                break;

            case R.id.just_list:
                /*Observable.flatMap()接收一个Observable的输出作为输入，同时输出另外一个Observable。*/
                mArrayList=new ArrayList<>();
                mArrayList.addAll(Arrays.asList("list1", "list2", "list3", "list4"));
                Observable.just(mArrayList)
                        .map(strings -> {
                            strings.add("添加数据5");
                            strings.add("添加数据6");
                            return strings;
                        })
                        .subscribeOn(Schedulers.io())
                        .flatMap(strings -> Observable.from(strings))//链式调用
                        .filter(s -> !s.contains("1"))//过滤，输出不包含1的元素
                        .take(6)//指定输出的最大数量
                        .map(s -> s.contains("2") ? s + "+++追加的数据" : s)
                        .doOnNext(s -> KLog.d(TAG, "doOnNext()允许我们在每次输出一个元素之前做一些额外的事情===s=" + s))
                        .subscribeOn(Schedulers.io())//subscribeOn--标志以上的操作在指定的操作在io线程中执行
                        .observeOn(AndroidSchedulers.mainThread())//observeOn--使下面语句在UI线程执行
                        .subscribe(o -> Toast.makeText(DemoApplication.getContext(), o, Toast.LENGTH_SHORT).show());
                break;

            default:
                break;
        }
    }

}
