package helloworld.android.appjangle.com.appjanglehelloworld;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;

import com.appjangle.android.AppjangleAndroid;

import io.nextweb.Link;
import io.nextweb.Query;
import io.nextweb.Session;


public class HelloWorldActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        final Session session = AppjangleAndroid.createSession(this.getApplicationContext());

        Link firstName = session.link("http://slicnet.com/mxrogm/mxrogm/xplr/.n/Appjangle_JS/.n/Examples/.n/Create_Person_Data/.n/First_Name");
        Link lastName = session.link("http://slicnet.com/mxrogm/mxrogm/xplr/.n/Appjangle_JS/.n/Examples/.n/Create_Person_Data/.n/Last_Name");

        // Creating data
        final Query john = session.seed();
        john.append("John").append(firstName);
        john.append("Smith").append(lastName);
        session.commit().get();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView t = (TextView)findViewById(R.id.output);

                t.setText("Created data: "+john.get().uri());

                session.close().get();
            }
        }, 100);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hello_world, menu);
        return true;
    }

}
