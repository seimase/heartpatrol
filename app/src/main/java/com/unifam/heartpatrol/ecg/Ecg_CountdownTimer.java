package com.unifam.heartpatrol.ecg;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

import com.unifam.heartpatrol.R;

import org.w3c.dom.Text;

/**
 * Created by setia.n on 11/4/2016.
 */

public class Ecg_CountdownTimer extends AppCompatActivity{
    TextView txtTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ecg_countdowntimer);

        txtTimer = (TextView)findViewById(R.id.text_timer);

        new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtTimer.setText(Long.toString(millisUntilFinished / 1000));
            }

            public void onFinish() {
                finish();
            }
        }.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_CAMERA) {
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_CALL) {
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }else if (keyCode == KeyEvent.KEYCODE_HOME){
            return true;
        }

        return false;
    }
}
