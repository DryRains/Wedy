package org.app.getweatherwithcrawling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


public class Recommend_Music extends AppCompatActivity{
    public String weather;
    public String url;

    WebView webView;
    EditText musicname;
    ImageButton imgbtnSearch;
    TextView textView;
    ImageView imgCharactermusic;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_music);

        Intent intent2 = getIntent(); //메인 액티비티에서 보낸 데이터 받기
        weather = intent2.getStringExtra("weather");


        webView = (WebView) findViewById(R.id.webView);
        musicname = (EditText) findViewById(R.id.editText);
        imgbtnSearch = (ImageButton) findViewById(R.id.imgbtnSearch);
        textView = (TextView) findViewById(R.id.textView);
        imgCharactermusic = (ImageView) findViewById(R.id.musiccharacter);

        Glide.with(this).load(R.raw.musiccat).into(imgCharactermusic);


        Settext();
        RecommendMusicWebView();


        imgbtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Searchmusic();
            }
        });

    }
    public void Settext(){//상단에 날씨별로 텍스트 띄워주는 메소드
        if(weather.contains("맑음")){
            textView.setText("맑은 날에 어울리는 음악을 추천해드릴게요");
        }

        else if(weather.contains("흐림")){
            textView.setText("흐린 날에 어울리는 음악을 추천해드릴게요");
        }

        else if(weather.contains("구름")){
            textView.setText("구름 많은 날에 어울리는 음악을 추천해드릴게요");
        }

        else if(weather.contains("비")){
            textView.setText("비오는 날에 어울리는 음악을 추천해드릴게요");
        }

        else if(weather.contains("눈")){
            textView.setText("눈오는 날에 어울리는 음악을 추천해드릴게요");
        }
    }



    public void Searchmusic(){ //하단에 edittext 창에서 입력한 음악이름을 검색하는 메소드
        String str = musicname.getText().toString();
        if(str.equals("검색할 노래를 입력하세요")||str.equals("")){ //만약 검색어를 따로 넣지않고 버튼 누르면 현재 날씨에 맞는 음악 알아서 검색하도록.
            if(weather.contains("맑음")){
                url = "https://www.youtube.com/results?search_query=%EB%A7%91%EC%9D%80%EB%82%A0+%ED%94%8C%EB%A0%88%EC%9D%B4%EB%A6%AC%EC%8A%A4%ED%8A%B8";
                webView.loadUrl(url);
            }

            else if(weather.contains("흐림")){
                url = "https://www.youtube.com/results?search_query=%EC%9A%B0%EC%9A%B8+%ED%94%8C%EB%A0%88%EC%9D%B4%EB%A6%AC%EC%8A%A4%ED%8A%B8";
                webView.loadUrl(url);
            }

            else if(weather.contains("구름")){
                url = "https://www.youtube.com/results?search_query=%EA%B5%AC%EB%A6%84+%ED%94%8C%EB%A0%88%EC%9D%B4%EB%A6%AC%EC%8A%A4%ED%8A%B8";
                webView.loadUrl(url);
            }

            else if(weather.contains("비")){
                url = "https://www.youtube.com/results?search_query=%EB%B9%84%EC%98%A4%EB%8A%94+%EB%82%A0+%ED%94%8C%EB%A0%88%EC%9D%B4%EB%A6%AC%EC%8A%A4%ED%8A%B8";
                webView.loadUrl(url);
            }

            else if(weather.contains("눈")){
                url = "https://www.youtube.com/results?search_query=%EB%88%88%EC%98%A4%EB%8A%94+%EB%82%A0+%ED%94%8C%EB%A0%88%EC%9D%B4%EB%A6%AC%EC%8A%A4%ED%8A%B8";
                webView.loadUrl(url);
            }
        }
        else { //검색어를 따로 입력한경우
            str = str.replace(" ", "+"); //유튜브 주소에선 띄어쓰기를 +로 입력해야함

            url = "https://www.youtube.com/results?search_query=" + str;
            webView.loadUrl(url);
        }

    }



    public void RecommendMusicWebView() {
        webView.getSettings().setJavaScriptEnabled(true); //웹뷰를 활성화 하기위한 초기설정정
        webView.setWebChromeClient(new WebChromeClient()); //크롬에서 최적화하기위한 세팅
        webView.setWebViewClient(new WebViewClientClass()); //일반 브라우저에서 최적화하기위한 세팅
        webView.getSettings().setUseWideViewPort(true); //와이드뷰로 보기
        webView.getSettings().setLoadWithOverviewMode(true); // 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
        //줌 기능 생성
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);


        if(weather.contains("맑음")){
        url = "https://www.genie.co.kr/playlist/tags?tags=ST0013%7C%7C%EB%A7%91%EC%9D%80%EB%82%A0";
        webView.loadUrl(url);
        }

        else if(weather.contains("흐림")||weather.contains("구름")){
            url = "https://www.genie.co.kr/playlist/tags?tags=ST0018%7C%7C%ED%9D%90%EB%A6%B0%EB%82%A0";
            webView.loadUrl(url);
        }

        else if(weather.contains("비")){
            url = "https://www.genie.co.kr/playlist/tags?tags=ST0019%7C%7C%EB%B9%84%EC%98%A4%EB%8A%94%EB%82%A0";
            webView.loadUrl(url);
        }

        else if(weather.contains("눈")){
            url = "https://www.genie.co.kr/playlist/tags?tags=ST0026%7C%7C%EB%88%88%EC%98%A4%EB%8A%94%EB%82%A0";
            webView.loadUrl(url);
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //웹뷰상태에서 뒤로가기 눌르면 원래는 앱이 꺼짐 -> 전화면으로돌아가기위해 설정
        if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient { //웹뷰최적화를위한작업
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
