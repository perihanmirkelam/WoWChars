package com.pmirkelam.wowchars.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pmirkelam.wowchars.Char;
import com.pmirkelam.wowchars.CharAdapter;
import com.pmirkelam.wowchars.R;
import com.pmirkelam.wowchars.databinding.FragmentSearchBinding;
import com.pmirkelam.wowchars.ui.detail.DetailActivity;

import java.util.List;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;
    private RecyclerView recyclerViewSearch;
    private CharAdapter charAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        View view = binding.getRoot();
        binding.setLifecycleOwner(this);
        binding.setSearchViewModel(searchViewModel);

        recyclerViewSearch = binding.recyclerViewSearch;
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSearch.setHasFixedSize(true);

        charAdapter = new CharAdapter();
        recyclerViewSearch.setAdapter(charAdapter);
        recyclerViewSearch.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchViewModel.getSearchedChars().observe(this, new Observer<List<Char>>() {
            @Override
            public void onChanged(List<Char> chars) {
                charAdapter.setCharList(chars);
                charAdapter.notifyDataSetChanged();
            }
        });
        charAdapter.getSelectedCharName().observe(this, new Observer<Char>() {
            @Override
            public void onChanged(Char wChar) {
                searchViewModel.setSelectedChar(wChar);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
