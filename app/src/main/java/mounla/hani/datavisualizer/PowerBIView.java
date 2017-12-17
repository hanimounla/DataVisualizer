package mounla.hani.datavisualizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PowerBIView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_biview);

        WebView webView = (WebView) findViewById(R.id.mainWebView);

        String powerBI = "<iframe width=\"400\" height=\"800\" " +
                "src=\"https://app.powerbi.com/view?r=eyJrIjoiNGRkN2Q0OGYtNjA5Zi00NTUzLThlYjQtYzlkZDIzZmM3YTBkIiwidCI6IjFkNWFhMmNmLTc4NGItNDU2MS05N2RmLWEyNmU3NzU3NGNjNCIsImMiOjl9\" " +
                "frameborder=\"0\" allowFullScreen=\"true\"></iframe>";
        webView.loadData(powerBI,"text/html","utf-8");
        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setPluginState(WebSettings.PluginState.ON);
        webViewSettings.setBuiltInZoomControls(true);
        webViewSettings.setDisplayZoomControls(false);
    }
}
