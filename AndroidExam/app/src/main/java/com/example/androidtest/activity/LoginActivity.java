package com.example.androidtest.activity;

import androidx.appcompat.app.ActionBar;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtest.R;
import com.example.androidtest.db.dbHelper;
import com.example.androidtest.tool.JellyInterpolator;

import java.util.Timer;
import java.util.TimerTask;

import static android.os.SystemClock.sleep;

public class LoginActivity extends BaseActivity {
    private TextView mBtnLogin;
    private View progress;
    private View mInputLayout;
    private float mWidth, mHeight;
    private LinearLayout mName, mPsw;
    private dbHelper dbhelper;
    String ak="";
    String sk="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper=new dbHelper(this,"Info.db",null,1);
        getSupportActionBar().setTitle("登录");
        initView();
        dbhelper.getWritableDatabase();

    }
    @Override
    protected void onStart(){
        super.onStart();
        recovery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mBtnLogin = (TextView) findViewById(R.id.main_btn_login);
        mInputLayout = findViewById(R.id.input_layout);
        progress = findViewById(R.id.layout_progress);
        mName = (LinearLayout) findViewById(R.id.input_layout_name);
        mPsw = (LinearLayout) findViewById(R.id.input_layout_psw);
    }

    public void login(View view) {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        EditText username=findViewById(R.id.useN);
        EditText password=findViewById(R.id.userP);
        String sql="select * from Info where username='"
                +username.getText().toString()+"' and password='"
                +password.getText().toString()+"';";
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            TextView s=findViewById(R.id.main_btn_sign);
            TextView l=findViewById(R.id.main_btn_login);
            TextView u=findViewById(R.id.main_btn_update);
            s.setVisibility(View.INVISIBLE);
            l.setVisibility(View.INVISIBLE);
            u.setVisibility(View.INVISIBLE);
            mInputLayout.setVisibility(view.INVISIBLE);
            // 计算出控件的高与宽
            mWidth = mBtnLogin.getMeasuredWidth();
            mHeight = mBtnLogin.getMeasuredHeight();
            // 隐藏输入框
            mName.setVisibility(View.INVISIBLE);
            mPsw.setVisibility(View.INVISIBLE);
            InputMethodManager inputManager =
                    (InputMethodManager)password.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            ak=cursor.getString(cursor.getColumnIndex("ak"));
            sk=cursor.getString(cursor.getColumnIndex("sk"));
            db.close();
            dbhelper.close();
            inputAnimator(view,mWidth,mHeight);
        }
        else {
            Toast.makeText(this,"账户或密码错误请重试！",Toast.LENGTH_SHORT).show();
        }
    }
    private void inputAnimator(final View view, float w, float h) {
        AnimatorSet set = new AnimatorSet();
        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(3000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        progress.setVisibility(View.VISIBLE);
        progressAnimator(progress);
        mInputLayout.setVisibility(View.INVISIBLE);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束后，先显示加载的动画，然后再隐藏输入框
                 */
                Intent intent=new Intent(LoginActivity.this,Home.class);
                intent.putExtra("ak",ak);
                intent.putExtra("sk",sk);
                startActivity(intent);

            }
            @Override
            public void onAnimationCancel(Animator animation) {
                recovery();
            }
        });
    }
    private void recovery() {
        TextView s=findViewById(R.id.main_btn_sign);
        TextView l=findViewById(R.id.main_btn_login);
        TextView u=findViewById(R.id.main_btn_update);
        EditText name=findViewById(R.id.useN);
        EditText pass=findViewById(R.id.userP);
        name.setText("");
        pass.setText("");
        s.setVisibility(View.VISIBLE);
        l.setVisibility(View.VISIBLE);
        u.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        mInputLayout.setVisibility(View.VISIBLE);
        mName.setVisibility(View.VISIBLE);
        mPsw.setVisibility(View.VISIBLE);

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mInputLayout.getLayoutParams();
        params.leftMargin = 0;
        params.rightMargin = 0;
        mInputLayout.setLayoutParams(params);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 0.5f,1f );
        animator2.setDuration(300);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.start();
    }
    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(3000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();

    }

    public void sign(View view) {
        TextView s=findViewById(R.id.main_btn_sign);
        TextView l=findViewById(R.id.main_btn_login);
        TextView u=findViewById(R.id.main_btn_update);
        mInputLayout.setVisibility(View.INVISIBLE);
        s.setVisibility(View.INVISIBLE);
        l.setVisibility(View.INVISIBLE);
        u.setVisibility(View.INVISIBLE);
        Intent intent=new Intent(LoginActivity.this,Sign.class);
        startActivity(intent);
    }

    public void update(View view) {
        TextView s=findViewById(R.id.main_btn_sign);
        TextView l=findViewById(R.id.main_btn_login);
        TextView u=findViewById(R.id.main_btn_update);
        mInputLayout.setVisibility(View.INVISIBLE);
        s.setVisibility(View.INVISIBLE);
        l.setVisibility(View.INVISIBLE);
        u.setVisibility(View.INVISIBLE);
        Intent intent=new Intent(LoginActivity.this,Update.class);
        startActivity(intent);
    }


}