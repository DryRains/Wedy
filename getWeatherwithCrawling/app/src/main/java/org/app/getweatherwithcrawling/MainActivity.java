package org.app.getweatherwithcrawling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;//크롤링을 위해

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import com.bumptech.glide.Glide;

import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    static MediaPlayer mp;//배경화면을 틀기위한 미디어플레이어(import한후 사용)


    public int time;
    public String temp; //온도
    String weather;//맑음 등 날씨상태
    String yesterday; //어제대비 기온


    LinearLayout linear;
    TextView textview, textview2, textview3, textview4;
    ImageView imgWeather, imgCharacter;


    ImageButton imgBtnCloth, imgBtnMusic, imgBtnMemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //bgm 재생
        mp = MediaPlayer.create(this,R.raw.bgm);
        mp.setLooping(true);//반복재생
        mp.start();//재생시작

       final Bundle bundle = new Bundle(); //번들이란?Bundle은 여러가지의 타입의 값을 저장하는 Map 클래스
        final Bundle bundle2 = new Bundle(); //맑음 등 날씨상태를 전달하기위해 또다른 번들 생성
        final Bundle bundle3 = new Bundle(); //오늘의 행동제안(운세)을 전달하기위한 세번째 번들


        linear = (LinearLayout) findViewById(R.id.linear1);
        textview = (TextView) findViewById(R.id.textView1);
        imgWeather = (ImageView) findViewById(R.id.imageview2);
        imgCharacter = (ImageView) findViewById(R.id.Character);

        textview2 = (TextView) findViewById(R.id.textView2);
        textview3 = (TextView) findViewById(R.id.textView3);
        textview4 = (TextView) findViewById(R.id.textView4);
        imgBtnCloth = (ImageButton) findViewById(R.id.imageButton1);
        imgBtnMusic = (ImageButton) findViewById(R.id.imageButton2);


        //Intent 생성과정 - 인텐트는 다른 액티비티로 전환되도록, 즉 화면 전환하는 과정에서 필요
        imgBtnCloth.setOnClickListener(new View.OnClickListener() {//해당 버튼을 눌렀을때 다음 화면으로 넘어가는
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Recommend_DailyLook.class); //(현재 액티비티, 이동할 액티비티)
                intent1.putExtra("temp",temp); //데이터 보내기
                intent1.putExtra("weather",weather);
                startActivity(intent1); //생성한 인텐트 객체 활성화 및 이동
            }
        });

        imgBtnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Recommend_Music.class); //(현재 액티비티, 이동할 액티비티)
                intent2.putExtra("weather",weather);
                startActivity(intent2); //생성한 인텐트 객체 활성화 및 이동
            }
        });










        new Thread() { //스레드를 하나 더 생성 -> 이걸 해주지 않으면 한 스레드에서 날씨정보도 가져오고 ui도 동작시키기때문에 과부화
            @Override
            public void run() {


                try {//현재 기온 가져오기
                    Document doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%84%9C%EC%9A%B8+%EB%82%A0%EC%94%A8").get();    //URL 웹사이트에 있는 html 코드를 다 끌어오기
                    Elements tempurature = doc.select(".temperature_text");    //끌어온 html에서 클래스네임이 "temperature_text" 인 값만 선택해서 빼오기
                    temp = tempurature.get(0).text().substring(4,7); //temp = contents.get(0).text() 은 contents에서 선별한 클래스중 첫째값을 가져오는 것 그게 현재온도:0'C 이런식으로 나오기때문에 온도만 나오도록 문자를 자르도록 subString
                    //하지만 영하일때 -가 4번째 글자이기때문에 -를 가져오기위해선 4번째글자부터 가져와야함
                    temp = temp.replace("도",""); //영하가 아닐땐 4번째글자 도가 딸려나오기에 이를 제거
                    temp = temp.replace("°",""); //한자릿수의 기온일시 ° 가 딸려나오기에 이를 제거해줌

                    bundle.putString("temperature", temp); //bundle에 temperature라는 키로 temp값 전송
                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start(); //스레드 끝

        new Thread() { //이번엔 맑음 등의 날씨정보를 전달하기위한 스레드
            @Override
            public void run() {


                try {//현재 날씨 가져오기
                    Document doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%84%9C%EC%9A%B8+%EB%82%A0%EC%94%A8").get();    //URL 웹사이트에 있는 html 코드를 다 끌어오기
                    Elements weatherstatus = doc.select(".summary");
                    String weatherinfo = weatherstatus.get(0).text();



                    bundle2.putString("weatherstatus", weatherinfo);
                    Message msg2 = handler2.obtainMessage();
                    msg2.setData(bundle2);
                    handler2.sendMessage(msg2);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start(); //스레드 끝

        new Thread() { //오늘의 운세,행동제안 메세지를 전달하기위한 스레드
            @Override
            public void run() {


                try {//오늘의 운세 가져오기
                    Document doc = Jsoup.connect("https://unse.daily.co.kr/?p=zodiac").get();    //URL 웹사이트에 있는 html 코드를 다 끌어오기
                    Elements todayMsg = doc.select(".txt");
                    String todayMessage = todayMsg.get(14).text(); //98년생운세



                    bundle3.putString("todayMsg", todayMessage);
                    Message msg3 = handler3.obtainMessage();
                    msg3.setData(bundle3);
                    handler3.sendMessage(msg3);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start(); //스레드 끝


    }

    protected void onStart() {//onStopt상태에서 다시 되돌아오면 onStart로 되돌아옴

        super.onStart();
        setBackground();
        setImgCharacter();

        mp.start(); //만약 onStop에서 음악이 일시정지되었다면 다시 돌아올떄 이어서 재생되도록

    }

    @Override

    protected void onStop(){//다른 액티비티나,홈화면으로 나갔을때 이부분 실행
        super.onStop();
        mp.pause();//음악 일시정지


    }

    protected void onDestroy(){//앱이 완전히 파괴(종료)되었을때 실행
        super.onDestroy();
        mp.stop();
    }




        Handler handler = new Handler() { //핸들러는 메인스레드와 서브스레드를 연결시켜주는 역할, 메인스레드에는 ui에 관한 기능만 넣어야 과부화가 걸리지 않기에 위의 날씨정보 기능을 가진 서브스레드와 연동필요
            @Override
            public void handleMessage(@NonNull Message msg) { //핸들러는 메인스레드와 서브스레드를 연결해주는 역할
                Bundle bundle = msg.getData();    //new Thread에서 작업한 결과물 받기
                textview.setText(bundle.getString("temperature")+"°C");    //암호키에 맞는 받아온 데이터 textView에 출력
            }
        };

        Handler handler2 = new Handler() { //맑음 등의 날씨정보를 전달해주는 두번째쓰레드와 연결
            @Override
            public void handleMessage(@NonNull Message msg) {
                Bundle bundle2 = msg.getData();
                String weatherinfo = bundle2.getString("weatherstatus"); //"어제보다 x도 높아요 맑음
                weather = weatherinfo.substring(12); //맑음등 날씨정보만 나오도록 13번째 글짜부터 끝까지 자르기
                yesterday = weatherinfo.substring(0,12); //어제보다 x도 높아요만 떼어내기
                textview2.setText(weather);
                textview3.setText(yesterday);

                setWeather();
            }
        };

        Handler handler3 = new Handler() { //오늘의 운세,행동제안인 세번째 쓰레드와 연결
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle3 = msg.getData();
            textview4.setText(bundle3.getString("todayMsg"));
        }
    };

        public void setBackground(){
            long now = System.currentTimeMillis(); //시스템의 시간값을 가져옴
            Date date = new Date(now);
            SimpleDateFormat sdf = new SimpleDateFormat("HH"); //시간만 출력하는 형식으로
            String getTime = sdf.format(date); //지정한 형식에 시간값을 넣어 값 가져옴

            time = Integer.parseInt(getTime); //시간을 조건문으로 비교하기위해 정수형으로

            //아래는 시간에 따라 배경화면 설정
            if(time<=17 && time>=7){
                linear.setBackgroundResource(R.drawable.afternoon);
            }
            else{
                linear.setBackgroundResource(R.drawable.night);
            }
        }

        public void setImgCharacter(){//메인화면에 고양이 캐릭터 이미지
            if(time>=7 && time<18){
                Glide.with(this).load(R.raw.playingcat).into(imgCharacter); //gif를 이미지뷰에넣는것(gradle조정도 필요함)
            }
            else if(time>=18 && time<=24){
                Glide.with(this).load(R.raw.cordingcat).into(imgCharacter);
            }
            else{
                Glide.with(this).load(R.raw.sleepcat).into(imgCharacter);
            }


        }


        public void setWeather() { //날씨 이미지 정하는 메소드
            if(time>=7 && time<=17){
                if (weather.contains("맑음")) {
                imgWeather.setImageResource(R.drawable.sunny);
                }
                else if (weather.contains("흐림")) {
                    imgWeather.setImageResource(R.drawable.cloudy);

                }
                else if (weather.contains("구름")) {
                    imgWeather.setImageResource(R.drawable.cloudy_a);
                }
                else if (weather.contains("비")) {
                    imgWeather.setImageResource(R.drawable.rainy);
                }
                else if (weather.contains("눈")) {
                    imgWeather.setImageResource(R.drawable.snow);
                }
                else if (weather.contains("바람")) {
                    imgWeather.setImageResource(R.drawable.windy);
                }
                else if (weather.contains("번개")) {
                    imgWeather.setImageResource(R.drawable.thunder);
                }
            }
            else{
                if (weather.contains("맑음")) {
                imgWeather.setImageResource(R.drawable.sunny_n);
                }
                else if (weather.contains("흐림")) {
                    imgWeather.setImageResource(R.drawable.cloudy);

                }
                else if (weather.contains("구름")) {
                    imgWeather.setImageResource(R.drawable.cloudy_n);
                }
                else if (weather.contains("비")) {
                    imgWeather.setImageResource(R.drawable.rainy);
                }
                else if (weather.contains("눈")) {
                    imgWeather.setImageResource(R.drawable.snow);
                }
                else if (weather.contains("바람")) {
                    imgWeather.setImageResource(R.drawable.windy);
                }
                else if (weather.contains("번개")) {
                    imgWeather.setImageResource(R.drawable.thunder);
                }
            }
        }
}

