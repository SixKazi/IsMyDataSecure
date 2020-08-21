package com.example.ismydatasecure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
/*
*  Class for Fragment Three; Tips
* Suppression for warnings on safe conditions
*/

@SuppressWarnings({"ConstantConditions", "unchecked"})
public class ThirdFragment extends Fragment {
public SearchView search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = view.findViewById(R.id.search_bar);

        //Generate Tip List with MainActivity Method genTips & Set to RecyclerView
        final ArrayList<Tip> tipList = ((MainActivity)getActivity()).genTips();
        RecyclerView mRecyclerview = view.findViewById(R.id.tip_view);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));
        final RecAdapter recAdapter = new RecAdapter(getContext(), tipList);
        mRecyclerview.setAdapter(recAdapter);

        //Search Bar On Query Listener for instant filtering
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                recAdapter.getFilter().filter(newText);
                return false;
            }
        });
        //OnClickListener to handle click events on Tips, To expand view to show main tip text
        recAdapter.setOnItemClickListener(new RecAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //noinspection ConstantConditions
                ((MainActivity)getActivity()).hideKeyboard();
                boolean expanded = tipList.get(position).isExpanded();
                tipList.get(position).setExpanded(!expanded);
                recAdapter.notifyItemChanged(position);
            }
        });
    }
}