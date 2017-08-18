package xyd.com.bydshop.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 创建人：赵小磊
 * 创建时间：2016/11/30 0030 8:51
 * 描述：recyclerView 单条的点击事件，长按事件
 */

public class RecyclerViewOnitemClickListener implements RecyclerView.OnItemTouchListener{

    private GestureDetector mGestureDetector;
    private View childView;
    private RecyclerView touchView;

    public RecyclerViewOnitemClickListener(Context context, final OnItemClickListener onItemClickListener){

        mGestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (childView!=null&&onItemClickListener!=null) {
                    onItemClickListener.onItemClick(childView,touchView.getChildPosition(childView));
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                if (childView!=null&&onItemClickListener!=null) {
                    onItemClickListener.onLongClick(childView,touchView.getChildPosition(childView));
                }
            }
        });

    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        childView = rv.findChildViewUnder(e.getX(),e.getY());
        touchView = rv;
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onLongClick(View view, int position);
    }
}
