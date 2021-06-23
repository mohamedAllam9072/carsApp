package com.allam.utils;
import android.content.Context;
import android.widget.ImageView;
import com.allam.carsapp.R;
import com.squareup.picasso.Picasso;



public class MyUtils {
    public static void m_Picasso(Context context, String imageUrl, ImageView imageView) {
        try {
            Picasso.with(context)
                    .load(imageUrl)
                    .error(R.drawable.ic_launcher_foreground)
                    .fit()
                    .centerCrop()
                    .into(imageView);
        } catch (Exception ignored) {
        }
    }



}
