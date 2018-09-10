package com.credit.exg.exg.adapter;

import android.content.Context;

import com.credit.exg.exg.R;
import com.credit.exg.exg.bean.RepairBean;

import java.util.List;

/**
 * Created by zhangjiaqi on 18/8/29.
 */

public class RepairAdapter extends SimpleAdapter<RepairBean> {
    public RepairAdapter(Context context, List<RepairBean> datas) {
        super(context, R.layout.item_repair, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, RepairBean item) {
        viewHoder.getTextView(R.id.item_number).setText(item.getNumber());
        viewHoder.getTextView(R.id.item_company).setText(item.getCompany());
        viewHoder.getTextView(R.id.item_name).setText(item.getName());
        viewHoder.getTextView(R.id.item_area).setText(item.getArea());
        viewHoder.getTextView(R.id.item_time).setText(item.getTime());
        viewHoder.getTextView(R.id.item_btn_1).setOnClickListener(viewHoder);
        viewHoder.getTextView(R.id.item_btn_2).setOnClickListener(viewHoder);
        viewHoder.getView(R.id.item_rel).setOnClickListener(viewHoder);
    }
}
