package com.meidp.itcradle.activity;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.meidp.itcradle.R;
import com.meidp.itcradle.adapter.ExpandableAdapter;
import com.meidp.itcradle.model.Child;
import com.meidp.itcradle.model.Group;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.activity_client_manager)
public class ClientManagerActivity extends BaseActivity implements ExpandableListView.OnChildClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.expListView)
    protected ExpandableListView expListView;

    private List<Group> list = new ArrayList<Group>();

    @Override
    public void onInitView() {
        title.setText("客户管理");
        Group g = new Group();

        g.setTitle("东莞");
        ArrayList<Child> childList = new ArrayList<Child>();
        childList.add(new Child(R.mipmap.ic_launcher, "东莞三星电子经销商"));
        childList.add(new Child(R.mipmap.ic_launcher, "东莞华为电子经销商"));
        g.setChildList(childList);

        Group g1 = new Group();
        g1.setTitle("深圳");
        ArrayList<Child> childList1 = new ArrayList<Child>();
        childList1.add(new Child(R.mipmap.ic_launcher, "深圳三星电子经销商"));
        childList1.add(new Child(R.mipmap.ic_launcher, "深圳华为电子经销商"));


        g1.setChildList(childList1);

        list.add(g);
        list.add(g1);

        expListView.setOnChildClickListener(this);

        expListView.setAdapter(new ExpandableAdapter(list, this));
        expListView.expandGroup(0);
    }

    @Event(value = {R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(this, list.get(groupPosition).getChildList().get(childPosition).getName(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
