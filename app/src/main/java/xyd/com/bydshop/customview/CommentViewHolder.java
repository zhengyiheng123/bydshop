package xyd.com.bydshop.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;


/**
 * 通用viewholder
 */
public class CommentViewHolder {


    private View mConvertview;
    private Context mContext;
    private int resid;

    public CommentViewHolder(Context mContext, int resid) {
        this.mContext = mContext;
        this.resid = resid;
        mConvertview = LayoutInflater.from(mContext).inflate(resid, null);
        AutoUtils.autoSize(mConvertview);
        mConvertview.setTag(this);
    }

    public View getConverView() {
        return mConvertview;
    }

    public static CommentViewHolder getViewHolder(View convertview, Context mContext, int resid) {
        CommentViewHolder commentViewHolder;
        if (convertview == null) {
            commentViewHolder = new CommentViewHolder(mContext, resid);
        } else {
            commentViewHolder = (CommentViewHolder) convertview.getTag();
        }
        return commentViewHolder;

    }

    public View FindViewById(int id) {
        return mConvertview.findViewById(id);
    }

}

