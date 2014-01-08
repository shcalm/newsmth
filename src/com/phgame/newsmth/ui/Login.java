package com.phgame.newsmth.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.phgame.newsmth.R;
import com.phgame.newsmth.task.LoginTask;
import com.phgame.newsmth.ui.widget.PhProgressBarDialog;
import com.phgame.newsmth.util.SmthCrawler;

public class Login extends BaseActivity {
	private EditText mUser; // �ʺű༭��
	private EditText mPassword; // ����༭��

	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			int ret = msg.arg1;
			if(ret == 1){
	    		Toast.makeText(Login.this, "Welcome you ", Toast.LENGTH_SHORT).show();
	        	Intent intent = new Intent(Login.this,MainView.class); 
	        	startActivity(intent);
	        	Login.this.finish();
	    	} else{
	           
	        	new AlertDialog.Builder(Login.this)
				.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
				.setTitle("��¼ʧ��")
				.setMessage("�ʺŻ������벻��ȷ��\n������������룡")
				.create().show();
	        }
			super.handleMessage(msg);
		}
		
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        mUser = (EditText)findViewById(R.id.login_user_edit);
        mPassword = (EditText)findViewById(R.id.login_passwd_edit);
        
    }
    

    public void login_newsmth(View v) {
    	String username = mUser.getText().toString();
    	String password = mPassword.getText().toString();
    	
        if("".equals(mUser.getText().toString()) || "".equals(mPassword.getText().toString()))   //�ж� �ʺź�����
        {
        	new AlertDialog.Builder(Login.this)
			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
			.setTitle("��¼����")
			.setMessage("�ʺŻ������벻��Ϊ�գ�\n��������ٵ�¼��")
			.create().show();
        	return;
         }
    	//int ret = SmthCrawler.getInstance().login(username, password);

        new LoginTask(mHandler).execute(username,password);
        this.showDialog(0);
    	
      }  
    @Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
    	ProgressDialog bar = new PhProgressBarDialog(this);
    	bar.setIndeterminate(true);
    	bar.setCancelable(false);
    	bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	//bar.setFeatureDrawable(featureId, drawable)
    	
    	
		return bar;
	}


	public void login_back(View v) {     //������ ���ذ�ť
      	this.finish();
      }  
    public void login_no_newsmth(View v) {     //�������밴ť
    	//Uri uri = Uri.parse("http://3g.qq.com"); 
    	Intent intent = new Intent(this,MainView.class); 
    	startActivity(intent);
    	this.finish();

      }  
}
