package com.example.androidtest.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidtest.R;
import com.example.androidtest.listview.Result;
import com.example.androidtest.listview.ResultAdapt;
import com.example.androidtest.tool.ActivityCollector;
import com.example.androidtest.tool.Ingredient;
import com.example.androidtest.tool.Plant;
import com.example.androidtest.tool.getPath;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends BaseActivity {
    private View progress;
    private ImageView imageView;
    private  String ak="";
    private  String sk="";
    private String path="";
    List<Result> results=new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        listView=(ListView)findViewById(R.id.list_view);
        progress = findViewById(R.id.layout_progress);
        final ResultAdapt resultAdapt= new ResultAdapt(ResultActivity.this,
                R.layout.result_item, results);
        final Intent intent=getIntent();
        ak=intent.getStringExtra("ak");
        sk=intent.getStringExtra("sk");
        final String img=intent.getStringExtra("data");
        final String a=intent.getStringExtra("data1");
        imageView =(ImageView)findViewById(R.id.image);
        path=img;
        final Uri uri = Uri.parse(img);
        String [] projection={MediaStore.Images.Media.DATA};
        Cursor cursor=getContentResolver().query(uri, projection, null, null, null);
        path= getPath.getPath(uri,cursor,path);
        imageView.setImageURI(uri);
        final Handler mHandler = new Handler() {
            String temp;
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                temp=(String) msg.obj;
                try {
                    JSONObject result=new JSONObject(temp);
                    JSONArray data=result.getJSONArray("result");
                    if (data.length()>=3){
                        try {
                            JSONObject json = data.getJSONObject(0);
                            Result first=new Result(json.getString("name"),json.getDouble("score"), R.mipmap.first);
                            results.add(first);
                            json=data.getJSONObject(1);
                            Result second=new Result(json.getString("name"),json.getDouble("score"),R.mipmap.second);
                            results.add(second);
                            json=data.getJSONObject(2);
                            Result third=new Result(json.getString("name"),json.getDouble("score"),R.mipmap.third);
                            results.add(third);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(data.length()>1){
                        try {
                            JSONObject json = data.getJSONObject(0);
                            Result first=new Result(json.getString("name"),json.getDouble("score"),R.mipmap.first);
                            results.add(first);
                            json=data.getJSONObject(1);
                            Result second=new Result(json.getString("name"),json.getDouble("score"),R.mipmap.second);
                            results.add(second);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        try {
                            JSONObject json = data.getJSONObject(0);
                            Result first=new Result(json.getString("name"),json.getDouble("score"),R.mipmap.first);
                            results.add(first);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    progress.setVisibility(View.GONE);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"网络错误！请检查网络并重试！",Toast.LENGTH_LONG).show();
                    progress.setVisibility(View.GONE);
                    e.printStackTrace();
                }

                listView.setAdapter(resultAdapt);
            }
        };
        new Thread(new Runnable(){
            @Override
            public void run() {
                //耗时操作，完成之后发送消息给Handler，完成UI更新；
                progress.setVisibility(View.VISIBLE);
                Message msg =Message.obtain();
                if(a.equals("1")){
                    msg.obj = Plant.plant(path,ak,sk);
                }//可以是基本类型，可以是对象，可以是List、map等;
                else{
                    msg.obj = Ingredient.ingredient(path,ak,sk);
                }
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    public void again(View view) {
        Intent intent=new Intent(ResultActivity.this,Home.class);
        intent.putExtra("ak",ak);
        intent.putExtra("sk",sk);
        startActivity(intent);
    }

    public void exitApp(View view) {
        ActivityCollector.finishAll();
    }
}