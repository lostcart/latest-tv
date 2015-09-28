package apps.lost.latesttv.shows;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import apps.lost.latesttv.R;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * View holder for handling each show item
 *
 * @author luke
 */
public class ShowViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.show_item_imageview_image)
    ImageView mImageView;

    public ShowViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setShow(Show show) {
        Glide.with(mImageView.getContext()).load(show.getImageUrl()).into(mImageView);
    }
}