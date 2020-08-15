package com.example.ismydatasecure;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WebsiteAdapter extends RecyclerView.Adapter<WebsiteAdapter.WebsiteViewHolder> {
    private Context mContext;
    private ArrayList<WebsiteItem> mWebsiteList;
    private WebsiteAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);}

    public void setOnItemClickListener(WebsiteAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public WebsiteAdapter(Context context, ArrayList<WebsiteItem> websiteList){
        mContext = context;
        mWebsiteList = websiteList;
    }

    @NonNull
    @Override
    public WebsiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.website_item, parent, false);
        return new WebsiteViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WebsiteViewHolder holder, final int position) {
        final WebsiteItem item = mWebsiteList.get(position);

        holder.bind(item);


    }

    @Override
    public int getItemCount() {
        return mWebsiteList.size();
    }

    public class WebsiteViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewTitle;
        public TextView mTextViewBlurb;
        public TextView mTextViewDate;
        public TextView mText;
        public View subItem;

        public WebsiteViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewTitle = itemView.findViewById(R.id.title_view);
            mTextViewBlurb = itemView.findViewById(R.id.pwncount_view);
            mTextViewDate = itemView.findViewById(R.id.date_view);
            subItem = itemView.findViewById(R.id.sub_item);
            mText = itemView.findViewById(R.id.sub_item_info);

            itemView.setOnClickListener(new View.OnClickListener() {
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
        private void bind(WebsiteItem item) {
            boolean expanded = item.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            mTextViewTitle.setText(item.getmTitle());
            Picasso.get().load(item.getmImageUrl()).fit().centerInside().into(mImageView);
            mTextViewBlurb.setText(item.getmPwncount() + " Leaked Accounts");
            mTextViewDate.setText(item.getmDate());
            mText.setText(Html.fromHtml(item.getText()));

        }
    }

}
