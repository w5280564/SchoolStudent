package com.waylen.schoolstudent.Myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chivox.AIEngine;
import com.chivox.AIRecorder;
import com.chivox.AiUtil;
import com.chivox.chivoxdemo.ChivoxOptions;
import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.Json;
import com.waylen.schoolstudent.StaticData.StaticData;
import com.waylen.schoolstudent.StaticData.UrlVO;
import com.waylen.schoolstudent.StaticData.myActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Admin on 2016/4/19.
 */
public class Work_Detail extends myActivity {
    private String workStuId;
    private Button voice_Btn;
    private TextView voice_Txt;
    private List<Map<String, Object>> detailArr;
    private String TAG = "Work_Detail";
    private TextView content_Txt;
    private String workQuestionType;
    private String course;
    private EditText work_submit_Edit;
    private String sysScore;
    private String wavetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_detail);


        View title_Include = findViewById(R.id.title_Include);
        TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
        Button base_Back_Btn = (Button) title_Include.findViewById(R.id.base_Back_Btn);
        base_Back_Btn.setBackgroundResource(R.drawable.back_icon);
        Button base_Right_Btn = (Button) title_Include.findViewById(R.id.base_Right_Btn);
        base_Right_Btn.setText("提交");
        StaticData.ViewScale(title_Include, 0, 88);
        StaticData.ViewScale(base_Back_Btn, 23, 44);
        StaticData.ViewScale(base_Right_Btn, 100, 88);

        ImageView title_img = (ImageView) findViewById(R.id.title_img);
        ImageView content_img = (ImageView) findViewById(R.id.content_img);
        work_submit_Edit = (EditText) findViewById(R.id.work_submit_Edit);
        RelativeLayout voice_Rel = (RelativeLayout) findViewById(R.id.voice_Rel);
        ImageView voice_img = (ImageView) findViewById(R.id.voice_img);
        voice_Txt = (TextView) findViewById(R.id.voice_Txt);
        voice_Btn = (Button) findViewById(R.id.voice_Btn);
        TextView title_Txt = (TextView) findViewById(R.id.title_Txt);
        content_Txt = (TextView) findViewById(R.id.content_Txt);

        StaticData.ViewScale(title_img, 60, 60);
        StaticData.ViewScale(content_img, 60, 60);
        StaticData.ViewScale(work_submit_Edit, 600, 677);
        StaticData.ViewScale(voice_img, 60, 60);
        StaticData.ViewScale(voice_Txt, 284, 65);
        StaticData.ViewScale(voice_Btn, 151, 204);
        int pad = (int) (Float.parseFloat(UrlVO.getShareData("scale", this)) * 116);
        voice_Rel.setPadding(0, 0, 0, pad);

        Intent i = getIntent();
