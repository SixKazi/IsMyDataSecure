package com.example.ismydatasecure;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> {

    private List<Tip> list;
    private Context mContext;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);}
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public RecAdapter(Context context, List<Tip> list) {
        mContext = context;
        this.list = list;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.tip_view, parent, false);
        return new RecViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, final int position) {
        final Tip tip = list.get(position);

        holder.bind(tip);
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean expanded = tip.isExpanded();
                tip.setExpanded(!expanded);
                RecAdapter.this.notifyItemChanged(position);
            }
        }); */
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView category;
        private TextView info;
        private View subItem;


        public RecViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            category = itemView.findViewById(R.id.sub_item_category);
            info = itemView.findViewById(R.id.sub_item_info);
            subItem = itemView.findViewById(R.id.sub_item);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            Log.d("click","" + pos);
                            listener.onItemClick(pos);
                        }
                    }
                }
            });




        }

        private void bind(Tip tip) {
            boolean expanded = tip.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            title.setText(tip.getTitle());
            category.setText(Html.fromHtml(tip.getCategory()));
            info.setText(Html.fromHtml(tip.getInfo()));
        }
    }
}