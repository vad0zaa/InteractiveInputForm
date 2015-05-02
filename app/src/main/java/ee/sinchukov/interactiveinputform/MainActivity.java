package ee.sinchukov.interactiveinputform;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Button sendBtn;
    private Button cancelBtn;
    private ProgressBar  progressBar;
    private EditText editText;
    private EditText editNumber;
    private LinearLayout subLayout;

    private int editTextBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber = (EditText)findViewById(R.id.editNumber);
        editText = (EditText)findViewById(R.id.editText);
        editText.setBackgroundColor(Color.WHITE);
        editNumber.setBackgroundColor(Color.WHITE);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        subLayout = (LinearLayout) findViewById(R.id.linearSubLayout);

    }

    public void sendMessage(View view){

        if(isNumeric(editText.getText().toString()) ) {
            editText.setBackgroundColor(Color.WHITE);
            sendBtn = (Button) findViewById(R.id.sendMessageBtn);
            sendBtn.setVisibility(View.INVISIBLE);

            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

            createCancelButton();
        }
        else
        {
            editText.setBackgroundColor(Color.RED);
        }
    }
    protected void createCancelButton(){

        cancelBtn = new Button(this);
        cancelBtn.setText("Cancel");
        subLayout.addView(cancelBtn);

        // listen cancel btn click
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    progressBar.setVisibility(View.INVISIBLE);
                    sendBtn.setVisibility(View.VISIBLE);
                    editNumber.setText("");
                    editText.setText("");
                    editText.requestFocus();

                    // remove cancel btn
                    subLayout.removeView(cancelBtn);
                }
            });


    }

    protected boolean isNumeric(String text){
        try{
            int number = Integer.parseInt(text);
            return true;
        }catch(Exception e)
        {
            return false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
