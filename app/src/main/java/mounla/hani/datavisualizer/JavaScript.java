package mounla.hani.datavisualizer;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

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
                Dude d = new Dude();
                d.execute("");

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

        Button DatadotGov = (Button)findViewById(R.id.testDatadotGovBTN);
        DatadotGov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button sp_today = (Button)findViewById(R.id.test_sp_today_BTN);
        sp_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dude_SP d = new Dude_SP();
                d.execute("");
            }
        });
    }

    public class Dude_SP extends AsyncTask<String,String,String>
    {

        String sp_today_url = "https://sp-today.com/en/currency/us_dollar";
        String sell_value, buy_value;

        @Override
        protected void onPostExecute(String r) {

            Toast.makeText(JavaScript.this, "Sell: " + sell_value + "\n" + "Buy: " + buy_value, Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(String... strings) {

            Document doc = null;
            try {
                doc = Jsoup.connect(sp_today_url).get();
                Elements elements = doc.getElementsByAttributeValue("class", "table cur-info-table");
                Element element = elements.get(0);
                List<Node> nodes = element.childNodes();

                Node values = nodes.get(2);
                Node values_in = values.childNodes().get(1);
                List <Node> dollars = values_in.childNodes();

                Element sell_element = (Element) dollars.get(7);
                Element buy_element = (Element)dollars.get(3);

                sell_value = sell_element.text();
                buy_value = buy_element.text();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public class Dude extends AsyncTask<String,String,String>
    {

        String responseString;
        JSONObject responseJSON;

        @Override
        protected void onPostExecute(String r) {
            try {
                responseJSON = new JSONObject(responseString);
                JSONObject Results = responseJSON.getJSONObject("Results");
                JSONObject Predected_Wheat_type_output = Results.getJSONObject("Predected_Wheat_type_output");
                JSONObject value = Predected_Wheat_type_output.getJSONObject("value");
                JSONArray Values = value.getJSONArray("Values");
                JSONArray wheat_type = Values.getJSONArray(0);
                Toast.makeText(JavaScript.this, wheat_type.get(0).toString(), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        @Override
        protected String doInBackground(String... strings) {
            responseString = rrsHttpPost();
            return null;
        }
    }
    public String rrsHttpPost() {

        HttpPost post;
        AndroidHttpClient client ;
        StringEntity entity;
        String Body = "{\n" +
                "\t\"Inputs\": {\n" +
                "        \"Single_wheat_parametes_input\": {\n" +
                "          \"ColumnNames\": [\"id\",\"area\",\"perimeter\",\"compactness\",\"length\",\"width\",\"asymmetry\",\"groove\",\"wheat_type\"],\n" +
                "          \"Values\": [[\"\",\"13.07\",\"13.92\",\"0.848\",\"5.472\",\"2.994\",\"5.304\",\"5.395\",\"\"]]\n" +
                "        }\n" +
                "      }\n" +
                "}";
        JSONObject jsonBody =null;
        try {
            jsonBody = new JSONObject(Body);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            // create HttpPost and HttpClient object
            post = new HttpPost(url);
            client = AndroidHttpClient.newInstance("Microsoft");

            // setup output message by copying JSON body into
            // apache StringEntity object along with content type
            entity = new StringEntity(String.valueOf(jsonBody));
            entity.setContentType("application/json");

            // add HTTP headers
            post.setHeader("Accept", "text/json");
            post.setHeader("Accept-Charset", "UTF-8");

            // set Authorization header based on the API key
            post.setHeader("Authorization", ("Bearer " + accesstoken));
            post.setHeader("Content-Type", "application/json");
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
