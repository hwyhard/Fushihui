package com.hwyhard.www.fushihui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hwyhard.www.fushihui.R;
import com.hwyhard.www.fushihui.activity.ItemActivity;
import com.hwyhard.www.fushihui.adapter.MessageAdapter;
import com.hwyhard.www.fushihui.bean.MessageBean;
import com.melnykov.fab.FloatingActionButton;
import com.tiancaicc.springfloatingactionmenu.OnMenuActionListener;
import com.tiancaicc.springfloatingactionmenu.SpringFloatingActionMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hwyhard on 17/4/18.
 * 主要的fragment页面
 */

public class MainFragment extends Fragment {
    RecyclerView messageRv;
    //创建测试用的url的数组
    String testUrl[] = new String[13];
    LinearLayoutManager linearLayoutManager;
    List<MessageBean> list;
    List<MessageBean> movieList;
    List<MessageBean> lifeList;
    MessageAdapter messageAdapter;
    View.OnClickListener fabItemClickListener;
    Context mContext;
    private SpringFloatingActionMenu springFloatingActionMenu;
    public final String KEY_TAG = "keyTag";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        messageRv = (RecyclerView) view.findViewById(R.id.main_rv_list);
        mContext = getActivity();//获得上下文
        //初始化布局管理器
        linearLayoutManager = new LinearLayoutManager(mContext);
        list = new ArrayList<>();
        movieList = new ArrayList<>();
        lifeList = new ArrayList<>();
        initMovieList(10);
        initLifeList(10);
        list.addAll(movieList);
        list.addAll(lifeList);
        randomList();
        //初始化数据
        //创建FabMenu里Item的监听器
        fabItemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, ItemActivity.class));
//
//                MenuItemView menuItem = (MenuItemView) view;
//                String tag = (String) menuItem.getLabelTextView().getText();
//                switch(tag){
//                    case "电影":
//                        break;
//                    case "音乐":
//                        break;
//                    case "阅读":
//                        break;
//                    case "生活":
//                        break;
//                    case "历史":
//                        break;
//                    default:
//                        break;
//                }
            }
        };
        //手动创建fab
        final FloatingActionButton fab = new FloatingActionButton(mContext);
        //菜单button大小
        fab.setType(FloatingActionButton.TYPE_NORMAL);
        // button icon
        fab.setImageResource(R.mipmap.button_category);
        fab.setColorNormalResId(R.color.colorPrimaryGreen);
        fab.setColorPressedResId(R.color.colorGrayGreen);
        fab.setColorRippleResId(R.color.colorRippleGreen);
        fab.setShadow(true);
        messageAdapter = new MessageAdapter(list);
        springFloatingActionMenu = new SpringFloatingActionMenu.Builder(mContext)
                .fab(fab)
                //添加菜单按钮参数依次是背景颜色，图标，标签，标签的颜色，点击事件
                .addMenuItem(R.color.colorRandom, R.mipmap.button_random, "随便看看", R.color.colorRandom, new View.OnClickListener() {
                    @Override
                    //点击后生成随机列表
                    public void onClick(View view) {
                        randomList();
//                        messageAdapter.notifyDataSetChanged();
                        messageAdapter = new MessageAdapter(list);
                        onItemClick();
                        messageRv.setAdapter(messageAdapter);
                        springFloatingActionMenu.hideMenu();
                    }
                })
                .addMenuItem(R.color.colorMovie, R.mipmap.button_movie, "影视", R.color.colorLable, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        messageAdapter = new MessageAdapter(movieList);
                        onItemClick();
                        messageRv.setAdapter(messageAdapter);
                        springFloatingActionMenu.hideMenu();
                    }
                })
                .addMenuItem(R.color.colorMusic, R.mipmap.button_music, "音乐", R.color.colorLable, fabItemClickListener)
                .addMenuItem(R.color.colorBook, R.mipmap.button_book, "阅读", R.color.colorLable, fabItemClickListener)
                .addMenuItem(R.color.colorLife, R.mipmap.button_life, "生活", R.color.colorLable, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        messageAdapter = new MessageAdapter(lifeList);
                        onItemClick();
                        messageRv.setAdapter(messageAdapter);
                        springFloatingActionMenu.hideMenu();
                    }
                })
                .addMenuItem(R.color.colorHistory, R.mipmap.button_history, "历史", R.color.colorLable, fabItemClickListener)
                //设置菜单展开后的动画
                .animationType(SpringFloatingActionMenu.ANIMATION_TYPE_TUMBLR)
                .revealColor(R.color.colorPrimaryGreen)
                .gravity(Gravity.RIGHT | Gravity.BOTTOM)
                .onMenuActionListner(new OnMenuActionListener() {
                    @Override
                    public void onMenuOpen() {
                        fab.setImageResource(R.mipmap.button_cancel);
                        fab.setColorNormalResId(R.color.colorFabBack);
                    }

                    @Override
                    public void onMenuClose() {
                        fab.setImageResource(R.mipmap.button_category);
                        fab.setColorNormalResId(R.color.colorPrimaryGreen);
                    }
                }).build();
