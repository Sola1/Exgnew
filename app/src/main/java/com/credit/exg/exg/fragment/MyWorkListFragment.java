package com.credit.exg.exg.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.credit.exg.exg.R;
import com.credit.exg.exg.adapter.BaseAdapter;
import com.credit.exg.exg.adapter.RepairAdapter;
import com.credit.exg.exg.base.BaseFragment;
import com.credit.exg.exg.bean.RepairBean;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjiaqi on 18/8/28.
 */

public class MyWorkListFragment extends BaseFragment{

    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerView;
    RepairAdapter adapter;
    List<RepairBean> list;
    @Override
    protected void init() {

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

    public static Fragment createListViewFragment() {
        Fragment fragment = new MyWorkListFragment();
        return fragment;
    }


    @Override
    protected void onCreateInit() {

    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_my_work_list,container,false);
    }

    @Override
    public void onClick(View view) {

    }
}
