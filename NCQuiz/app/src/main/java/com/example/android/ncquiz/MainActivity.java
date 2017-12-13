package com.example.android.ncquiz;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final int QUESTION_NUMBER = 6;
    private int correctAnswer = 0;
    private int level = 0;
    boolean check1 = false;
    boolean check2 = false;
    boolean check3 = false;
    boolean check4 = false;
    boolean check5 = false;
    boolean check6 = false;
    boolean checkAnswer = false;
    private RadioButton checkQuestion1;
    private RadioButton checkQuestion11;
    private RadioButton checkQuestion13;
    private CheckBox checkQuestion21;
    private CheckBox checkQuestion22;
    private CheckBox checkQuestion23;
    private CheckBox checkQuestion24;
    private RadioButton checkQuestion3;
    private RadioButton checkQuestion32;
    private RadioButton checkQuestion33;
    private CheckBox checkQuestion41;
    private CheckBox checkQuestion42;
    private CheckBox checkQuestion43;
    private CheckBox checkQuestion44;
    private RadioButton checkQuestion5;
    private RadioButton checkQuestion51;
    private RadioButton checkQuestion52;
    private RadioButton checkQuestion53;
    private EditText checkQuestion6;
    private TextView resultView;
    private TextView detailView;
    private ImageView stickerView;
    private Button submit1;
    private Button submit2;
    private Button submit3;
    private Button submit4;
    private Button submit5;
    private Button submit6;
    private TextView question1;
    private TextView question2;
    private TextView question3;
    private TextView question4;
    private TextView question5;
    private TextView question6;
    private Button moreDetails;
    private Button close;
    List<Button> rightAnswer = new ArrayList<>();
    List<Button> submitButton = new ArrayList<>();
    List<Button> allButton = new ArrayList<>();
    Map<ImageView, Integer> imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini();
        for (Map.Entry<ImageView, Integer> entry : imageLoader.entrySet()) {
            GlideApp.with(this).load(entry.getValue()).into(entry.getKey());
        }
    }


    public void detail(View view) {
        TextView detail = (TextView) findViewById(R.id.rule);
        if (detail.getText().toString().isEmpty()) {
            detail.setText(getString(R.string.readme_text));
            close.setText(getString(R.string.close));
        } else {
            detail.setText("");
            close.setText(getString(R.string.readme));
        }
    }


    private void ini() {
        checkQuestion1 = findViewById(R.id.answer1);
        checkQuestion11 = findViewById(R.id.q11);
        checkQuestion13 = findViewById(R.id.q13);
        checkQuestion21 = findViewById(R.id.q21);
        checkQuestion22 = findViewById(R.id.q22);
        checkQuestion23 = findViewById(R.id.q23);
        checkQuestion24 = findViewById(R.id.q24);
        checkQuestion3 = findViewById(R.id.answer3);
        checkQuestion32 = findViewById(R.id.q32);
        checkQuestion33 = findViewById(R.id.q33);
        checkQuestion41 = findViewById(R.id.q41);
        checkQuestion42 = findViewById(R.id.q42);
        checkQuestion43 = findViewById(R.id.q43);
        checkQuestion44 = findViewById(R.id.q44);
        checkQuestion51 = findViewById(R.id.q51);
        checkQuestion52 = findViewById(R.id.q52);
        checkQuestion53 = findViewById(R.id.q53);
        checkQuestion5 = findViewById(R.id.answer5);
        checkQuestion6 = findViewById(R.id.answer6);
        resultView = findViewById(R.id.result);
        detailView = findViewById(R.id.detail);
        stickerView = findViewById(R.id.nerd);
        rightAnswer.addAll(Arrays.asList(checkQuestion1, checkQuestion22, checkQuestion23,
                checkQuestion24, checkQuestion3, checkQuestion41, checkQuestion42,
                checkQuestion43, checkQuestion44, checkQuestion5));
        submit1 = findViewById(R.id.submit1);
        submit2 = findViewById(R.id.submit2);
        submit3 = findViewById(R.id.submit3);
        submit4 = findViewById(R.id.submit4);
        submit5 = findViewById(R.id.submit5);
        submit6 = findViewById(R.id.submit6);
        submitButton.addAll(Arrays.asList(submit1, submit2, submit3, submit4, submit5, submit6));
        question1 = findViewById(R.id.q1_text);
        question2 = findViewById(R.id.q2_text);
        question3 = findViewById(R.id.q3_text);
        question4 = findViewById(R.id.q4_text);
        question5 = findViewById(R.id.q5_text);
        question6 = findViewById(R.id.q6_text);
        moreDetails = findViewById(R.id.more_details);
        close = findViewById(R.id.readme);
        allButton.addAll(Arrays.asList(checkQuestion1, checkQuestion11, checkQuestion13,
                checkQuestion21, checkQuestion22, checkQuestion23, checkQuestion24,
                checkQuestion3, checkQuestion32, checkQuestion33,
                checkQuestion41, checkQuestion42, checkQuestion43, checkQuestion44,
                checkQuestion5, checkQuestion51, checkQuestion52, checkQuestion53));
        imageLoader = new HashMap<>();
        imageLoader.put((ImageView) findViewById(R.id.marvin), R.drawable.marvin);
        imageLoader.put((ImageView) findViewById(R.id.c3po), R.drawable.c3po);
        imageLoader.put((ImageView) findViewById(R.id.r2d2), R.drawable.r2d2);
        imageLoader.put((ImageView) findViewById(R.id.bb8), R.drawable.bb8);
        imageLoader.put((ImageView) findViewById(R.id.fb), R.drawable.fb);
        imageLoader.put((ImageView) findViewById(R.id.insta), R.drawable.instagram);
        imageLoader.put((ImageView) findViewById(R.id.mail), R.drawable.mail);
        imageLoader.put((ImageView) findViewById(R.id.git), R.drawable.github);
    }

    public void submit1(View view) {
        boolean q1 = checkQuestion1.isChecked();
        boolean q11 = checkQuestion11.isChecked();
        boolean q13 = checkQuestion13.isChecked();
        if ((q1 || q11 || q13) && !check1) {
            checkQuestion11.setClickable(false);
            checkQuestion1.setClickable(false);
            checkQuestion13.setClickable(false);
            if (q1) {
                correctAnswer++;
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        getString(R.string.ircorrect) + getString(R.string.answer1),
                        Toast.LENGTH_LONG).show();
            }
            check1 = true;
        }
    }

    public void submit2(View view) {
        boolean q21 = checkQuestion21.isChecked();
        boolean q22 = checkQuestion22.isChecked();
        boolean q23 = checkQuestion23.isChecked();
        boolean q24 = checkQuestion24.isChecked();
        boolean q2 = !q21 && q22 && q23 && q24;
        if ((q21 || q22 || q23 || q24) && !check2) {
            checkQuestion21.setClickable(false);
            checkQuestion22.setClickable(false);
            checkQuestion23.setClickable(false);
            checkQuestion24.setClickable(false);
            if (q2) {
                correctAnswer++;
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        getString(R.string.ircorrect) + getString(R.string.answer2),
                        Toast.LENGTH_LONG).show();
            }
            check2 = true;
        }
    }

    public void submit3(View view) {
        boolean q3 = checkQuestion3.isChecked();
        boolean q32 = checkQuestion32.isChecked();
        boolean q33 = checkQuestion33.isChecked();
        if ((q3 || q32 || q33) && !check3) {
            checkQuestion3.setClickable(false);
            checkQuestion32.setClickable(false);
            checkQuestion33.setClickable(false);
            if (q3) {
                correctAnswer++;
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        getString(R.string.ircorrect) + getString(R.string.answer3),
                        Toast.LENGTH_LONG).show();
            }
            check3 = true;
        }
    }

    public void submit4(View view) {
        boolean q41 = ((CheckBox) findViewById(R.id.q41)).isChecked();
        boolean q42 = ((CheckBox) findViewById(R.id.q42)).isChecked();
        boolean q43 = ((CheckBox) findViewById(R.id.q43)).isChecked();
        boolean q44 = ((CheckBox) findViewById(R.id.q44)).isChecked();
        boolean q4 = q41 && q42 && q43 && q44;
        if ((q41 || q42 || q43 || q44) && !check4) {
            checkQuestion41.setClickable(false);
            checkQuestion42.setClickable(false);
            checkQuestion43.setClickable(false);
            checkQuestion44.setClickable(false);
            if (q4) {
                correctAnswer++;
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        getString(R.string.ircorrect) + getString(R.string.answer4),
                        Toast.LENGTH_LONG).show();
            }
            check4 = true;
        }
    }

    public void submit5(View view) {
        boolean q5 = checkQuestion5.isChecked();
        boolean q51 = checkQuestion51.isChecked();
        boolean q52 = checkQuestion52.isChecked();
        boolean q53 = checkQuestion53.isChecked();
        if ((q51 || q52 || q53 || q5) && !check5) {
            checkQuestion51.setClickable(false);
            checkQuestion52.setClickable(false);
            checkQuestion53.setClickable(false);
            checkQuestion5.setClickable(false);
            if (q5) {
                correctAnswer++;
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        getString(R.string.ircorrect) + getString(R.string.answer5),
                        Toast.LENGTH_LONG).show();
            }
            check5 = true;
        }
    }

    public void submit6(View view) {
        Editable name = checkQuestion6.getText();
        String nameLowerCase = name.toString().toLowerCase();
        boolean q6 = nameLowerCase.contains("wtf") || nameLowerCase.contains("whatthefuck") || nameLowerCase.contains("what the fuck");
        if (!name.toString().isEmpty() && !check6) {
            checkQuestion6.setFocusable(false);
            if (q6) {
                correctAnswer++;
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        getString(R.string.ircorrect) + getString(R.string.answer6),
                        Toast.LENGTH_LONG).show();
            }
            check6 = true;
        }

    }

    public void getResult(View view) {
        String result = "";
        String image = "";
        int prozent = correctAnswer == 0 ? 0 : (correctAnswer / QUESTION_NUMBER) * 100;
        this.level = prozent < 30 ? 0 : (prozent < 60 ? 1 : 2);
        switch (this.level) {
            case 0:
                result = getString(R.string.level1);
                stickerView.setImageResource(R.drawable.nerd1);
                break;
            case 1:
                result = getString(R.string.level2);
                stickerView.setImageResource(R.drawable.nerd2);
                break;
            case 2:
                stickerView.setImageResource(R.drawable.nerd3);
                result = getString(R.string.level3);
        }
        resultView.setText(result);
    }

    public void getDetail(View view) {
        checkAnswer = true;
        for (Button button : rightAnswer) {
            button.setPressed(true);
        }
        checkQuestion6.setText(getString(R.string.text6));
        checkQuestion6.setFocusable(false);
        checkQuestion6.setEnabled(false);
        String answer1 = question1.getText().toString();
        answer1 += getString(R.string.answer1);
        String answer2 = question2.getText().toString();
        answer2 += getString(R.string.answer2);
        String answer3 = question3.getText().toString();
        answer3 += getString(R.string.answer3);
        String answer4 = question4.getText().toString();
        answer4 += getString(R.string.answer4);
        String answer5 = question5.getText().toString();
        answer5 += getString(R.string.answer5);
        String answer6 = question6.getText().toString();
        answer6 += getString(R.string.answer6);
        String detail = getString(R.string.roll);

        detailView.setText(detail);
        question1.setText(answer1);
        question2.setText(answer2);
        question3.setText(answer3);
        question4.setText(answer4);
        question5.setText(answer5);
        question6.setText(answer6);

        for (Button button : allButton) {
            button.setClickable(false);
        }
        for (Button submit : submitButton) {
            submit.setClickable(false);
        }
        moreDetails.setClickable(false);

    }

    /*
    This method reads image and saves it in a new file
     */
    private File parserCertificate(File dir, int cert, String fileName) {
        Bitmap certificate = BitmapFactory.decodeResource(getResources(), cert);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        certificate.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File filePdfDes = new File(dir.getAbsolutePath(), fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePdfDes);
            fileOutputStream.write(bytes.toByteArray());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePdfDes;
    }

    /*
    This function reads raw pdf in /res/raw and saves it in a new file
     */
    private File parserCertificatePdf(File dir, int cert, String filename) {
        Resources resources = getResources();
        InputStream ins = resources.openRawResource(cert);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count;
        try {
            while ((count = ins.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, count);
            }
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(dir.getAbsolutePath(), filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(byteArrayOutputStream.toByteArray());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void sendEmail(View view) {
        if (!checkAnswer) {
            Toast.makeText(this, getString(R.string.warning), Toast.LENGTH_LONG).show();
        } else {
            //screenshot to bitmap
            ScrollView root = (ScrollView) findViewById(R.id.scroll);
            root.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(
                    root.getChildAt(0).getWidth(),
                    root.getChildAt(0).getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Drawable drawable = root.getBackground();
            if (drawable != null) {
                drawable.draw(canvas);
            } else {
                canvas.drawColor(Color.WHITE);
            }
            root.draw(canvas);
            root.setDrawingCacheEnabled(false);
            //Store bitmap to file --> create a directory at external file dir for use later as well
            File dir = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.FROYO) {
                dir = new File(
                        this.getExternalFilesDir(
                                Environment.DIRECTORY_PICTURES), "screen_shot");
            }
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = getString(R.string.file1);
            File file = new File(dir.getAbsolutePath(), fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos);
                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //get cert in image
            //int cert = level == 0 ? R.drawable.cert1 : (level == 1 ? R.drawable.cert2 : R.drawable.cert3);
            //get cert in pdf for better document quality
            int cert = level == 0 ? R.raw.cert1 : (level == 1 ? R.raw.cert2 : R.raw.cert3);
            File filePdfDes = this.parserCertificatePdf(dir, cert, getString(R.string.certificate));

            //Go to gmail
            Uri uri = Uri.fromFile(file);
            Uri uriPdf = Uri.fromFile(filePdfDes);
            ArrayList<Uri> uris = new ArrayList<>(
                    Arrays.asList(uri, uriPdf));
            Intent sendEmail = new Intent();
            sendEmail.setAction(Intent.ACTION_SEND_MULTIPLE);
            sendEmail.setType("*/*");
            sendEmail.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.name));
            sendEmail.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_text));
            sendEmail.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
            if (sendEmail.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(sendEmail, "Send QA"));
            }
        }

    }

    private void viewLink(String link) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void goToFb(View view) {
        viewLink("https://www.facebook.com/LilliCao4");
    }

    public void goToInsta(View view) {
        viewLink("https://www.instagram.com/it_is_funny_42/");
    }

    public void goToEmail(View view) {
        Intent sendEmail = new Intent(Intent.ACTION_SEND);
        sendEmail.setType("*/*");
        sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"caothivananh98@gmail.com"});
        if (sendEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendEmail);
        }
    }

    public void goToGitHub(View view) {
        viewLink("https://github.com/lilliCao/AndroidBasic/tree/master/NCQuiz");
    }
}
