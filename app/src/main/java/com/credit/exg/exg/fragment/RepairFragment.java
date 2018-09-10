package com.credit.exg.exg.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.credit.exg.exg.R;
import com.credit.exg.exg.adapter.BaseAdapter;
import com.credit.exg.exg.adapter.RepairAdapter;
import com.credit.exg.exg.base.IViewFragment;
import com.credit.exg.exg.bean.BaseBean;
import com.credit.exg.exg.bean.RepairBean;
import com.credit.exg.exg.mvp.repair.RepairContrac;
import com.credit.exg.exg.mvp.repair.RepairPresenter;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjiaqi on 18/8/28.
 */

public class RepairFragment extends IViewFragment<RepairPresenter> implements RepairContrac.View {

    @ViewInject(R.id.rel_all)
    RelativeLayout relAll;
    @ViewInject(R.id.rel_my)
    RelativeLayout relMy;
    @ViewInject(R.id.line1)
    View line1;
    @ViewInject(R.id.line2)
    View line2;
    @ViewInject(R.id.all_num)
    TextView allNum;
    @ViewInject(R.id.my_num)
    TextView myNum;

    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerView;
    RepairAdapter adapter;
    List<RepairBean> list;

    @ViewInject(R.id.change_iv)
    ImageView changeIv;
    boolean change = false;
    int tag = 0;//表示全部工单

    @Override
    protected void init() {
        changeIv.setOnClickListener(this);
    }

    @Override
    protected void onCreateInit() {
        line2.setVisibility(View.GONE);
        relAll.setOnClickListener(this);
        relMy.setOnClickListener(this);
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RepairBean bean = new RepairBean();
            bean.setNumber("BQ-201708091123");
            bean.setCompany("杭州沃德塑胶有限公司");
            bean.setName("李倩");
            bean.setTime("2016-07-09 11:00");
            bean.setArea("杭州市星桥街星桥北路66号");
            bean.setStatus("1");
            list.add(bean);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RepairAdapter(getActivity(), list);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.item_btn_1:
                        break;
                    case R.id.item_btn_2:
                        break;
                    case R.id.item_rel:
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_repair, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_all:
                if (tag == 1) {
                    line1.setVisibility(View.VISIBLE);
                    line2.setVisibility(View.GONE);
                    tag = 0;
                }
                break;
            case R.id.rel_my:
                if (tag == 0) {
                    line2.setVisibility(View.VISIBLE);
                    line1.setVisibility(View.GONE);
                    tag = 1;
                }

                break;
        }
    }


    @Override
    public void result(RepairBean data) {

    }

    @Override
    public void error(String e) {

    }

    @Override
    public void zzqxResult(BaseBean data) {

    }

    @Override
    public void zzqxError(String e) {

    }

    @Override
    public void bxjdResult(BaseBean data) {

    }

    @Override
    public void bxjdError(String e) {

    }

    @Override
    public void ddxcResult(BaseBean data) {

    }

    @Override
    public void ddxcError(String e) {

    }

    @Override
    protected RepairPresenter onLoadPresenter() {
        return RepairPresenter.getInstance();
    }


}
