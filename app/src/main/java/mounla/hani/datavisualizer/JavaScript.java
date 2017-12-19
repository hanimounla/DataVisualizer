package mounla.hani.datavisualizer;

import android.app.Activity;
import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.ZoomButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hani Mounla on 2017-12-19.
 */

public class JavaScript extends Activity {

    String Hani;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javascript);
        WebView webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/index.html");

        Button azureBTN = (Button)findViewById(R.id.testAzureBTN);
        azureBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://ussouthcentral.services.azureml.net/workspaces/264711fbdc9d48ba8205ed385fce483e/services/050153fa554e4b5786d21ae02389e276/execute?api-version=2.0&details=true";
                final String accesstoken = "VlDmE4z0FVLayQqOYjcslYbGVUi7uM5kxTzcgVj9aAyXM1s455mZ4a3Vdpi3xfLNwIIYBIlgFEBdj66eX9dsjg==";
//                String body = "{\n" +
//                        "\t\"Inputs\": {\n" +
//                        "        \"Single_wheat_parametes_input\": {\n" +
//                        "          \"ColumnNames\": [\n" +
//                        "            \"id\",\n" +
//                        "            \"area\",\n" +
//                        "            \"perimeter\",\n" +
//                        "            \"compactness\",\n" +
//                        "            \"length\",\n" +
//                        "            \"width\",\n" +
//                        "            \"asymmetry\",\n" +
//                        "            \"groove\",\n" +
//                        "            \"wheat_type\"\n" +
//                        "          ],\n" +
//                        "          \"Values\": [\n" +
//                        "            [\n" +
//                        "              \"\",\n" +
//                        "              \"21.18\",\n" +
//                        "              \"17.21\",\n" +
//                        "              \"0.8989\",\n" +
//                        "              \"6.573\",\n" +
//                        "              \"4.033\",\n" +
//                        "              \"5.78\",\n" +
//                        "              \"6.231\",\n" +
//                        "              \"\"\n" +
//                        "            ]\n" +
//                        "          ]\n" +
//                        "        }\n" +
//                        "      },\n" +
//                        "      \"GlobalParameters\": {}\n" +
//                        "}";
                JSONObject parameters = new JSONObject();
                try {
                    parameters.put("key", "value");
                } catch (Exception e) {
                }
                JsonObjectRequest request = new JsonObjectRequest(Method.POST, url, parameters,new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String Hani = response.toString();
                        Log.i("onResponse", Hani);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String Hani = error.toString();
                        Log.e("onErrorResponse", Hani);
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        // Basic Authentication
                        //String auth = "Basic " + Base64.encodeToString(CONSUMER_KEY_AND_SECRET.getBytes(), Base64.NO_WRAP);

                        headers.put("Authorization", "Bearer " + accesstoken);
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map <String, String> hashMap = new HashMap<>();
                        hashMap.put("id", "22");
                        hashMap.put("area", "21.18");
                        hashMap.put("perimeter", "17.21");
                        hashMap.put("compactness", "0.8989");
                        hashMap.put("length", "6.573");
                        hashMap.put("width", "4.033");
                        hashMap.put("asymmetry", "5.78");
                        hashMap.put("groove", "6.231");
                        hashMap.put("wheat_type", "aa");
                        return hashMap;
                    }
                };
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Authorization", "Bearer ");
//                params.put("Content-Type", "application/json");
//                params.put("body", body);
//
//                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
//                        url, new JSONObject(params),
//                        new Response.Listener<JSONObject>() {
//                            public void onResponse(JSONObject response) {
//                                Hani = response.toString();
//                                Log.d("JSONPost", Hani);
//                                //pDialog.hide();
//                            }
//                        }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Hani = error.getMessage();
//                        VolleyLog.d("JSONPost", "Error: " + Hani);
//                        //pDialog.hide();
//                    }
//
//                });
            }
        });
    }
}
