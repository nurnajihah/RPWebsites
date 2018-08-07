package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Main2Activity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        webView = findViewById(R.id.webView);

        Intent intentReceived = getIntent();
        int Category = intentReceived.getIntExtra("Category", 0);
        int Site = intentReceived.getIntExtra("Site", 0);

        if (Category == 0)  {
            String[] RP = getResources().getStringArray(R.array.URLRP);
            if (Site == 0)  {
                webView.loadUrl(RP[0]);
            }
            else    {
                webView.loadUrl(RP[1]);
            }
        }
        else    {
            String[] SOI = getResources().getStringArray(R.array.URLSOI);
            if (Site == 0)  {
                webView.loadUrl(SOI[0]);
            }
            else    {
                webView.loadUrl(SOI[1]);
            }
        }
    }
}
