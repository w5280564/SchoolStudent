package com.chivox.chivoxdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chivox.AIEngine;
import com.chivox.AIRecorder;
import com.chivox.AiUtil;
import com.waylen.schoolstudent.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends Activity {
    private String TAG = "ChivoxMainActivity";

    //dependence
    private long engine = 0;
    private AIRecorder recorder = null;

    private ExecutorService workerThread = Executors.newFixedThreadPool(1);

    //view
    private EditText refText;                //输入文本
    private TextView result;                 //结果显示

    //data
    private Context context;
    private String mCoreType = "en.sent.score";
    private String mRes = "";
    private String mresult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        bindEvent();
        initEngine();
    }

    private void bindEvent() {
        final Button recStartBtn = (Button) findViewById(R.id.rec_start);
        final Button recStopBtn = (Button) findViewById(R.id.rec_stop);
        final Button repStartBtn = (Button) findViewById(R.id.rep_start);
        refText = (EditText) findViewById(R.id.editText);
        result = (TextView) findViewById(R.id.text_item1);
        result.setMovementMethod(new ScrollingMovementMethod());
        recStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (engine == 0 || recorder == null) {
                    return;
                }
                recStartBtn.setEnabled(false);
                byte[] id = new byte[64];

                String params = String.format("{\"coreProvideType\":\"cloud\",\"app\": {\"userId\": \"ChivoxDemo\"}, \"audio\": {\"audioType\": \"wav\", \"channel\": 1, \"sampleBytes\": 2, \"sampleRate\": 16000}, \"request\": {\"coreType\": \"%s\",\"refText\": \"%s\"}}",
                        mCoreType, refText.getText());
                Log.d(TAG, "params: " + params);
                int rv = AIEngine.aiengine_start(engine, params, id, callback, context);
                Log.d(TAG, "engine start: " + rv);
                String wavPath = AiUtil.getFilesDir(context).getPath()
                        + "/record/" + new String(id).trim() + ".wav";
                recorder.start(wavPath, new AIRecorder.Callback() {
                    public void run(byte[] data, int size) {
                        AIEngine.aiengine_feed(engine, data, size);
                    }
                });
            }
        });
        recStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recorder.stop();
                int rv = AIEngine.aiengine_stop(engine);
                Log.d(TAG, "engine stop: " + rv);
            }
        });
        repStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != recorder) {
                    recorder.playback();
                }
            }
        });

    }

    private void initEngine() {
        runOnWorkerThread(new Runnable() {
            public void run() {
                /* create aiengine instance */
                if (engine == 0) {
                    byte buf[] = new byte[1024];
                    AIEngine.aiengine_get_device_id(buf, getApplicationContext());

                    String deviceId = new String(buf).trim();
                    Log.d(TAG, "deviceId: " + deviceId);

                    InputStream is = null;
                    String provisionPath = "";

                    try {
                        is = getAssets().open("aiengine.provision");
                        File provisionFile = new File(AiUtil.externalFilesDir(context), "aiengine.provision");
                        AiUtil.writeToFile(provisionFile, is);
                        is.close();
                        provisionPath = provisionFile.getAbsolutePath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Log.d(TAG, "provisionPath:" + provisionPath);

                    String cfg = String.format("{\"appKey\": \"%s\", \"secretKey\": \"%s\", \"cloud\": {\"server\": \"%s\"}, \"provision\": \"%s\"}",
                            ChivoxOptions.APP_KEY, ChivoxOptions.SECRET_KEY, ChivoxOptions.SERVER, provisionPath);

                    engine = AIEngine.aiengine_new(cfg, context);
                    Log.d(TAG, "aiengine: " + engine);
                }
				/* create airecorder instance  */
                if (recorder == null) {
                    recorder = AIRecorder.getInstance();
                    Log.d(TAG, "airecorder: " + recorder);
                }
            }
        });


    }


    private AIEngine.aiengine_callback callback = new AIEngine.aiengine_callback() {

        @Override
        public int run(byte[] id, int type, byte[] data, int size) {
            if (type == AIEngine.AIENGINE_MESSAGE_TYPE_JSON) {
                mresult = new String(data, 0, size).trim();
                runOnUiThread(new Runnable() {
                    public void run() {
                        JSONObject json;
                        try {
                            json = new JSONObject(mresult);
                            result.setText(json.toString(4));
//                            if (json.has("result")) {
//                                JSONObject jsonRet = json.getJSONObject("result");
//                                if (jsonRet.has("overall")) {
//                                    result.setText("总分: " + jsonRet.getInt("overall") + "\n");
//                                }
//
//                            } else {
//                                result.setText(json.toString(4));
//                            }
                            //text1.setText(json.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ((Button) findViewById(R.id.rec_start)).setEnabled(true);
                    }
                });
            }

            return 0;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (engine != 0) {
            AIEngine.aiengine_delete(engine);
            engine = 0;
            Log.d(TAG, "engine deleted: " + engine);
        }

        if (recorder != null) {
            recorder.stop();
            recorder = null;
        }

    }

    public void runOnWorkerThread(Runnable runnable) {
        workerThread.execute(runnable);
    }

}
