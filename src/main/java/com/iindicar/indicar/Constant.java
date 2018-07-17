package com.iindicar.indicar;

import android.databinding.ObservableField;

/**
 * Created by yeseul on 2018-04-28.
 */

public class Constant {
    public static String locale = "ko";

    public class BoardType {
        public static final String DAY_LIFE = "daylife";
        public static final String MARKET = "market";
        public static final String DIY = "diy";
    }

    public class BoardWrite {
        public static final int NEW = 0;
        public static final int UPDATE = 0;
    }

    public static final ObservableField<String> TUNING = new ObservableField<>("tuning");
    public static final ObservableField<String> COMMUNITY = new ObservableField<>("community");
    public static final ObservableField<String> SHOPPING = new ObservableField<>("shopping");
    public static final ObservableField<String> ACCOUNT = new ObservableField<>("account");

    public class BoardTab {
        public static final int BOARD_POPULAR = 0;
        public static final int BOARD_ALL = 1;
    }

    public class RequestCode {
        public static final int REQUEST_BOARD_DETAIL = 101;
        public static final int REQUEST_BOARD_UPDATE = 102;
    }

    public static final ObservableField<String> DAY_LIFE = new ObservableField<>("daylife");
    public static final ObservableField<String> MARKET = new ObservableField<>("market");
    public static final ObservableField<String> DIY = new ObservableField<>("diy");
}
