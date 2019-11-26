package com.pmirkelam.wowchars;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.pmirkelam.wowchars.databinding.ItemCharBinding;

import java.util.List;

public class CharAdapter extends RecyclerView.Adapter<CharAdapter.CharViewHolder> {

        private List<Char> chars;
        private int limit = 10;
        private MutableLiveData<Char> selectedCharName = new MutableLiveData<>();

        public MutableLiveData<Char> getSelectedCharName(){
            return selectedCharName;
        }

        @NonNull
        @Override
        public CharViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ItemCharBinding itemCharBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                            R.layout.item_char, viewGroup, false);
            return new CharViewHolder(itemCharBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull CharViewHolder charViewHolder, int i) {
            charViewHolder.itemCharBinding.setWChar(chars.get(i));
        }

        @Override
        public int getItemCount() {
            if(chars != null) {
                if(chars.size() > limit){
                    return limit;
                } else {
                    return chars.size();
                }
            } else {
                return 0;
            }

        }

        public void setCharList(List<Char> chars) {
            this.chars = chars;
            notifyDataSetChanged();
        }

        class CharViewHolder extends RecyclerView.ViewHolder {

            private ItemCharBinding itemCharBinding;

            public CharViewHolder(@NonNull final ItemCharBinding itemCharBinding) {
                super(itemCharBinding.getRoot());
                this.itemCharBinding = itemCharBinding;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Char wChar = itemCharBinding.getWChar();
                        Log.i("CharAdapter", "Selected char: " + wChar.toString());
                        selectedCharName.setValue(wChar);
                    }
                });
            }
        }
    }

