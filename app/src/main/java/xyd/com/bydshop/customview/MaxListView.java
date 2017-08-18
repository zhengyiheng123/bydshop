package xyd.com.bydshop.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by ${zxl} on 2017/4/14.
 * D:
 * C:
 */

public class MaxListView extends ListView {
    public MaxListView(Context context) {
        super(context);
    }

    public MaxListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>> 2,MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, height);

    }
}
