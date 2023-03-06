package com.example.androidtest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidtest.R;
import com.example.androidtest.db.dbHelper;

public class Update extends BaseActivity {
    private dbHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dbhelper = new dbHelper(this, "Info.db", null, 1);
        getSupportActionBar().setTitle("修改信息");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void init(View view) {
        EditText user = (EditText) findViewById(R.id.username);
        EditText pws = (EditText) findViewById(R.id.pws);
        EditText pws1 = (EditText) findViewById(R.id.pws1);
        EditText eak = (EditText) findViewById(R.id.eak);
        user.setClickable(true);
        user.setFocusableInTouchMode(false);
        pws.setClickable(true);
        pws.setFocusableInTouchMode(false);
        pws1.setClickable(true);
        pws1.setFocusableInTouchMode(false);
        eak.setClickable(true);
        eak.setFocusableInTouchMode(false);
        view.invalidate();
    }

    public void CheckUser(View view) {
        init(view);
        EditText username = (EditText) findViewById(R.id.username);
        EditText pws = (EditText) findViewById(R.id.pws);
        ImageView ckUser = (ImageView) findViewById(R.id.ckUser);
        ImageView ckPws = (ImageView) findViewById(R.id.ckPws);
        ImageView ckPws1 = (ImageView) findViewById(R.id.ckPws1);
        ckPws.setImageResource(0);
        ckPws1.setImageResource(0);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String sql = "select * from Info where username= '" + username.getText().toString() + "'";
        System.out.println(db.rawQuery(sql, null).getCount());
        if (username.getText().toString().equals("")){
            Toast.makeText(this, "请输入用户名！", Toast.LENGTH_SHORT).show();
            ckUser.setImageResource(R.mipmap.no);
            ckUser.invalidate();
        } else if (db.rawQuery(sql, null).getCount() == 0) {
            Toast.makeText(this, "用户不存在！", Toast.LENGTH_SHORT).show();
            ckUser.setImageResource(R.mipmap.no);
            ckUser.invalidate();
        } else {
            ckUser.setImageResource(R.mipmap.yes);
            ckUser.invalidate();
            pws.setFocusable(true);
            pws.setFocusableInTouchMode(true);
            pws.requestFocus();
            pws.requestFocusFromTouch();
            InputMethodManager inputManager =
                    (InputMethodManager) pws.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(pws, 0);
        }
    }