//        String type = i.getStringExtra("type");
        workStuId = i.getStringExtra("workStuId");//学生id
        course = i.getStringExtra("courseType");//作业类型
        detailArr = (List<Map<String, Object>>) i.getSerializableExtra("detailArr");
        workQuestionType = detailArr.get(0).get("workQuestionType").toString();//语音文本type

        if (detailArr != null) {//2作业朗读 3背诵 4预习 5语音写作 是语音的,其他文字
            if (workQuestionType.equals("1")) {
                title_Txt.setText("作业：书面作业");
                work_submit_Edit.setVisibility(View.VISIBLE);
                voice_Rel.setVisibility(View.GONE);
            } else if (workQuestionType.equals("2")) {
                title_Txt.setText("作业：作业朗读");
                work_submit_Edit.setVisibility(View.GONE);
                voice_Rel.setVisibility(View.VISIBLE);
            } else if (workQuestionType.equals("3")) {
                title_Txt.setText("作业：背诵");
                work_submit_Edit.setVisibility(View.GONE);
                voice_Rel.setVisibility(View.VISIBLE);
            } else if (workQuestionType.equals("4")) {
                title_Txt.setText("作业：预习");
                work_submit_Edit.setVisibility(View.GONE);
                voice_Rel.setVisibility(View.VISIBLE);
            } else if (workQuestionType.equals("5")) {
                title_Txt.setText("作业：语音写作");
                work_submit_Edit.setVisibility(View.GONE);
                voice_Rel.setVisibility(View.VISIBLE);
            } else if (workQuestionType.equals("6")) {
                title_Txt.setText("作业：文字写作");
                work_submit_Edit.setVisibility(View.VISIBLE);
                voice_Rel.setVisibility(View.GONE);
            } else if (workQuestionType.equals("7")) {
                title_Txt.setText("作业：口算");
                work_submit_Edit.setVisibility(View.VISIBLE);
                voice_Rel.setVisibility(View.GONE);
            } else if (workQuestionType.equals("8")) {
                title_Txt.setText("作业：观后感");
                work_submit_Edit.setVisibility(View.VISIBLE);
                voice_Rel.setVisibility(View.GONE);
            }
            content_Txt.setText("内容：" + detailArr.get(0).get("workQuestion").toString());
        }
        base_Back_Btn.setOnClickListener(new base_Back_Btn());
        base_Right_Btn.setOnClickListener(new base_Right_Btn());
        voice_Btn.setOnClickListener(new voice_Btn());
        voice_Txt.setOnClickListener(new voice_Txt());
        initEngine();
    }

    public class base_Back_Btn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }

    public class base_Right_Btn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (workQuestionType.equals("2") || workQuestionType.equals("3") || workQuestionType.equals("4") || workQuestionType.equals("5")) {
//                if (media == null) {
//                    Toast.makeText(Work_Detail.this, "答案不能是空", Toast.LENGTH_SHORT);
//                    return;
//                }
                UpWorkData("http://swfx.sinthonic.com/swfx/doingHomework-workAnswer-add.action");
            } else {
                UpWorkData("http://swfx.sinthonic.com/swfx/doingHomework-workAnswer-add.action");
            }

        }
    }


    private boolean isrecording = false;
