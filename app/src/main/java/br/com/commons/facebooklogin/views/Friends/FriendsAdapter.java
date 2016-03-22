/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.commons.facebooklogin.views.Friends;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import br.com.commons.facebooklogin.R;
import br.com.commons.facebooklogin.objects.Friend;

/**
 * Created by Mateus Emanuel Araújo on 19/03/16.
 * MA Solutions
 * teusemanuel@gmail.com
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private final List<Friend> friends;
    private final ImageLoader imageLoader;

    public FriendsAdapter(List<Friend> friends, ImageLoader imageLoader) {
        this.friends = friends;
        this.imageLoader = imageLoader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friends_cell, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Friend friend = friends.get(position);

        holder.imgFriend.setImageUrl(friend.getPicture().getData().getUrl(), imageLoader);
        holder.imgFriend.setDefaultImageResId(R.drawable.com_facebook_profile_picture_blank_square);
        holder.imgFriend.setErrorImageResId(R.drawable.com_facebook_profile_picture_blank_square);
        holder.friendName.setText(friend.getName());
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public NetworkImageView imgFriend;
        public TextView friendName;
        public ViewHolder(View v) {
            super(v);
            imgFriend = (NetworkImageView) v.findViewById(R.id.img_friend);
            friendName = (TextView) v.findViewById(R.id.friend_name);

        }
    }
}
