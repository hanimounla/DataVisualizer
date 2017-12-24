package mounla.hani.datavisualizer;

import android.app.Activity;
import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.Toast;
import android.widget.ZoomButton;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hani Mounla on 2017-12-19.
 */

public class JavaScript extends Activity {

    String url = "https://ussouthcentral.services.azureml.net/workspaces/264711fbdc9d48ba8205ed385fce483e/services/050153fa554e4b5786d21ae02389e276/execute?api-version=2.0&details=true";
    final String accesstoken = "VlDmE4z0FVLayQqOYjcslYbGVUi7uM5kxTzcgVj9aAyXM1s455mZ4a3Vdpi3xfLNwIIYBIlgFEBdj66eX9dsjg==";

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
//                tryPost();
//                JSONObject parameters = new JSONObject();
//                try {
//                    parameters.put("Authorization", "Bearer " + accesstoken);
//                    parameters.put("Content-Type", "application/json");
//                    parameters.put("id", "22");
//                    parameters.put("area", "21.18");
//                    parameters.put("perimeter", "17.21");
//                    parameters.put("compactness", "0.8989");
//                    parameters.put("length", "6.573");
//                    parameters.put("width", "4.033");
//                    parameters.put("asymmetry", "5.78");
//                    parameters.put("groove", "6.231");
//                    parameters.put("wheat_type", "aa");
//                } catch (Exception e) {
//                }
            }
        });
    }
    public String rrsHttpPost() {

        HttpPost post;
        HttpClient client;
        StringEntity entity;

        try {
            // create HttpPost and HttpClient object
            post = new HttpPost(url);
            client = new HttpClient();

            // setup output message by copying JSON body into
            // apache StringEntity object along with content type
            entity = new StringEntity(jsonBody, HTTP.UTF_8);
            entity.setContentEncoding(HTTP.UTF_8);
            entity.setContentType("text/json");

            // add HTTP headers
            post.setHeader("Accept", "text/json");
            post.setHeader("Accept-Charset", "UTF-8");

            // set Authorization header based on the API key
            post.setHeader("Authorization", ("Bearer " + accesstoken));
            post.setEntity(entity);

            // Call REST API and retrieve response content
            HttpResponse authResponse = client.execute(post);

            return EntityUtils.toString(authResponse.getEntity());

        }
        catch (Exception e) {

            return e.toString();
        }

    }

//    public void tryPost() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        final String inputs = "\"Single_wheat_parametes_input\": {\n" +
//                "          \"ColumnNames\": [\"id\",\"area\",\"perimeter\",\"compactness\",\"length\",\"width\",\"asymmetry\",\"groove\",\"wheat_type\"],\n" +
//                "          \"Values\": [[\"\",\"21.18\",\"17.21\",\"0.8989\",\"6.573\",\"4.033\",\"5.78\",\"6.231\",\"\"]]\n" +
//                "        }";
//
//        StringRequest stringRequest = new StringRequest(Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("TAG", "response = "+ response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("TAG", "Error = "+ error);
//            }
//        })
//        {
//            ////
//            @Override
//            public Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("inputs",inputs);
//                return params; //return the parameters
//            }
//            ////
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type", "application/json");
//                headers.put("Authorization", "Bearer " + accesstoken);
//                return headers;
//            }
//        };
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }
}
