package nguyenngochoa3979.com.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviewButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int mCurrent = 0;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private boolean mIsCheater;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    private void updateQuestion(){
        int question = mQuestionBank[mCurrent].getTextResId();
        mQuestionTextView.setText(question);
    };
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrent].isAnswerTrue();
        int messageResId = 0;

        if(mIsCheater)
        {
            messageResId = R.string.judgment_toast;
        }
        else {


            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question_textview);
        //int question = mQuestionBank[mCurrent].getTextResId();
        //mQuestionTextView.setText(questio
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrent = (mCurrent + 1)%mQuestionBank.length;
                updateQuestion();

            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do st
                checkAnswer(false);
            }
        });
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrent = (mCurrent + 1) % mQuestionBank.length;
                updateQuestion();
                mIsCheater = false;
            }
        });
        mPreviewButton = (Button) findViewById(R.id.preview_button);
        mPreviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrent = (mCurrent + 1)%mQuestionBank.length;
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start cheat activity
                boolean answerIsTrue = mQuestionBank[mCurrent].isAnswerTrue();
                Intent i = CheatActivity.newIntent(MainActivity.this,answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);

            }
        });
        if(savedInstanceState != null){
            mCurrent = savedInstanceState.getInt(KEY_INDEX,0);
        }

        updateQuestion();

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null)
            {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {

        super.onSaveInstanceState(saveInstanceState);

        Log.i(TAG, "onSaveInstanceState");
        saveInstanceState.putInt(KEY_INDEX, mCurrent);
    }



    public void OnStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    public void OnPause()
    {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    public void OnResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    public void OnStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    public void Destroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
