package com.unifam.heartpatrol.estore;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.unifam.heartpatrol.R;
import com.unifam.heartpatrol.estore.fragment.Frag_Estore_Package;
import com.unifam.heartpatrol.estore.fragment.Frag_Estore_Payment;
import com.unifam.heartpatrol.estore.fragment.Frag_Estore_Payment_Next;
import com.unifam.heartpatrol.estore.fragment.Frag_Estore_Review;

import ui.util.FragmentUtil;
import ui.view.EstorePaymentStepView;

/**
 * Created by Unifam on 9/21/2016.
 */
public class EstoreActivity extends AppCompatActivity {

    ImageView imgBack;
    TextView txtLabel;

    private EstorePaymentStepView paymentStepView;
    private int currentStep = 0;
    private int[] titles = {
            R.string.estore_package,
            R.string.estore_review,
            R.string.estore_payment,
            R.string.estore_confirm
    };
    private int[] paymentSteps = {0, 1, 2, 2, 3};
    private Class[] fragments = new Class[]{
            Frag_Estore_Package.class,
            Frag_Estore_Review.class,
            Frag_Estore_Payment.class,
            Frag_Estore_Payment_Next.class,
            Frag_Estore_Package.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estore);
        imgBack = (ImageView)findViewById(R.id.arrow_back);
        txtLabel = (TextView)findViewById(R.id.textLabel);
        paymentStepView = (EstorePaymentStepView) findViewById(R.id.payment_step_view);
        txtLabel.setText(getResources().getText(R.string.estore));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nextStep();
    }

    private void setCurrentFragment(int currentStep) {
        try {
            Fragment currentFragment = (Fragment) fragments[currentStep].newInstance();
            //title.setText(titles[currentStep]);
            if (currentStep > 0) {
                FragmentUtil.changeFragment(this, R.id.fragment_container, currentFragment);
            } else {
                FragmentUtil.changeFragment(this, R.id.fragment_container, currentFragment, false);
            }
            paymentStepView.setCurrentStep(paymentSteps[currentStep]);
            if (paymentSteps[currentStep] == 3) {
                imgBack.setVisibility(View.GONE);
                //closeButton.setVisibility(View.VISIBLE);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void nextStep() {
        nextStep(null);
    }

    public void nextStep(View view) {
        setCurrentFragment(currentStep);
        currentStep++;
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (paymentSteps[currentStep] == 1) {
            super.onBackPressed();
        } else if (paymentSteps[currentStep] < 4) {
            //title.setText(R.string.estore_product);
            currentStep -= 2;
            paymentStepView.setCurrentStep(paymentSteps[currentStep]);
            currentStep++;
            if (FragmentUtil.backPressed(this)) {
                super.onBackPressed();
            }

        } else {
            //close(null);
        }
    }

    public static void changeToolbarname(){
        //title.setText(R.string.estore_product);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
