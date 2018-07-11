
package android.databinding;
import com.iindicar.indicar.BR;
class DataBinderMapper  {
    final static int TARGET_MIN_SDK = 16;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.iindicar.indicar.R.layout.board_popular_item:
                    return com.iindicar.indicar.databinding.BoardPopularItemBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.board_list_fragment:
                    return com.iindicar.indicar.databinding.BoardListFragmentBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.board_write_filter_fragment:
                    return com.iindicar.indicar.databinding.BoardWriteFilterFragmentBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.profile_suggest:
                    return com.iindicar.indicar.databinding.ProfileSuggestBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.board_comment_item:
                    return com.iindicar.indicar.databinding.BoardCommentItemBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.board_write_edit_activity:
                    return com.iindicar.indicar.databinding.BoardWriteEditActivityBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.notice_item:
                    return com.iindicar.indicar.databinding.NoticeItemBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.splash_activity:
                    return com.iindicar.indicar.databinding.SplashActivityBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.fragment_account:
                    return com.iindicar.indicar.databinding.FragmentAccountBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.activity_tuning2:
                    return com.iindicar.indicar.databinding.ActivityTuning2Binding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.community_fragment:
                    return com.iindicar.indicar.databinding.CommunityFragmentBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.activity_board_filter:
                    return com.iindicar.indicar.databinding.ActivityBoardFilterBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.board_detail_header:
                    return com.iindicar.indicar.databinding.BoardDetailHeaderBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.board_write_item:
                    return com.iindicar.indicar.databinding.BoardWriteItemBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.board_write_item_fragment:
                    return com.iindicar.indicar.databinding.BoardWriteItemFragmentBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.tunning_fragment:
                    return com.iindicar.indicar.databinding.TunningFragmentBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.activity_edit_profile:
                    return com.iindicar.indicar.databinding.ActivityEditProfileBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.activity_trace:
                    return com.iindicar.indicar.databinding.ActivityTraceBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.activity_profile:
                    return com.iindicar.indicar.databinding.ActivityProfileBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.activity_login:
                    return com.iindicar.indicar.databinding.ActivityLoginBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.car_filter_activity:
                    return com.iindicar.indicar.databinding.CarFilterActivityBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.profile_term:
                    return com.iindicar.indicar.databinding.ProfileTermBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.main_activity:
                    return com.iindicar.indicar.databinding.MainActivityBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.board_all_item:
                    return com.iindicar.indicar.databinding.BoardAllItemBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.action_bar_layout:
                    return com.iindicar.indicar.databinding.ActionBarLayoutBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.layout_car_list_item:
                    return com.iindicar.indicar.databinding.LayoutCarListItemBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.board_detail_item:
                    return com.iindicar.indicar.databinding.BoardDetailItemBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.board_detail_content_layout:
                    return com.iindicar.indicar.databinding.BoardDetailContentLayoutBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.board_detail_activity:
                    return com.iindicar.indicar.databinding.BoardDetailActivityBinding.bind(view, bindingComponent);
                case com.iindicar.indicar.R.layout.dialog_layout:
                    return com.iindicar.indicar.databinding.DialogLayoutBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 67210856: {
                if(tag.equals("layout/board_popular_item_0")) {
                    return com.iindicar.indicar.R.layout.board_popular_item;
                }
                break;
            }
            case 490613700: {
                if(tag.equals("layout/board_list_fragment_0")) {
                    return com.iindicar.indicar.R.layout.board_list_fragment;
                }
                break;
            }
            case 1215867466: {
                if(tag.equals("layout/board_write_filter_fragment_0")) {
                    return com.iindicar.indicar.R.layout.board_write_filter_fragment;
                }
                break;
            }
            case -625821830: {
                if(tag.equals("layout/profile_suggest_0")) {
                    return com.iindicar.indicar.R.layout.profile_suggest;
                }
                break;
            }
            case 1992142882: {
                if(tag.equals("layout/board_comment_item_0")) {
                    return com.iindicar.indicar.R.layout.board_comment_item;
                }
                break;
            }
            case -916793865: {
                if(tag.equals("layout/board_write_edit_activity_0")) {
                    return com.iindicar.indicar.R.layout.board_write_edit_activity;
                }
                break;
            }
            case -120602842: {
                if(tag.equals("layout/notice_item_0")) {
                    return com.iindicar.indicar.R.layout.notice_item;
                }
                break;
            }
            case 128562227: {
                if(tag.equals("layout/splash_activity_0")) {
                    return com.iindicar.indicar.R.layout.splash_activity;
                }
                break;
            }
            case 1118648948: {
                if(tag.equals("layout/fragment_account_0")) {
                    return com.iindicar.indicar.R.layout.fragment_account;
                }
                break;
            }
            case 1863170659: {
                if(tag.equals("layout/activity_tuning2_0")) {
                    return com.iindicar.indicar.R.layout.activity_tuning2;
                }
                break;
            }
            case -1461814820: {
                if(tag.equals("layout/community_fragment_0")) {
                    return com.iindicar.indicar.R.layout.community_fragment;
                }
                break;
            }
            case -915443283: {
                if(tag.equals("layout/activity_board_filter_0")) {
                    return com.iindicar.indicar.R.layout.activity_board_filter;
                }
                break;
            }
            case 94518958: {
                if(tag.equals("layout/board_detail_header_0")) {
                    return com.iindicar.indicar.R.layout.board_detail_header;
                }
                break;
            }
            case 741969442: {
                if(tag.equals("layout/board_write_item_0")) {
                    return com.iindicar.indicar.R.layout.board_write_item;
                }
                break;
            }
            case -1525310385: {
                if(tag.equals("layout/board_write_item_fragment_0")) {
                    return com.iindicar.indicar.R.layout.board_write_item_fragment;
                }
                break;
            }
            case -760470588: {
                if(tag.equals("layout/tunning_fragment_0")) {
                    return com.iindicar.indicar.R.layout.tunning_fragment;
                }
                break;
            }
            case -1158109584: {
                if(tag.equals("layout/activity_edit_profile_0")) {
                    return com.iindicar.indicar.R.layout.activity_edit_profile;
                }
                break;
            }
            case -1646978357: {
                if(tag.equals("layout/activity_trace_0")) {
                    return com.iindicar.indicar.R.layout.activity_trace;
                }
                break;
            }
            case 366786799: {
                if(tag.equals("layout/activity_profile_0")) {
                    return com.iindicar.indicar.R.layout.activity_profile;
                }
                break;
            }
            case -237232145: {
                if(tag.equals("layout/activity_login_0")) {
                    return com.iindicar.indicar.R.layout.activity_login;
                }
                break;
            }
            case 1428317527: {
                if(tag.equals("layout/car_filter_activity_0")) {
                    return com.iindicar.indicar.R.layout.car_filter_activity;
                }
                break;
            }
            case -248945160: {
                if(tag.equals("layout/profile_term_0")) {
                    return com.iindicar.indicar.R.layout.profile_term;
                }
                break;
            }
            case -1330819999: {
                if(tag.equals("layout/main_activity_0")) {
                    return com.iindicar.indicar.R.layout.main_activity;
                }
                break;
            }
            case 834217376: {
                if(tag.equals("layout/board_all_item_0")) {
                    return com.iindicar.indicar.R.layout.board_all_item;
                }
                break;
            }
            case 1157550091: {
                if(tag.equals("layout/action_bar_layout_0")) {
                    return com.iindicar.indicar.R.layout.action_bar_layout;
                }
                break;
            }
            case -2087402934: {
                if(tag.equals("layout/layout_car_list_item_0")) {
                    return com.iindicar.indicar.R.layout.layout_car_list_item;
                }
                break;
            }
            case -64554572: {
                if(tag.equals("layout/board_detail_item_0")) {
                    return com.iindicar.indicar.R.layout.board_detail_item;
                }
                break;
            }
            case -1170151151: {
                if(tag.equals("layout/board_detail_content_layout_0")) {
                    return com.iindicar.indicar.R.layout.board_detail_content_layout;
                }
                break;
            }
            case 709233328: {
                if(tag.equals("layout/board_detail_activity_0")) {
                    return com.iindicar.indicar.R.layout.board_detail_activity;
                }
                break;
            }
            case -376198003: {
                if(tag.equals("layout/dialog_layout_0")) {
                    return com.iindicar.indicar.R.layout.dialog_layout;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"activity"
            ,"atchFileId"
            ,"board"
            ,"boardContent"
            ,"boardId"
            ,"boardItem"
            ,"boardTitle"
            ,"boardType"
            ,"bottom"
            ,"carDataImageUrl"
            ,"center"
            ,"comment"
            ,"commentCount"
            ,"commentIndex"
            ,"constant"
            ,"content"
            ,"dialog"
            ,"fileContent"
            ,"fileExtension"
            ,"fileHeight"
            ,"fileIndex"
            ,"filePath"
            ,"fileUrl"
            ,"fileWidth"
            ,"firstDate"
            ,"firstTime"
            ,"fragment"
            ,"imageUrl"
            ,"item"
            ,"lastUpdateDate"
            ,"lastUpdateTime"
            ,"likeCount"
            ,"mainImageHeight"
            ,"mainImageUrl"
            ,"mainImageWidth"
            ,"originalFileName"
            ,"profileImageUrl"
            ,"readCount"
            ,"storeFileName"
            ,"user"
            ,"userEmail"
            ,"userId"
            ,"userKey"
            ,"userName"
            ,"userProfileUrl"
            ,"viewModel"
            ,"writeText"};
    }
}