package org.app.getweatherwithcrawling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Random;

public class Recommend_DailyLook extends AppCompatActivity {
    public int temp;
    public String weatherinfo;


    TextView textWeatherInfo, textMain;
    ImageView imgCloth,imgCharacterCloth;
    ImageButton imgbtnRecall;
    ImageButton imgbtnOuter, imgbtnTop, imgbtnBottom;

    private final static int[] cody1 = new int[] { //랜덤하게 옷 이미지를 뽑기위한 이미지배열
            R.drawable.codycoat1,
            R.drawable.codycoat2,
            R.drawable.codycoat3,
            R.drawable.codyzibup1,
            R.drawable.codyzibup2,
            R.drawable.codyjacket1,
            R.drawable.codyjacket2,
            R.drawable.codyjacket3,
            R.drawable.codyjacket4,
            R.drawable.codyjacket5};
    private final static int[] cody2 = new int[]{
            R.drawable.codypadding1,R.drawable.codypadding2,R.drawable.codypadding3,R.drawable.codypadding4,R.drawable.codypadding5
    };

    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_daily_look);

        Intent intent1 = getIntent(); //메인 액티비티에서 보낸 데이터 받기
        String str = intent1.getStringExtra("temp"); //메인액티비티에서 temp라는 코드로 보낸 데이터를 받음
        weatherinfo = intent1.getStringExtra("weather");

        temp = Integer.parseInt(str);

        textWeatherInfo = (TextView) findViewById(R.id.textWeatherInfo);
        textMain = (TextView) findViewById(R.id.textMain);
        imgCloth = (ImageView) findViewById(R.id.imageview1);
        imgbtnRecall = (ImageButton) findViewById(R.id.imageButtonRecall);
        imgCharacterCloth = (ImageView) findViewById(R.id.charactercloth);

        imgbtnOuter = (ImageButton) findViewById(R.id.imageButtonOuter);
        imgbtnTop = (ImageButton) findViewById(R.id.imageButtonTop);
        imgbtnBottom = (ImageButton) findViewById(R.id.imageButtonBottom);

        Glide.with(this).load(R.raw.changingcat).into(imgCharacterCloth);

        imgbtnRecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickCloth();
            }
        });

        imgbtnOuter.setOnClickListener(new View.OnClickListener() { //이미지뷰에 뜬 사진의 아우터 쇼핑
            @Override
            public void onClick(View v) {
                if(imgCloth.getTag().equals(R.drawable.codycoat1)){ //만약 현재 이미지뷰의 태그 = hoodzip의 태그가 같다면 해당 옷정보가 나오도록
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2229637"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codycoat2)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2267498"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codycoat3)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2231402"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyzibup1)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2007322"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyzibup2)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2273431"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket1)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2257537"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket2)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2228350"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket3)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2230843"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket4)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2277798"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket5)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2086720"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding1)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2259826"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding2)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2265820"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding3)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2243216"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding4)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2270407"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding5)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2272898"));
                    startActivity(intent);
                }
            }
        });

        imgbtnTop.setOnClickListener(new View.OnClickListener() { //이미지뷰에 뜬 사진의 상의를 쇼핑
            @Override
            public void onClick(View v) {
                if(imgCloth.getTag().equals(R.drawable.codycoat1)){ //만약 현재 이미지뷰의 태그 = hoodzip의 태그가 같다면 해당 옷정보가 나오도록
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2175687"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codycoat2)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2173074"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codycoat3)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2107984"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyzibup1)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/1923464"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyzibup2)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2266736"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket1)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2264505"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket2)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2228498"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket3)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2172711"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket4)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2264800"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket5)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2074198"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding1)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2238443"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding2)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2265056"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding3)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2087493"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding4)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/1922365"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding5)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2272580"));
                    startActivity(intent);
                }
            }
        });

        imgbtnBottom.setOnClickListener(new View.OnClickListener() { //이미지뷰에 뜬 사진의 하의를 쇼핑
            @Override
            public void onClick(View v) {
                if(imgCloth.getTag().equals(R.drawable.codycoat1)){ //만약 현재 이미지뷰의 태그 = hoodzip의 태그가 같다면 해당 옷정보가 나오도록
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2225245"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codycoat2)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2005223"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codycoat3)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2112926"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyzibup1)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2005223"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyzibup2)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2184518"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket1)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2188262"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket3)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2219645"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket4)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2112926"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codyjacket5)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2079005"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding1)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2241168"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding2)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2234980"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding3)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2129048"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding4)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2188356"));
                    startActivity(intent);
                }
                else if(imgCloth.getTag().equals(R.drawable.codypadding5)){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lookpin.co.kr/products/2231814"));
                    startActivity(intent);
                }



            }
        });




    textWeatherInfo.setText("현재 기온: "+temp+"°C ("+weatherinfo+")");
    pickCloth();
    setmainText();

    }



    public void pickCloth(){

        if(temp<=12&&temp>=-2){ //메인액티비티에서 가져온 온도값으로 조건문을통해 옷 배열 출력
            int i = random.nextInt(cody1.length); //이미지 갯수만큼 랜덤 정수로 두고

            imgCloth.setImageResource(cody1[i]); //랜덤으로 이미지배열에서 사진 출력하고
            imgCloth.setTag(cody1[i]); //그 사진의 tag값을 현재 이미지뷰에 저장
        }
        else if(temp<-2&&temp>=-10){
            int i = random.nextInt(cody2.length);

            imgCloth.setImageResource(cody2[i]);
            imgCloth.setTag(cody2[i]);
        }
    }

    public void setmainText(){ //옷 추천 문구
        if(temp<=12&&temp>=-2){
            textMain.setText("쌀쌀한 날씨, 이 옷은 어때요?");
        }
        else if(temp<-2&&temp>=-10){
            textMain.setText("추워요! 따듯한 옷들을 추천해 드릴게요");
        }


    }

}