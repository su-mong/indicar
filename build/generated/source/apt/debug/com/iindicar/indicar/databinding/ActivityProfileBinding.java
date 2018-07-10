package com.iindicar.indicar.databinding;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityProfileBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.guidelineProfile1, 10);
        sViewsWithIds.put(R.id.guidelineProfile2, 11);
        sViewsWithIds.put(R.id.guidelineProfile3, 12);
        sViewsWithIds.put(R.id.guidelineProfile4, 13);
        sViewsWithIds.put(R.id.guidelineProfile5, 14);
        sViewsWithIds.put(R.id.guidelineProfile6, 15);
        sViewsWithIds.put(R.id.guidelineProfile7, 16);
        sViewsWithIds.put(R.id.ivA_profileback1, 17);
        sViewsWithIds.put(R.id.ivA_profileback2, 18);
        sViewsWithIds.put(R.id.text_eventAlarm, 19);
        sViewsWithIds.put(R.id.text_eventAlarmSub, 20);
        sViewsWithIds.put(R.id.text_otherAlarm, 21);
        sViewsWithIds.put(R.id.text_otherAlarmSub, 22);
        sViewsWithIds.put(R.id.btn_clause, 23);
        sViewsWithIds.put(R.id.text_clause, 24);
        sViewsWithIds.put(R.id.btn_opinion, 25);
        sViewsWithIds.put(R.id.text_opinion, 26);
        sViewsWithIds.put(R.id.text_delete_account, 27);
        sViewsWithIds.put(R.id.text_logout, 28);
    }
    // views
    @NonNull
    public final android.widget.ImageButton btnAModify1;
    @NonNull
    public final android.widget.LinearLayout btnClause;
    @NonNull
    public final android.widget.LinearLayout btnOpinion;
    @NonNull
    public final android.support.constraint.Guideline guidelineProfile1;
    @NonNull
    public final android.support.constraint.Guideline guidelineProfile2;
    @NonNull
    public final android.support.constraint.Guideline guidelineProfile3;
    @NonNull
    public final android.support.constraint.Guideline guidelineProfile4;
    @NonNull
    public final android.support.constraint.Guideline guidelineProfile5;
    @NonNull
    public final android.support.constraint.Guideline guidelineProfile6;
    @NonNull
    public final android.support.constraint.Guideline guidelineProfile7;
    @NonNull
    public final android.widget.ImageView ivAProfileback1;
    @NonNull
    public final android.widget.LinearLayout ivAProfileback2;
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView0;
    @NonNull
    private final android.widget.ImageButton mboundView5;
    @NonNull
    private final android.widget.ImageButton mboundView6;
    @NonNull
    private final android.widget.ImageButton mboundView7;
    @NonNull
    private final android.widget.RelativeLayout mboundView8;
    @NonNull
    private final android.widget.RelativeLayout mboundView9;
    @NonNull
    public final android.widget.TextView textAddress;
    @NonNull
    public final android.widget.TextView textClause;
    @NonNull
    public final android.widget.TextView textDeleteAccount;
    @NonNull
    public final android.widget.TextView textEmail;
    @NonNull
    public final android.widget.TextView textEventAlarm;
    @NonNull
    public final android.widget.TextView textEventAlarmSub;
    @NonNull
    public final android.widget.TextView textLogout;
    @NonNull
    public final android.widget.TextView textName;
    @NonNull
    public final android.widget.TextView textOpinion;
    @NonNull
    public final android.widget.TextView textOtherAlarm;
    @NonNull
    public final android.widget.TextView textOtherAlarmSub;
    // variables
    @Nullable
    private com.iindicar.indicar.b4_account.ProfileActivity mActivity;
    @Nullable
    private com.iindicar.indicar.data.vo.UserVO mUser;
    @Nullable
    private final android.view.View.OnClickListener mCallback27;
    @Nullable
    private final android.view.View.OnClickListener mCallback30;
    @Nullable
    private final android.view.View.OnClickListener mCallback28;
    @Nullable
    private final android.view.View.OnClickListener mCallback31;
    @Nullable
    private final android.view.View.OnClickListener mCallback29;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityProfileBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 3);
        final Object[] bindings = mapBindings(bindingComponent, root, 29, sIncludes, sViewsWithIds);
        this.btnAModify1 = (android.widget.ImageButton) bindings[2];
        this.btnAModify1.setTag(null);
        this.btnClause = (android.widget.LinearLayout) bindings[23];
        this.btnOpinion = (android.widget.LinearLayout) bindings[25];
        this.guidelineProfile1 = (android.support.constraint.Guideline) bindings[10];
        this.guidelineProfile2 = (android.support.constraint.Guideline) bindings[11];
        this.guidelineProfile3 = (android.support.constraint.Guideline) bindings[12];
        this.guidelineProfile4 = (android.support.constraint.Guideline) bindings[13];
        this.guidelineProfile5 = (android.support.constraint.Guideline) bindings[14];
        this.guidelineProfile6 = (android.support.constraint.Guideline) bindings[15];
        this.guidelineProfile7 = (android.support.constraint.Guideline) bindings[16];
        this.ivAProfileback1 = (android.widget.ImageView) bindings[17];
        this.ivAProfileback2 = (android.widget.LinearLayout) bindings[18];
        this.mboundView0 = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView5 = (android.widget.ImageButton) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (android.widget.ImageButton) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (android.widget.ImageButton) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (android.widget.RelativeLayout) bindings[8];
        this.mboundView8.setTag(null);
        this.mboundView9 = (android.widget.RelativeLayout) bindings[9];
        this.mboundView9.setTag(null);
        this.textAddress = (android.widget.TextView) bindings[4];
        this.textAddress.setTag(null);
        this.textClause = (android.widget.TextView) bindings[24];
        this.textDeleteAccount = (android.widget.TextView) bindings[27];
        this.textEmail = (android.widget.TextView) bindings[3];
        this.textEmail.setTag(null);
        this.textEventAlarm = (android.widget.TextView) bindings[19];
        this.textEventAlarmSub = (android.widget.TextView) bindings[20];
        this.textLogout = (android.widget.TextView) bindings[28];
        this.textName = (android.widget.TextView) bindings[1];
        this.textName.setTag(null);
        this.textOpinion = (android.widget.TextView) bindings[26];
        this.textOtherAlarm = (android.widget.TextView) bindings[21];
        this.textOtherAlarmSub = (android.widget.TextView) bindings[22];
        setRootTag(root);
        // listeners
        mCallback27 = new android.databinding.generated.callback.OnClickListener(this, 1);
        mCallback30 = new android.databinding.generated.callback.OnClickListener(this, 4);
        mCallback28 = new android.databinding.generated.callback.OnClickListener(this, 2);
        mCallback31 = new android.databinding.generated.callback.OnClickListener(this, 5);
        mCallback29 = new android.databinding.generated.callback.OnClickListener(this, 3);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x80L;
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
        if (BR.activity == variableId) {
            setActivity((com.iindicar.indicar.b4_account.ProfileActivity) variable);
        }
        else if (BR.user == variableId) {
            setUser((com.iindicar.indicar.data.vo.UserVO) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setActivity(@Nullable com.iindicar.indicar.b4_account.ProfileActivity Activity) {
        this.mActivity = Activity;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.activity);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.b4_account.ProfileActivity getActivity() {
        return mActivity;
    }
    public void setUser(@Nullable com.iindicar.indicar.data.vo.UserVO User) {
        updateRegistration(1, User);
        this.mUser = User;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }
    @Nullable
    public com.iindicar.indicar.data.vo.UserVO getUser() {
        return mUser;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeActivityIsEventAlarmOn((android.databinding.ObservableBoolean) object, fieldId);
            case 1 :
                return onChangeUser((com.iindicar.indicar.data.vo.UserVO) object, fieldId);
            case 2 :
                return onChangeActivityIsOtherAlarmOn((android.databinding.ObservableBoolean) object, fieldId);
        }
        return false;
    }
    private boolean onChangeActivityIsEventAlarmOn(android.databinding.ObservableBoolean ActivityIsEventAlarmOn, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeUser(com.iindicar.indicar.data.vo.UserVO User, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.userName) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        else if (fieldId == BR.userEmail) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        else if (fieldId == BR.profileImageUrl) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeActivityIsOtherAlarmOn(android.databinding.ObservableBoolean ActivityIsOtherAlarmOn, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
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
        com.iindicar.indicar.b4_account.ProfileActivity activity = mActivity;
        boolean activityIsEventAlarmOnGet = false;
        java.lang.String userUserName = null;
        android.databinding.ObservableBoolean activityIsEventAlarmOn = null;
        java.lang.String userUserEmail = null;
        com.iindicar.indicar.data.vo.UserVO user = mUser;
        java.lang.String userUserAddress = null;
        java.lang.String userProfileImageUrl = null;
        android.databinding.ObservableBoolean activityIsOtherAlarmOn = null;
        boolean activityIsOtherAlarmOnGet = false;

        if ((dirtyFlags & 0x8dL) != 0) {


            if ((dirtyFlags & 0x89L) != 0) {

                    if (activity != null) {
                        // read activity.isEventAlarmOn
                        activityIsEventAlarmOn = activity.isEventAlarmOn;
                    }
                    updateRegistration(0, activityIsEventAlarmOn);


                    if (activityIsEventAlarmOn != null) {
                        // read activity.isEventAlarmOn.get()
                        activityIsEventAlarmOnGet = activityIsEventAlarmOn.get();
                    }
            }
            if ((dirtyFlags & 0x8cL) != 0) {

                    if (activity != null) {
                        // read activity.isOtherAlarmOn
                        activityIsOtherAlarmOn = activity.isOtherAlarmOn;
                    }
                    updateRegistration(2, activityIsOtherAlarmOn);


                    if (activityIsOtherAlarmOn != null) {
                        // read activity.isOtherAlarmOn.get()
                        activityIsOtherAlarmOnGet = activityIsOtherAlarmOn.get();
                    }
            }
        }
        if ((dirtyFlags & 0xf2L) != 0) {


            if ((dirtyFlags & 0x92L) != 0) {

                    if (user != null) {
                        // read user.userName
                        userUserName = user.getUserName();
                    }
            }
            if ((dirtyFlags & 0xa2L) != 0) {

                    if (user != null) {
                        // read user.userEmail
                        userUserEmail = user.getUserEmail();
                    }
            }
            if ((dirtyFlags & 0x82L) != 0) {

                    if (user != null) {
                        // read user.userAddress
                        userUserAddress = user.getUserAddress();
                    }
            }
            if ((dirtyFlags & 0xc2L) != 0) {

                    if (user != null) {
                        // read user.profileImageUrl
                        userProfileImageUrl = user.getProfileImageUrl();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x80L) != 0) {
            // api target 1

            this.btnAModify1.setOnClickListener(mCallback27);
            this.mboundView6.setOnClickListener(mCallback28);
            this.mboundView7.setOnClickListener(mCallback29);
            this.mboundView8.setOnClickListener(mCallback30);
            this.mboundView9.setOnClickListener(mCallback31);
        }
        if ((dirtyFlags & 0xc2L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.ImageViewBinding.loadCircleImage(this.mboundView5, userProfileImageUrl, (android.graphics.drawable.Drawable)null);
        }
        if ((dirtyFlags & 0x89L) != 0) {
            // api target 1

            com.iindicar.indicar.binding.BindAdapter.setSelected(this.mboundView6, activityIsEventAlarmOnGet);
        }
        if ((dirtyFlags & 0x8cL) != 0) {
            // api target 1

            com.iindicar.indicar.binding.BindAdapter.setSelected(this.mboundView7, activityIsOtherAlarmOnGet);
        }
        if ((dirtyFlags & 0x82L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textAddress, userUserAddress);
        }
        if ((dirtyFlags & 0xa2L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textEmail, userUserEmail);
        }
        if ((dirtyFlags & 0x92L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.textName, userUserName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 1: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b4_account.ProfileActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.onEditNameClicked();
                }
                break;
            }
            case 4: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b4_account.ProfileActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.onDeleteAccountClicked();
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b4_account.ProfileActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.onEventAlarmClicked();
                }
                break;
            }
            case 5: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b4_account.ProfileActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.onLogoutClicked();
                }
                break;
            }
            case 3: {
                // localize variables for thread safety
                // activity != null
                boolean activityJavaLangObjectNull = false;
                // activity
                com.iindicar.indicar.b4_account.ProfileActivity activity = mActivity;



                activityJavaLangObjectNull = (activity) != (null);
                if (activityJavaLangObjectNull) {


                    activity.onOtherAlarmClicked();
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityProfileBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_profile, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityProfileBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.iindicar.indicar.R.layout.activity_profile, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityProfileBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityProfileBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_profile_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityProfileBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): activity.isEventAlarmOn
        flag 1 (0x2L): user
        flag 2 (0x3L): activity.isOtherAlarmOn
        flag 3 (0x4L): activity
        flag 4 (0x5L): user.userName
        flag 5 (0x6L): user.userEmail
        flag 6 (0x7L): user.profileImageUrl
        flag 7 (0x8L): null
    flag mapping end*/
    //end
}