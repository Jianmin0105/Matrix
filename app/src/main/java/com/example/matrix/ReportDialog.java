package com.example.matrix;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReportDialog extends Dialog {
    private int cx;
    private int cy;
    private RecyclerView mRecyclerView;
    private ReportRecyclerViewAdapter mRecyclerViewAdapter;

    public ReportDialog(@NonNull Context context) {
        this(context, R.style.MyAlertDialogStyle);
    }

    public ReportDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View dialogView = View.inflate(getContext(), R.layout.dialog, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(dialogView);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialogInterface) {
//                animateDialog(dialogView, true);
//            }
//        });
//
//        setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
//                if (i == KeyEvent.KEYCODE_BACK) {
//                    animateDialog(dialogView, false);
//                    return true;
//                }
//                return false;
//            }
//        });

        setupRecyclerView(dialogView);

    }

    private void setupRecyclerView(View dialogView) {
        mRecyclerView = dialogView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerViewAdapter = new ReportRecyclerViewAdapter(getContext(), Config.listItems);
        mRecyclerViewAdapter.setClickListener(new ReportRecyclerViewAdapter
                .OnClickListener() {
            @Override
            public void setItem(String item) {
                // for switch item
            }
        });

        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }
}