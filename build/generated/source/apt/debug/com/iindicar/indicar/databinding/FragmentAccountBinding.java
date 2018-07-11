package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentAccountBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.guidelineAccount1, 9);
        sViewsWithIds.put(R.id.guidelineAccount2, 10);
        sViewsWithIds.put(R.id.guidelineAccount3, 11);
        sViewsWithIds.put(R.id.guidelineAccount4, 12);
        sViewsWithIds.put(R.id.text_account_title, 13);
        sViewsWithIds.put(R.id.text_subtitle, 14);
        sViewsWithIds.put(R.id.llA_profile, 15);
        sViewsWithIds.put(R.id.ivA_profileImage, 16);
        sViewsWithIds.put(R.id.tvA_name, 17);
        sViewsWithIds.put(R.id.tvA_email, 18);
        sViewsWithIds.put(R.id.tvA_address, 19);
        sViewsWithIds.put(R.id.tvA_subtc, 20);
        sViewsWithIds.put(R.id.tvA_subtt, 21);
        sViewsWithIds.put(R.id.buttontx_basket, 22);
        sViewsWithIds.put(R.id.subbutton_basket, 23);
        sViewsWithIds.put(R.id.buttontx_howtouse, 24);
        sViewsWithIds.put(R.id.subbutton_howtouse, 25);
        sViewsWithIds.put(R.id.buttontx_alliacne, 26);
        sViewsWithIds.put(R.id.subbutton_alliance, 27);
        sViewsWithIds.put(R.id.buttontx_logout, 28);
        sViewsWithIds.put(R.id.subbutton_logout, 29);
        sViewsWithIds.put(R.id.buttontx_like, 30);
        sViewsWithIds.put(R.id.subbutton_like, 31);
        sViewsWithIds.put(R.id.buttontx_writing, 32);
        sViewsWithIds.put(R.id.subbutton_writing, 33);
        sViewsWithIds.put(R.id.buttontx_comment, 34);
        sViewsWithIds.put(R.id.subbutton_comment, 35);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout btnAAlliance;
    @NonNull
    public final android.widget.LinearLayout btnABasket;
    @NonNull
    public final android.widget.LinearLayout btnAComment;
    @NonNull
    public final android.widget.ImageButton btnAGotoProfile;
    @NonNull
    public final android.widget.LinearLayout btnAHowtouse;
    @NonNull
    public final android.widget.LinearLayout btnALike;
    @NonNull
    public final android.widget.LinearLayout btnALogout;
    @NonNull
    public final android.widget.LinearLayout btnAWriting;
    @NonNull
    public final android.widget.TextView buttontxAlliacne;
    @NonNull
    public final android.widget.TextView buttontxBasket;
    @NonNull
    public final android.widget.TextView buttontxComment;
    @NonNull
    public final android.widget.TextView buttontxHowtouse;
    @NonNull
    public final android.widget.TextView buttontxLike;
    @NonNull
    public final android.widget.TextView buttontxLogout;
    @NonNull
    public final android.widget.TextView buttontxWriting;
    @NonNull
    public final android.support.constraint.Guideline guidelineAccount1;
    @NonNull
    public final android.support.constraint.Guideline guidelineAccount2;
    @NonNull
    public final android.support.constraint.Guideline guidelineAccount3;
    @NonNull
    public final android.support.constraint.Guideline guidelineAccount4;
    @NonNull
    public final android.widget.ImageButton ivAProfileImage;
    @NonNull
    public final android.widget.LinearLayout llAProfile;
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView0;
    @NonNull
    public final android.widget.TextView subbuttonAlliance;
    @NonNull
    public final android.widget.TextView subbuttonBasket;
    @NonNull
    public final android.widget.TextView subbuttonComment;
    @NonNull
    public final android.widget.TextView subbuttonHowtouse;
    @NonNull
    public final android.widget.TextView subbuttonLike;
    @NonNull
    public final android.widget.TextView subbuttonLogout;
    @NonNull
    public final android.widget.TextView subbuttonWriting;
    @NonNull
    public final android.widget.TextView textAccountTitle;
    @NonNull
    public final android.widget.TextView textSubtitle;
    @NonNull
    public final android.widget.TextView tvAAddress;
    @NonNull
    public final android.widget.TextView tvAEmail;
    @NonNull
    public final android.widget.TextView tvAName;
    @NonNull
    public final android.widget.TextView tvASubtc;
    @NonNull
    public final android.widget.TextView tvASubtt;
    // variables
    @Nullable
    private com.iindicar.indicar.b4_account.AccountFragment mFragment;
    @Nullable
    private final android.view.View.OnClickListener mCallback9;
    @Nullable
    private final android.view.View.OnClickListener mCallback8;
    @Nullable
    private final android.view.View.OnClickListener mCallback7;
    @Nullable
    private final android.view.View.OnClickListener mCallback11;
    @Nullable
    private final android.view.View.OnClickListener mCallback6;
    @Nullable
    private final android.view.View.OnClickListener mCallback12;
    @Nullable
    private final android.view.View.OnClickListener mCallback5;
    @Nullable
    private final android.view.View.OnClickListener mCallback10;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentAccountBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 36, sIncludes, sViewsWithIds);
        this.btnAAlliance = (android.widget.LinearLayout) bindings[4];
        this.btnAAlliance.setTag(null);
        this.btnABasket = (android.widget.LinearLayout) bindings[2];
        this.btnABasket.setTag(null);
        this.btnAComment = (android.widget.LinearLayout) bindings[8];
        this.btnAComment.setTag(null);
        this.btnAGotoProfile = (android.widget.ImageButton) bindings[1];
        this.btnAGotoProfile.setTag(null);
        this.btnAHowtouse = (android.widget.LinearLayout) bindings[3];
        this.btnAHowtouse.setTag(null);
        this.btnALike = (android.widget.LinearLayout) bindings[6];
        this.btnALike.setTag(null);
        this.btnALogout = (android.widget.LinearLayout) bindings[5];
        this.btnALogout.setTag(null);
        this.btnAWriting = (android.widget.LinearLayout) bindings[7];
        this.btnAWriting.setTag(null);
        this.buttontxAlliacne = (android.widget.TextView) bindings[26];
        this.buttontxBasket = (android.widget.TextView) bindings[22];
        this.buttontxComment = (android.widget.TextView) bindings[34];
        this.buttontxHowtouse = (android.widget.TextView) bindings[24];
        this.buttontxLike = (android.widget.TextView) bindings[30];
        this.buttontxLogout = (android.widget.TextView) bindings[28];
        this.buttontxWriting = (android.widget.TextView) bindings[32];
        this.guidelineAccount1 = (android.support.constraint.Guideline) bindings[9];
        this.guidelineAccount2 = (android.support.constraint.Guideline) bindings[10];
        this.guidelineAccount3 = (android.support.constraint.Guideline) bindings[11];
        this.guidelineAccount4 = (android.support.constraint.Guideline) bindings[12];
        this.ivAProfileImage = (android.widget.ImageButton) bindings[16];
        this.llAProfile = (android.widget.LinearLayout) bindings[15];
        this.mboundView0 = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.subbuttonAlliance = (android.widget.TextView) bindings[27];
        this.subbuttonBasket = (android.widget.TextView) bindings[23];
        this.subbuttonComment = (android.widget.TextView) bindings[35];
        this.subbuttonHowtouse = (android.widget.TextView) bindings[25];
        this.subbuttonLike = (android.widget.TextView) bindings[31];
        this.subbuttonLogout = (android.widget.TextView) bindings[29];
        this.subbuttonWriting = (android.widget.TextView) bindings[33];
        this.textAccountTitle = (android.widget.TextView) bindings[13];
        this.textSubtitle = (android.widget.TextView) bindings[14];
        this.tvAAddress = (android.widget.TextView) bindings[19];
        this.tvAEmail = (android.widget.TextView) bindings[18];
        this.tvAName = (android.widget.TextView) bindings[17];
        this.tvASubtc = (android.widget.TextView) bindings[20];
        this.tvASubtt = (android.widget.TextView) bindings[21];
        setRootTag(root);
        // listeners
        mCallback9 = new android.databinding.generated.callback.OnClickListener(this, 5);
        mCallback8 = new android.databinding.generated.callback.OnClickListener(this, 4);
        mCallback7 = new android.databinding.generated.callback.OnClickListener(this, 3);
        mCallback11 = new android.databinding.generated.callback.OnClickListener(this, 7);
        mCallback6 = new android.databinding.generated.callback.OnClickListener(this, 2);
        mCallback12 = new android.databinding.generated.callback.OnClickListener(this, 8);
        mCallback5 = new android.databinding.generated.callback.OnClickListener(this, 1);
        mCallback10 = new android.databinding.generated.callback.OnClickListener(this, 6);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.fragment == variableId) {
            setFragment((com.iindicar.indicar.b4_account.AccountFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setFragment(@Nullable com.iindicar.indicar.b4_account.AccountFragment Fragment) {
        this.mFragment = Fragment;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.fragment);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.b4_account.AccountFragment getFragment() {
        return mFragment;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.iindicar.indicar.b4_account.AccountFragment fragment = mFragment;
        // batch finished
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            this.btnAAlliance.setOnClickListener(mCallback8);
            this.btnABasket.setOnClickListener(mCallback6);
            this.btnAComment.setOnClickListener(mCallback12);
            this.btnAGotoProfile.setOnClickListener(mCallback5);
            this.btnAHowtouse.setOnClickListener(mCallback7);
            this.btnALike.setOnClickListener(mCallback10);
            this.btnALogout.setOnClickListener(mCallback9);
            this.btnAWriting.setOnClickListener(mCallback11);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 5: {
                // localize variables for thread safety
                // fragment
                com.iindicar.indicar.b4_account.AccountFragment fragment = mFragment;
                // fragment != null
                boolean fragmentJavaLangObjectNull = false;



                fragmentJavaLangObjectNull = (fragment) != (null);
                if (fragmentJavaLangObjectNull) {


                    fragment.btnALogout();
                }
                break;
            }
            case 4: {
                // localize variables for thread safety
                // fragment
                com.iindicar.indicar.b4_account.AccountFragment fragment = mFragment;
                // fragment != null
                boolean fragmentJavaLangObjectNull = false;



                fragmentJavaLangObjectNull = (fragment) != (null);
                if (fragmentJavaLangObjectNull) {


                    fragment.btnAAlliance();
                }
                break;
            }
            case 3: {
                // localize variables for thread safety
                // fragment
                com.iindicar.indicar.b4_account.AccountFragment fragment = mFragment;
                // fragment != null
                boolean fragmentJavaLangObjectNull = false;



                fragmentJavaLangObjectNull = (fragment) != (null);
                if (fragmentJavaLangObjectNull) {


                    fragment.btnAHowtouse();
                }
                break;
            }
            case 7: {
                // localize variables for thread safety
                // fragment
                com.iindicar.indicar.b4_account.AccountFragment fragment = mFragment;
                // fragment != null
                boolean fragmentJavaLangObjectNull = false;



                fragmentJavaLangObjectNull = (fragment) != (null);
                if (fragmentJavaLangObjectNull) {



                    fragment.traceClickListener(callbackArg_0);
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // fragment
                com.iindicar.indicar.b4_account.AccountFragment fragment = mFragment;
                // fragment != null
                boolean fragmentJavaLangObjectNull = false;



                fragmentJavaLangObjectNull = (fragment) != (null);
                if (fragmentJavaLangObjectNull) {


                    fragment.btnABasket();
                }
                break;
            }
            case 8: {
                // localize variables for thread safety
                // fragment
                com.iindicar.indicar.b4_account.AccountFragment fragment = mFragment;
                // fragment != null
                boolean fragmentJavaLangObjectNull = false;



                fragmentJavaLangObjectNull = (fragment) != (null);
                if (fragmentJavaLangObjectNull) {



                    fragment.traceClickListener(callbackArg_0);
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // fragment
                com.iindicar.indicar.b4_account.AccountFragment fragment = mFragment;
                // fragment != null
                boolean fragmentJavaLangObjectNull = false;



                fragmentJavaLangObjectNull = (fragment) != (null);
                if (fragmentJavaLangObjectNull) {


                    fragment.btnAGotoProfile();
                }
                break;
            }
            case 6: {
                // localize variables for thread safety
                // fragment
                com.iindicar.indicar.b4_account.AccountFragment fragment = mFragment;
                // fragment != null
                boolean fragmentJavaLangObjectNull = false;



                fragmentJavaLangObjectNull = (fragment) != (null);
                if (fragmentJavaLangObjectNull) {



                    fragment.traceClickListener(callbackArg_0);
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentAccountBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentAccountBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentAccountBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_account, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentAccountBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentAccountBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.fragment_account, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentAccountBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentAccountBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_account_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentAccountBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): fragment
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}