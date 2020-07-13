package com.awan.uas.comp;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class RecyclerViewMargin extends RecyclerView.ItemDecoration
{
    private int rightMargin, leftMargin, topMargin, bottomMargin;

    public RecyclerViewMargin(int topMargin, int rightMargin, int bottomMargin, int leftMargin)
    {
        this.topMargin = topMargin;
        this.rightMargin = rightMargin;
        this.bottomMargin = bottomMargin;
        this.leftMargin = leftMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state)
    {
        outRect.right = rightMargin;
        outRect.bottom = bottomMargin;
        outRect.top = topMargin;
        outRect.left = leftMargin;

    }
}