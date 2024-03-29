package nguyenngochoa3979.com.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviewButton;
    private TextView mQuestionTextView;
    private int mCurrent = 0;

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
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        }
        else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        updateQuestion();

    }
}
