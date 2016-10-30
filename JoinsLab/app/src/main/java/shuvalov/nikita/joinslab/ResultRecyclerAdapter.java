package shuvalov.nikita.joinslab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by NikitaShuvalov on 10/28/16.
 */

public class ResultRecyclerAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    List<String> mQueriedResults;

    public ResultRecyclerAdapter(List<String> queriedResults) {
        mQueriedResults = queriedResults;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_form, null));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.mTextView.setText(mQueriedResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mQueriedResults.size();
    }

    public void replaceData(List<String> newList){
        mQueriedResults =newList;
        notifyDataSetChanged();
    }
}
