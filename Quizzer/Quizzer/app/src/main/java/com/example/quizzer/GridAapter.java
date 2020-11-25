package com.example.quizzer;

import android.content.Intent;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class GridAapter extends BaseAdapter {

    private int sets=0;
    private String category;
    private InterstitialAd interstitialAd;

    public GridAapter(int sets,String category,InterstitialAd interstitialAd)
    {
        this.sets = sets;
        this.category=category;
        this.interstitialAd=interstitialAd;

    }

    @Override
    public int getCount(){
        return sets;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent)
    {
        View view;

        if (convertView==null)
        {
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item,parent,false);

        }
        else
        {
            view=convertView;
        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                interstitialAd.setAdListener(new AdListener()
                {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();

                   //     interstitialAd.loadAd(new AdRequest.Builder().build());
                        Intent questionIntent=new Intent(parent.getContext(),QuestionActivity.class);
                        questionIntent.putExtra("category",category);
                        questionIntent.putExtra("setNo",position+1);
                        parent.getContext().startActivity(questionIntent);
                    }
                });

                if (interstitialAd.isLoaded())
                {
                    interstitialAd.show();
                    return;
                }
                Intent questionIntent=new Intent(parent.getContext(),QuestionActivity.class);
                questionIntent.putExtra("category",category);
                questionIntent.putExtra("setNo",position+1);
                parent.getContext().startActivity(questionIntent);



            }
        });

        ((TextView)view.findViewById(R.id.question)).setText(String.valueOf(position+1));
        return view;
    }
}
