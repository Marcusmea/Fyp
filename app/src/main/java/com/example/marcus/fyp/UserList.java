package com.example.marcus.fyp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.marcus.fyp.Model.User;

import java.util.List;

public class UserList extends ArrayAdapter<User> {

    private Activity context;
    private List<User> userList;

    public UserList(Activity context, List<User> userList){
        super(context,R.layout.list_layout, userList);
        this.context=context;
        this.userList=userList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem= inflater.inflate(R.layout.list_layout,null,true);
       // TextView textViewUserID= (TextView)listViewItem.findViewById(R.id.tvUserID);
        TextView textViewUserEmail= (TextView)listViewItem.findViewById(R.id.tvuseremail);
        TextView textViewUserphnumber= (TextView)listViewItem.findViewById(R.id.tvuserphnumber);
        TextView textViewUserSNumber= (TextView)listViewItem.findViewById(R.id.tvuserSnumber);
        TextView textViewUserPass= (TextView)listViewItem.findViewById(R.id.tvuserpass);

        User user=userList.get(position);

        textViewUserSNumber.setText(user.getseriesNO());
        textViewUserEmail.setText(user.getEmail());
        textViewUserphnumber.setText(user.getPhoneNumber());
        textViewUserPass.setText(user.getPassword());



        return listViewItem;


    }
}
