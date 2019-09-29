package com.xiaoge.org.activity.demo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoge.org.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ListViewActivityX extends AppCompatActivity {
    @BindView(R.id.lv_tt)
    ListView listView;

    List<String> datas = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_x);

        ButterKnife.bind(this);
        initData();
        initView();

        test();
    }

    private void test() {
        Lock lock = new ReentrantLock();
        ((ReentrantLock) lock).hasQueuedThreads();
        lock.lock();
        lock.lock();
        Log.i("lock", "---------------");
        int holdCount = ((ReentrantLock) lock).getHoldCount();
        Log.i("lock", "--------------- " + holdCount);
        lock.unlock();
        lock.unlock();


    }

    private void initView() {
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            datas.add(String.valueOf(i));
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(ListViewActivityX.this);
            tv.setText(datas.get(position));
            return tv;
        }
    }
}
