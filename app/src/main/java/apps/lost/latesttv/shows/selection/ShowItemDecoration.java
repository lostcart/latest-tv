package apps.lost.latesttv.shows.selection;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ShowItemDecoration extends RecyclerView.ItemDecoration {

    private int mColumns;
    private int mSpacing;
    private boolean mIncludeEdge;

    public ShowItemDecoration(Context context, int columns, int spacingDimensionId, boolean includeEdge) {
        mColumns = columns;
        mSpacing = context.getResources().getDimensionPixelSize(spacingDimensionId);
        mIncludeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % mColumns; // item column

        if (mIncludeEdge) {
            outRect.left = mSpacing - column * mSpacing / mColumns; // mSpacing - column * ((1f / mColumns) * mSpacing)
            outRect.right = (column + 1) * mSpacing / mColumns; // (column + 1) * ((1f / mColumns) * mSpacing)

            if (position < mColumns) { // top edge
                outRect.top = mSpacing;
            }
            outRect.bottom = mSpacing; // item bottom
        } else {
            outRect.left = column * mSpacing / mColumns; // column * ((1f / mColumns) * mSpacing)
            outRect.right = mSpacing - (column + 1) * mSpacing / mColumns; // mSpacing - (column + 1) * ((1f /    mColumns) * mSpacing)
            if (position >= mColumns) {
                outRect.top = mSpacing; // item top
            }
        }
    }
}