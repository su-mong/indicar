package android.databinding.generated.callback;
public final class OnTouchListener implements android.view.View.OnTouchListener {
    final Listener mListener;
    final int mSourceId;
    public OnTouchListener(Listener listener, int sourceId) {
        mListener = listener;
        mSourceId = sourceId;
    }
    @Override
    public boolean onTouch(android.view.View callbackArg_0, android.view.MotionEvent callbackArg_1) {
        return mListener._internalCallbackOnTouch(mSourceId , callbackArg_0, callbackArg_1);
    }
    public interface Listener {
        boolean _internalCallbackOnTouch(int sourceId , android.view.View callbackArg_0, android.view.MotionEvent callbackArg_1);
    }
}