//    private File MP3file;
//    private MP3Recorder mRecorder;
//    private MediaPlayer media;

    public class voice_Btn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (isrecording == false) {//false开始录音
                voice_Btn.setBackgroundResource(R.drawable.voice_btn_select);
                StaticData.ViewScale(voice_Btn, 187, 203);
                if (engine == 0 || recorder == null) {
                    return;
                }
                start_native();//启动评测
                isrecording = true;
            } else {
                voice_Btn.setBackgroundResource(R.drawable.voice_btn_icon);
                StaticData.ViewScale(voice_Btn, 151, 204);

                if (recorder != null) {
                    recorder.stop();
                }
                int rv = AIEngine.aiengine_stop(engine);

                isrecording = false;
            }
        }
    }

    public class voice_Txt implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (null != recorder) {
                recorder.playback();
            }
        }
    }

    private String wavPath;
    Json JsonGet = new Json();

    //提交答案
    public void UpWorkData(String baseUrl) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("workStudentId", workStuId);
            obj.put("workQuestionId", detailArr.get(0).get("id").toString());
            if (workQuestionType.equals("2") || workQuestionType.equals("3") || workQuestionType.equals("4") || workQuestionType.equals("5")) {
                obj.put("sysScore", sysScore);
                obj.put("maxScore", "100");
                obj.put("answerType", "2");
                obj.put("answer", wavPath);
            } else {
                obj.put("answerType", "1");
                obj.put("answer", work_submit_Edit.getText().toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams(baseUrl);
        params.addBodyParameter("param", obj.toString());
        params.setMultipart(true);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String resultString) {
                if (JsonGet.getReturnValue(resultString, "status").equals("1")) {//1是成功
                    finish();
                }
            }
            @Override
            public void onError(Throwable throwable, boolean b) {
            }
            @Override
            public void onCancelled(CancelledException e) {
            }
            @Override
            public void onFinished() {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (engine != 0) {
            AIEngine.aiengine_delete(engine);
            engine = 0;
        }

        if (recorder != null) {
            recorder.stop();
            recorder = null;
        }

    }

    private long engine = 0;
    private ExecutorService workerThread = Executors.newFixedThreadPool(1);

    public void runOnWorkerThread(Runnable runnable) {
        workerThread.execute(runnable);
    }

    private AIRecorder recorder = null;

    private void initEngine() {//初始化
        runOnWorkerThread(new Runnable() {
            public void run() {
                /* create aiengine instance */
                if (engine == 0) {
                    byte buf[] = new byte[1024];
                    AIEngine.aiengine_get_device_id(buf, getApplicationContext());
                    String deviceId = new String(buf).trim();
                    InputStream is = null;
                    String provisionPath = "";
                    try {
                        is = getAssets().open("aiengine.provision");
                        File provisionFile = new File(AiUtil.externalFilesDir(Work_Detail.this), "aiengine.provision");
                        AiUtil.writeToFile(provisionFile, is);
                        is.close();
                        provisionPath = provisionFile.getAbsolutePath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String cfg = String.format("{\"appKey\": \"%s\", \"secretKey\": \"%s\", \"cloud\": {\"server\": \"%s\"}, \"provision\": \"%s\"}",
                            ChivoxOptions.APP_KEY, ChivoxOptions.SECRET_KEY, ChivoxOptions.SERVER, provisionPath);
                    engine = AIEngine.aiengine_new(cfg, Work_Detail.this);
                }
                /* create airecorder instance  单例 */
                if (recorder == null) {
                    recorder = AIRecorder.getInstance();
                }
            }
        });
    }

    public void start_native() {
        if (engine == 0 || recorder == null) {
            return;
        }
        byte[] id = new byte[64];
        String userId = UrlVO.getShareData("userId", Work_Detail.this);
        String coreType;
        String refText;
//        if (workQuestionType.equals("2")||workQuestionType.equals("3")||workQuestionType.equals("4")||workQuestionType.equals("5")){
        if (course.equals("4")) {
//            coreType = "en.pred.exam";//英文段落朗读评测
//            coreType = "en.word.score";//英文单词评测
            coreType = "en.sent.score";//英文句子评测
            refText = detailArr.get(0).get("workQuestion").toString();
        } else {
            coreType = "cn.sent.score";//中文评测
            refText = AiUtil.getPinYin(detailArr.get(0).get("workQuestion").toString());//中文需要带音调
        }
        String params = String.format("{\"coreProvideType\": \"cloud\", \"app\": {\"userId\": \"%s\"}, \"audio\": {\"audioType\": \"wav\", \"channel\": 1, \"sampleBytes\": 2, \"sampleRate\": 16000}, " +
                "\"request\": {\"coreType\": \"%s\", \"rank\": 100, \"refText\":\"%s\"}}", userId, coreType, refText);
        int rv = AIEngine.aiengine_start(engine, params, id, callback, Work_Detail.this);
        wavPath = AiUtil.getFilesDir(Work_Detail.this).getPath() + "/record/" + new String(id).trim() + ".wav";
        recorder.start(wavPath, new AIRecorder.Callback() {
            public void run(byte[] data, int size) {
                AIEngine.aiengine_feed(engine, data, size);
            }
        });
    }

    private AIEngine.aiengine_callback callback = new AIEngine.aiengine_callback() {
        @Override
        public int run(byte[] id, int type, byte[] data, int size) {
            if (type == AIEngine.AIENGINE_MESSAGE_TYPE_JSON) {
                final String mresult = new String(data, 0, size).trim();
                runOnUiThread(new Runnable() {
                    public void run() {
                        JSONObject json;
                        try {
                            json = new JSONObject(mresult);
                            if (json.has("result")) {
                                JSONObject jsonRet = json.getJSONObject("result");
                                if (jsonRet.has("overall")) {
                                    if (jsonRet.getString("overall").equals("0")) {
                                        sysScore = "0";
                                    } else {
                                        sysScore = jsonRet.getString("overall");
                                    }
                                    wavetime = jsonRet.getString("wavetime");
                                    DecimalFormat df = new DecimalFormat("0.00");
                                    String ms = df.format(Double.valueOf(wavetime) / 1000);
                                    voice_Txt.setText(ms + "s");
                                }
                            } else {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
            return 0;
        }
    };


}