    public void CheckPws(View view) {
        init(view);
        EditText pws = (EditText) findViewById(R.id.pws);
        EditText pws1 = (EditText) findViewById(R.id.pws1);
        EditText username = (EditText) findViewById(R.id.username);
        ImageView ckPws = (ImageView) findViewById(R.id.ckPws);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String sql = "select * from Info where username= '" + username.getText().toString() + "' and password='"
                +pws.getText().toString()+"'";
        System.out.println(db.rawQuery(sql, null).getCount());
        if (db.rawQuery(sql, null).getCount()==0) {
            ckPws.setImageResource(R.mipmap.no);
            Toast.makeText(this, "密码错误！", Toast.LENGTH_SHORT).show();
        }
        else {
            ckPws.setImageResource(R.mipmap.yes);
            pws1.setFocusable(true);
            pws1.setFocusableInTouchMode(true);
            pws1.requestFocus();
            pws1.requestFocusFromTouch();
            InputMethodManager inputManager =
                    (InputMethodManager) pws1.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(pws1, 0);
        }
    }
    public void aCheck(View view) {
        init(view);
        EditText pws = (EditText) findViewById(R.id.pws);
        EditText eak = (EditText) findViewById(R.id.eak);
        EditText username = (EditText) findViewById(R.id.username);
        ImageView ckPws = (ImageView) findViewById(R.id.ckPws);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String sql = "select * from Info where username= '" + username.getText().toString() + "' and password='"
                +pws.getText().toString()+"'";
        System.out.println(db.rawQuery(sql, null).getCount());
        if (db.rawQuery(sql, null).getCount()==0) {
            ckPws.setImageResource(R.mipmap.no);
            Toast.makeText(this, "密码错误！", Toast.LENGTH_SHORT).show();
        }
        else {
            ckPws.setImageResource(R.mipmap.yes);
            eak.setFocusable(true);
            eak.setFocusableInTouchMode(true);
            eak.requestFocus();
            eak.requestFocusFromTouch();
            InputMethodManager inputManager =
                    (InputMethodManager) eak.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(eak, 0);
        }
    }
    public void ini(View view) {
        init(view);
        EditText username = (EditText) findViewById(R.id.username);
        ImageView ckUser = (ImageView) findViewById(R.id.ckUser);
        EditText pws = (EditText) findViewById(R.id.pws);
        EditText pws1 = (EditText) findViewById(R.id.pws1);
        EditText eak = (EditText) findViewById(R.id.eak);
        EditText esk = (EditText) findViewById(R.id.esk);
        ImageView ckPws = (ImageView) findViewById(R.id.ckPws);
        ImageView ckPws1 = (ImageView) findViewById(R.id.ckPws1);
        username.setFocusable(true);
        username.setFocusableInTouchMode(true);
        username.requestFocus();
        username.requestFocusFromTouch();
        InputMethodManager inputManager =
                (InputMethodManager) username.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(username, 0);
        username.setText("");
        pws.setText("");
        pws1.setText("");
        eak.setText("");
        esk.setText("");
        ckPws.setImageResource(0);
        ckPws1.setImageResource(0);
        ckUser.setImageResource(0);
        view.invalidate();

    }

    public void update(View view) {
        init(view);
        EditText username = (EditText) findViewById(R.id.username);
        EditText pws = (EditText) findViewById(R.id.pws);
        EditText pws1 = (EditText) findViewById(R.id.pws1);
        EditText eak = (EditText) findViewById(R.id.eak);
        EditText esk = (EditText) findViewById(R.id.esk);
        ImageView ckPws=findViewById(R.id.ckPws);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String sql = "select * from Info where username= '" + username.getText().toString() + "' and password='"
                +pws.getText().toString()+"'";
        System.out.println(db.rawQuery(sql, null).getCount());
        if (db.rawQuery(sql, null).getCount()==0) {
            ckPws.setImageResource(R.mipmap.no);
            Toast.makeText(this, "密码错误！", Toast.LENGTH_SHORT).show();
            pws.setFocusable(true);
            pws.setFocusableInTouchMode(true);
            pws.requestFocus();
            pws.requestFocusFromTouch();
            InputMethodManager inputManager =
                    (InputMethodManager) pws.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(pws, 0);

        }
        else {
            ckPws.setImageResource(R.mipmap.yes);
            view.invalidate();
            ContentValues values = new ContentValues();
            values.put("username", username.getText().toString());
            if(pws1.getText().toString().equals(""))
                values.put("password", pws.getText().toString());
            else
                values.put("password", pws1.getText().toString());
            if(eak.getText().toString().equals("")&& esk.getText().toString().equals("")){
                Cursor cursor=db.rawQuery(sql,null);
                values.put("ak",cursor.getString(cursor.getColumnIndex("ak")));
                values.put("sk",cursor.getString(cursor.getColumnIndex("sk")));
                System.out.println(cursor.getString(cursor.getColumnIndex("ak")));
            }
            else{
                values.put("ak", eak.getText().toString());
                values.put("sk", esk.getText().toString());
            }
            if(db.delete("Info","username=?",new String[] {username.getText().toString()})==1){
                if (db.insert("Info",null,values) == 1) {
                    Intent intent = new Intent(Update.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "修改失败请重新注册！", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Update.this,Sign.class);
                    startActivity(intent);
                }
            }else {
                Toast.makeText(this, "修改失败请重试！", Toast.LENGTH_SHORT).show();
            }
        }

    }
}