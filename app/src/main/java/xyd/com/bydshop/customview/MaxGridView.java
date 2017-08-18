package xyd.com.bydshop.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by ${zxl} on 2017/4/14.
 * D:
 * C:
 */

public class MaxGridView extends GridView {
    public MaxGridView(Context context) {
        super(context);
    }

    public MaxGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, height);

    }
}