//        fab.attachToRecyclerView(messageRv);
        messageRv.setHasFixedSize(true);
        //将布局管理器绑定到视图
        messageRv.setLayoutManager(linearLayoutManager);
        // 为item设置点击事件
        onItemClick();
        //为视图绑定数据监听器
        messageRv.setAdapter(messageAdapter);
        return view;

    }

//    private List<MessageBean> createBeanList(int num) {
//        for (int i = 0; i < num; i++) {
//            if (i < 13) {
//                MessageBean messageBean = new MessageBean(testUrl[i], "爱要怎么说出口" + i, "你劝我灭了心中的火" + i, "April 7" + i);
//                list.add(messageBean);
//            } else {
//                int index = i % 13;
//                MessageBean messageBean = new MessageBean(testUrl[index], "你一直在往我的剑上撞" + i, "死亡如风，常伴吾身" + i, "April 8" + i);
//                list.add(messageBean);
//            }
//            Log.d("MainActivityUrl", list.get(i).getItemPic());
//
//        }
//        return list;
//    }
//
//    //初始化供测试的url
//    private void initUrl() {
//        for (int i = 0; i < 13; i++) {
//            int index = i + 12;
//            testUrl[i] = "http://img.ivsky.com/img/bizhi/pre/201406/19/iron_man-0" + index + ".jpg";
//        }


    //打乱列表生成随机列表
    public void randomList() {
        Collections.shuffle(list);
    }

    //item点击事件
    public void onItemClick() {
        messageAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "click----->" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ItemActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initMovieList(int num) {
        for (int i = 0; i < num; i++) {
            movieList.add(new MessageBean("http://www.twwiki.com/uploads/wiki/ee/03/786052_0.jpg", "菊次郎的夏天", "回忆充满夏天气息的童年之旅", i + ""));
            movieList.add(new MessageBean("http://img.ivsky.com/img/bizhi/pre/201604/23/wozaigugong_xiuwenwu-004.jpg", "我在故宫修文物", "择一事，终一生", "" + i));
            movieList.add(new MessageBean("http://image11.m1905.cn/uploadfile/2009/0812/095317591.jpg", "第九区", "你可能会爱上这种长得很丑的外星人", "" + i));
            movieList.add(new MessageBean("http://www.hanguobo.com/resources/se2/upload/2_s.png", "奸臣", "它哪都好，就是不太好找", "" + i));
            movieList.add(new MessageBean("http://p2.cri.cn/M00/54/A8/CqgNOldbZx-AGd2QAAAAAAAAAAA821.700x467.jpg","小姐", "这部片的内容可能和你想象的不太一样", "" + i));
            movieList.add(new MessageBean("http://upload-images.jianshu.io/upload_images/2062476-62048baf95b97592.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1536/q/100", "绝命毒师", "老天你一定是在逗我", "" + i));
        }


    }
    public void initLifeList(int num){
        for(int i = 0; i < num; i++){
            lifeList.add(new MessageBean("https://tide.zjuqsc.com/wp/wp-content/uploads/2014/12/20120310021614_HjRnE.jpeg","人间失格","很抱歉我失去了做人的资格",i+""));
            lifeList.add(new MessageBean("https://pgw.udn.com.tw/gw/photo.php?u=https://uc.udn.com.tw/photo/2017/02/24/4/3214147.jpg&x=0&y=0&sw=0&sh=0&sl=W&fw=1050&exp=3600","金州勇士","爱上一场认真的消遣",i+""));
            lifeList.add(new MessageBean("http://m-miya.net/blog/wp-content/uploads/2014/03/00-086-1_min.jpg","浮世绘","这世界很美",i+""));
            lifeList.add(new MessageBean("http://b.blog.xuite.net/b/2/d/e/12584724/blog_32120/txt/382381113/2.jpg","本能寺","那一夜那里发生的事改写了日本历史",i+""));
            lifeList.add(new MessageBean("http://img.taopic.com/uploads/allimg/120209/2195-120209103J585.jpg","中世纪骑士","如果他们穿越回来依然很强",i+""));
            lifeList.add(new MessageBean("https://newsneakernews-wpengine.netdna-ssl.com/wp-content/uploads/2017/02/adidas-yeezy-boost-350-v2-zebra-official-images-2.jpg","Yeezy350 Zebra","道理我都懂，它为什么这么难买",""+i));

        }
    }


}
