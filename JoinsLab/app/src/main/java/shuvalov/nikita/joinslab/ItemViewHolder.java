package shuvalov.nikita.joinslab;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by NikitaShuvalov on 10/28/16.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView;
    public ItemViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.holder_text);
    }
}
