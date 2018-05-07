package com.iindicar.indicar.utils;

/**
 * Created by candykick on 2018. 4. 29..
 */

public class ConstClass {
    //토스트 명령어
    public static String strVersionErr = "버전이 맞지 않습니다.";
    public static String strNoInternet = "기기가 인터넷에 연결되지 않았습니다. 인터넷에 연결해주십시오.";
    public static String strLoginedErr = "로그인 정보를 제대로 불러오지 못했습니다. 다시 로그인해주세요.";
    public static String strAddUserSuccess = "회원가입에 성공했습니다.";
    public static String strCancelLogin = "로그인을 취소하셨습니다.";
    public static String strNoSearchResultErr = "해당하는 검색 결과가 없습니다.";
    public static String strCarLoadErr = "차량을 불러오는 데 실패했습니다. 다시 시도해 주세요.";
    public static String strNoCar = "아직 등록된 자동차가 없습니다.";
    public static String strUnlink = " 회원님의 계정이 탈퇴처리되었습니다. 그동안 저희 서비스를 이용해 주셔서 감사합니다.";
    public static String strUserInfoChanged = "유저 정보를 변경했습니다.";
    public static String strErrwithCode = "오류가 발생했습니다: ";
    public static String strServerCheck = "서버 점검중입니다.";
    public static String strNotPrepare = "준비중인 기능입니다. 차후 업데이트에서 만나보실 수 있습니다.";
    public static String strNotAllowUpdateUser = "유저 닉네임은 직전 변경일로부터 7일이 지나야 바꿀 수 있습니다.";
    public static String strTooLongNickname = "닉네임은 한글 10자, 영어 20자 이내여야 합니다.";

    //웹 주소
    public static String versionLink = "http://13.125.173.118:8080/version/version";
    public static String check_User = "http://13.125.173.118:8080/user/search_user2";
    public static String add_User = "http://13.125.173.118:8080/user/add_user2";
    public static String update_User = "http://13.125.173.118:8080/user/updt_user";
    public static String delete_User = "http://13.125.173.118:8080/user/remove";
    public static String getCarLink = "http://13.125.173.118:8080/car/get_car";
    public static String traceLikeResult = "http://13.125.173.118:8080/selectUserLikeBoardArticle";
    public static String traceWritingResult = "http://13.125.173.118:8080/selectUserBoardArticles";
    public static String clause = "https://m.cafe.naver.com/ArticleRead.nhn?clubid=29055312&articleid=7&page=1&boardtype=L&menuid=15";
    public static String opinion = "https://m.cafe.naver.com/SimpleArticleList.nhn?search.clubid=29055312&search.menuid=23&search.moreDirection=next";

    //자동차 다운로드에 쓰이는 String
    public static String http = "http://";
    public static String company = "company";
    public static String img_url = "img_url";
    public static String car_name = "car_name";
    public static String car_name_kor = "car_name_kor";
    public static String company_kor = "company_kor";
    public static String audi = "AUDI";
    public static String benz = "BENZ";
    public static String bmw = "BMW";
    public static String chevrolet = "CHEVROLET";
    public static String hyundai = "HYUNDAI";
    public static String kia = "KIA";
    public static String Volkswagen = "VOLKSWAGEN";
}
