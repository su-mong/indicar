package com.iindicar.indicar.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.iindicar.indicar.utils.ImageUtil;

/**
 * Created by yeseul on 2018-04-22.
 */

public class ImageViewBinding {

    @BindingAdapter(value = {"imageUrl"}, requireAll = false)
    public static void loadImage(ImageView imageView, String url){

        ImageUtil.loadImage(imageView, url);
    }

    @BindingAdapter(value = {"imageUrl", "imageWidth", "imageHeight"})
    public static void loadImage(ImageView imageView, String url, String width, String height){
        Log.d("ImageViewBinding", "imageWidth" + width + "imageHeight" + height);
        try {
            int w = Integer.parseInt(width);
            int h = Integer.parseInt(height);
            ImageUtil.setImageViewSize(imageView, w, h);
            ImageUtil.loadImage(imageView, url, w, h);
            return;
        } catch (Exception e){}
        ImageUtil.loadImage(imageView, url);
    }

    @BindingAdapter(value = {"imageUrl"}, requireAll = false)
    public static void loadImage(ImageView imageView, Uri url){

        ImageUtil.loadImage(imageView, url);
    }

    @BindingAdapter(value = {"imageUrl"}, requireAll = false)
    public static void loadImage(ImageView imageView, int id){

        ImageUtil.loadImage(imageView, id);
    }

    @BindingAdapter(value = {"circleImageUrl", "error"}, requireAll = false)
    public static void loadCircleImage(ImageView imageView, String url, Drawable errorDrawable){

        ImageUtil.loadCircleImage(imageView, url, errorDrawable);
    }

    @BindingAdapter(value = {"circleImageUrl", "error"}, requireAll = false)
    public static void loadCircleImage(ImageView imageView, int id, Drawable errorDrawable){

        ImageUtil.loadCircleImage(imageView, id, errorDrawable);
    }

    @BindingAdapter(value = {"circleImageUrl", "error"}, requireAll = false)
    public static void loadCircleImage(ImageView imageView, Uri url, Drawable errorDrawable){

        ImageUtil.loadCircleImage(imageView, url, errorDrawable);
    }


}
