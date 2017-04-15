package com.va.shabbirhussain.gonegative;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.http.BasicNameValuePair;
import com.koushikdutta.async.http.NameValuePair;
import com.koushikdutta.ion.Ion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class SignState extends AppCompatActivity {

    private String name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_state);

        Bundle b=getIntent().getExtras();
         name=b.getString("name");
         email = b.getString("email");
        String photourl = b.getString("image");



        TextView txt=(TextView)findViewById(R.id.name);
        txt.setText(name);
        txt=(TextView)findViewById(R.id.email);
        txt.setText(email);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        new Demo().execute();

        Ion.with(imageView)
                .error(R.color.error)
                .load(photourl);
        cal();
    }

    public void cal(){
        try{
            BufferedReader reader = null;
            //Toast.makeText(SignState.this,"On do in background",Toast.LENGTH_LONG).show();
            URL url = new URL("http://demo.shivambharadwaj.com/GoNegative/public/createUser");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            List<NameValuePair> params2 = new ArrayList<NameValuePair>();
            params2.add(new BasicNameValuePair("naam", name));
            params2.add(new BasicNameValuePair("email", email));
            //params.add(new BasicNameValuePair("thirdParam", paramValue3));

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(getQuery(params2));
            //Toast.makeText(SignState.this,"Hi",Toast.LENGTH_LONG).show();

            writer.flush();
            writer.close();
            os.close();

            conn.connect();



            InputStream stream = conn.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

            }

            Toast.makeText(SignState.this,buffer.toString(),Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
            EditText txt=(EditText) findViewById(R.id.error);
            txt.setText(e.getMessage());
            Toast.makeText(SignState.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public class Demo extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(SignState.this,"On Pre Execute",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... params) {
//           try{
//               Toast.makeText(SignState.this,"On do in background",Toast.LENGTH_LONG).show();
//               URL url = new URL("http://demo.shivambharadwaj.com/GoNegative/public/createUser");
//               HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//               conn.setReadTimeout(10000);
//               conn.setConnectTimeout(15000);
//               conn.setRequestMethod("POST");
//               conn.setDoInput(true);
//               conn.setDoOutput(true);
//
//               List<NameValuePair> params2 = new ArrayList<NameValuePair>();
//               params2.add(new BasicNameValuePair("naam", name));
//               params2.add(new BasicNameValuePair("email", email));
//               //params.add(new BasicNameValuePair("thirdParam", paramValue3));
//
//               OutputStream os = conn.getOutputStream();
//               BufferedWriter writer = new BufferedWriter(
//                       new OutputStreamWriter(os, "UTF-8"));
//
//               writer.write(getQuery(params2));
//               Toast.makeText(SignState.this,"Hi",Toast.LENGTH_LONG).show();
//               Toast.makeText(SignState.this,getQuery(params2),Toast.LENGTH_LONG).show();
//               writer.flush();
//               writer.close();
//               os.close();
//
//               conn.connect();
//
//           }catch (Exception e){
//               e.printStackTrace();
//           }
            return null;
        }
    }

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        //Toast.makeText(SignState.this,result.toString(),Toast.LENGTH_LONG).show();

        return result.toString();


    }

}
