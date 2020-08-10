package com.example.ismydatasecure;

import android.content.Context;
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

    public WebsiteAdapter(Context context, ArrayList<WebsiteItem> websiteList){
        mContext = context;
        mWebsiteList = websiteList;

    }

    @NonNull
    @Override
    public WebsiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.website_item, parent, false);
        return new WebsiteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WebsiteViewHolder holder, int position) {
    WebsiteItem currentItem = mWebsiteList.get(position);
    String imageUrl = currentItem.getmImageUrl();
    String titleName = currentItem.getmTitle();
    int blurbText = currentItem.getmPwncount();
    String date = currentItem.getmDate();

    holder.mTextViewTitle.setText(titleName);
    holder.mTextViewBlurb.setText(blurbText + " Leaked Accounts");
    Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
    holder.mTextViewDate.setText("Date of Breach: " + date);

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

        public WebsiteViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewTitle = itemView.findViewById(R.id.title_view);
            mTextViewBlurb = itemView.findViewById(R.id.pwncount_view);
            mTextViewDate = itemView.findViewById(R.id.date_view);
        }
    }

}
