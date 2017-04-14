package com.noel201296gmail.cinematalk;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
/**
 * Created by OMOSEFE NOEL OBASEKI on 08/04/2017.
 */
public class NetworkController {

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;
    private static NetworkController mInstance;

    private NetworkController(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
        // This will Load Images from Network in Separate Thread
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    //Create ImageCache of max size 10MB
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(10);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized NetworkController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkController(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}



