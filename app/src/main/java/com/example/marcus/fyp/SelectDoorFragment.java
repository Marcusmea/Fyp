package com.example.marcus.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SelectDoorFragment extends Fragment {

    public SelectDoorFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_selectdoor, container,false);

        Button frontdoor= (Button) view.findViewById(R.id.frontdoor);
        Button masteroom= (Button) view.findViewById(R.id.masterroom);
        Button bedroom1=(Button)view.findViewById(R.id.bedroomdoor1);

        frontdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(getActivity(),FrontDoorActivity.class);
                startActivity(in);
            }
        });

        masteroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(getActivity(),MasterRoomActivity.class);
                startActivity(in);
            }
        });

        bedroom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(getActivity(),BedDoor1Activity.class);
                startActivity(in);
            }
        });


        return view;

        }
    }





