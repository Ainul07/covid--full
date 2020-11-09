package com.ai.covidainuas.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ai.covidainuas.activity.VidActivity;
import com.ai.covidainuluas.R;


public class HomeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);


    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //dari fragment ke activity
        Button btnVid;
        btnVid = view.findViewById(R.id.btnVid);
        btnVid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), VidActivity.class);
                startActivity(i);
            }
        });

        Button btnwa;
        btnwa = view.findViewById(R.id.btnWa);
        btnwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "628561679995";
                String url = "https://api.whatsapp.com/send?phone="+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setPackage("com.whatsapp");
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    //public void openWA(View view) {
     //   String number = "6285880827382";
   //     String url = "https://api.whatsapp.com/send?phone="+number;
    //    Intent i = new Intent(Intent.ACTION_VIEW);
      //  i.setPackage("com.whatsapp");
    //    i.setData(Uri.parse(url));
    //    startActivity(i);
  //  }
}




