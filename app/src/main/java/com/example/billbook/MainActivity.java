package com.example.billbook;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.billbook.constant.PictureUrl;
import com.example.billbook.enums.SeasonTerm;
import com.example.billbook.utils.SeasonUtils;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.season_term)
    RelativeLayout relativeLayout;

    SeasonUtils seasonUtils=new SeasonUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setHeadGif();
    }

    private void setHeadGif(){
        SeasonTerm seasonTerm=seasonUtils.getBetween();
        String url;
        if(seasonTerm!=null){
            String seasonIndex=String.valueOf(seasonTerm.getIndex());
            url=PictureUrl.seasonGifUrls.get(seasonIndex);
        }else{
            url=PictureUrl.bgJpgUrls.get(0);
        }

        if(url.length()==0){
            url=PictureUrl.defaultGifUrl;
        }

        Glide.with(this).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                relativeLayout.setBackground(resource);
            }
        });
    }
}
