package com.example.mwael.scrollingnumper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private int firstVisibleInListview = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerViewObject = (RecyclerView) findViewById(R.id.rvDigit);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewObject.setLayoutManager(layoutManager);
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewObject);

        recyclerViewObject.setOnFlingListener(snapHelper);

        final ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        final ScrollingAdapter adapter = new ScrollingAdapter(numbers, recyclerViewObject);
        recyclerViewObject.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new MoRecyclerEndlessAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                adapter.addMoreItems(numbers);
            }
        });

        firstVisibleInListview = layoutManager.findFirstVisibleItemPosition();
        recyclerViewObject.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findFirstCompletelyVisibleItemPosition() != -1) {
                    String s = ((ScrollingAdapter.ScrollingNumberViewHolder) recyclerView.findViewHolderForAdapterPosition(layoutManager.findFirstCompletelyVisibleItemPosition())).tvDigit.getText().toString();
                    Log.d("currentPosition", layoutManager.findFirstCompletelyVisibleItemPosition() + "");
                    Log.d("currentItem", s);
                }
            }
        });
    }
}
