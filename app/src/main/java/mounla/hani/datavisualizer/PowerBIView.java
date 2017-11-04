package mounla.hani.datavisualizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PowerBIView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_biview);

        WebView webView = (WebView) findViewById(R.id.mainWebView);
        webView.loadData("<iframe width=\"800\" height=\"600\" " +
                        "src=\"https://app.powerbi.com/view?r=eyJrIjoiNmRjYmEwOGYtYjU2Ny00ZTAyLWIxNDAtNDM3MzY0YmEwZWZlIiwidCI6IjFkNWFhMmNmLTc4NGItNDU2MS05N2RmLWEyNmU3NzU3NGNjNCIsImMiOjl9\" " +
                        "frameborder=\"0\" allowFullScreen=\"true\"></iframe>","text/html","utf-8");
        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setPluginState(WebSettings.PluginState.ON);
        webViewSettings.setBuiltInZoomControls(true);
    }
}
