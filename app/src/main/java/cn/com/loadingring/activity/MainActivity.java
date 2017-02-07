package cn.com.loadingring.activity;

import cn.com.loadingring.R;
import cn.com.loadingring.R.id;
import cn.com.loadingring.R.layout;
import cn.com.loadingring.view.LoadingRingView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
	
	private LoadingRingView loadingRingView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        
        loadingRingView = (LoadingRingView)findViewById(id.loadingRingView1);
        loadingRingView.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case id.loadingRingView1:
			if(loadingRingView.isRunning() == true){
				loadingRingView.setRunning(false);
			}else{
				loadingRingView.setRunning(true);
			}
			break;
		default:
			break;
		}
	}
}
