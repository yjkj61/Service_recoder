package com.yjkj.service_recoder.java.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.databinding.ActivityCfbgBinding;
import com.yjkj.service_recoder.java.base.BaseActivity;
import com.yjkj.service_recoder.java.bean.CfbgBean;
import com.yjkj.service_recoder.java.http.API;
import com.yjkj.service_recoder.java.http.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @description 查房报告
 * @author: jhw
 * @date: 2024/5/25
 */
public class CfbgActivity extends BaseActivity<ActivityCfbgBinding> {

    public CfbgBean data = null;

    private String question1 = "";
    private String answer1 = "";

    private String question2 = "";
    private String answer2 = "";

    private String question3 = "";
    private String answer3 = "";

    private String question4 = "";
    private String answer4 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        super.initView();

        viewBinding.tvTitle.setText("查房报告");

        setClick();
        getQuestionList();
    }

    public void setClick(){
        viewBinding.rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                answer1 = data.getData().getAnswerOneList().get(i);
            }
        });

        viewBinding.rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                answer2 = data.getData().getAnswerTwoList().get(i);
            }
        });

        viewBinding.rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                answer3 = data.getData().getAnswerThreeList().get(i);
            }
        });

        viewBinding.rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                answer4 = data.getData().getAnswerFourList().get(i);
            }
        });

        viewBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(answer1) || "".equals(answer2) || "".equals(answer3) || "".equals(answer4)){
                    showToast("请选择完问题再提交报告，谢谢！");
                    return;
                }
                addCfbg();
            }
        });

        viewBinding.igBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setQuestionData(CfbgBean bean){
        viewBinding.tvTitle1.setText(bean.getData().getAnswerOne());
        question1 = bean.getData().getAnswerOne();
        for (int i = 0; i < bean.getData().getAnswerOneList().size(); i++){
            RadioButton rb = new RadioButton(this);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            layoutParams.setMargins(10, 10, 10, 10);
            rb.setLayoutParams(layoutParams);
            rb.setText(bean.getData().getAnswerOneList().get(i));
            rb.setTextSize(16);
            rb.setButtonDrawable(null);
            rb.setGravity(Gravity.CENTER);
            rb.setPadding(10, 5, 10, 5);
            rb.setBackground(getResources().getDrawable(R.drawable.rb_question));
            rb.setId(i);
            viewBinding.rg1.addView(rb);
        }

        viewBinding.tvTitle2.setText(bean.getData().getAnswerTwo());
        question3 = bean.getData().getAnswerTwo();
        for (int i = 0; i < bean.getData().getAnswerTwoList().size(); i++){
            RadioButton rb = new RadioButton(this);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            layoutParams.setMargins(10, 10, 10, 10);
            rb.setLayoutParams(layoutParams);
            rb.setText(bean.getData().getAnswerOneList().get(i));
            rb.setTextSize(16);
            rb.setButtonDrawable(null);
            rb.setGravity(Gravity.CENTER);
            rb.setPadding(10, 5, 10, 5);
            rb.setBackground(getResources().getDrawable(R.drawable.rb_question));
            rb.setId(i);
            viewBinding.rg2.addView(rb);
        }

        viewBinding.tvTitle3.setText(bean.getData().getAnswerThree());
        question3 = bean.getData().getAnswerThree();
        for (int i = 0; i < bean.getData().getAnswerThreeList().size(); i++){
            RadioButton rb = new RadioButton(this);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            layoutParams.setMargins(10, 10, 10, 10);
            rb.setLayoutParams(layoutParams);
            rb.setText(bean.getData().getAnswerOneList().get(i));
            rb.setTextSize(16);
            rb.setButtonDrawable(null);
            rb.setGravity(Gravity.CENTER);
            rb.setPadding(10, 5, 10, 5);
            rb.setBackground(getResources().getDrawable(R.drawable.rb_question));
            rb.setId(i);
            viewBinding.rg3.addView(rb);
        }

        viewBinding.tvTitle4.setText(bean.getData().getAnswerFour());
        question4 = bean.getData().getAnswerFour();
        for (int i = 0; i < bean.getData().getAnswerFourList().size(); i++){
            RadioButton rb = new RadioButton(this);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            layoutParams.setMargins(10, 10, 10, 10);
            rb.setLayoutParams(layoutParams);
            rb.setText(bean.getData().getAnswerOneList().get(i));
            rb.setTextSize(16);
            rb.setButtonDrawable(null);
            rb.setGravity(Gravity.CENTER);
            rb.setPadding(10, 5, 10, 5);
            rb.setBackground(getResources().getDrawable(R.drawable.rb_question));
            rb.setId(i);
            viewBinding.rg4.addView(rb);
        }
    }

    public int pageNum = 1;

    public int pageSize = 100;

    //物业工单
    private void getQuestionList(){
        OkHttpUtil.getInstance().doGet(API.CFBG_GET_QUESTIONS + "pageNum=" + pageNum + "&pageSize=" + pageSize, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                CfbgBean bean = new Gson().fromJson(response.body().string(), CfbgBean.class);
                if (bean.getCode() == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            data = bean;
                            setQuestionData(bean);
                        }
                    });
                }
            }
        });
    }

    //新增查房报告
    private void addCfbg(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("qOne", question1);
            jsonObject.put("answerOne", answer1);
            jsonObject.put("qTwo", question2);
            jsonObject.put("answerTwo", answer2);
            jsonObject.put("qThree", question3);
            jsonObject.put("answerThree", answer3);
            jsonObject.put("qFour", question4);
            jsonObject.put("answerFour", answer4);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        OkHttpUtil.getInstance().doPost(API.CFBG_ADD_QUESTIONS, jsonObject.toString(), new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                CfbgBean bean = new Gson().fromJson(response.body().string(), CfbgBean.class);
                if (bean.getCode() == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast("提交成功");
                            finish();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }

}
