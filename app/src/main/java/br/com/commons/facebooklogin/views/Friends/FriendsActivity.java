package br.com.commons.facebooklogin.views.Friends;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.List;

import br.com.commons.facebooklogin.R;
import br.com.commons.facebooklogin.dto.FriendFacebookDTO;
import br.com.commons.facebooklogin.objects.BitmapLruCache;
import br.com.commons.facebooklogin.objects.Friend;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView friendsRecyclerView;
    private RecyclerView.Adapter friendsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        friendsRecyclerView = (RecyclerView) findViewById(R.id.friends_list);
        friendsRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        friendsRecyclerView.setLayoutManager(mLayoutManager);

        final ImageLoader.ImageCache imageCache = new BitmapLruCache();
        final ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(this), imageCache);

        // Get friends list
        FriendFacebookDTO friendsDTO = (FriendFacebookDTO) getIntent().getSerializableExtra("friend_list_dto");
        List<Friend> data = friendsDTO.getData();
        friendsAdapter = new FriendsAdapter(data , imageLoader);
        friendsRecyclerView.setAdapter(friendsAdapter);
    }
}
