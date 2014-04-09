/************************************************************************
* 版权所有 (C)2014,	深圳市康佳集团股份有限公司。						 
*																		 
* 文件名称 ：RegisterActivity.java										 
* 文件标识 ：
* 内容摘要 ：
* 其它说明 ：
* 当前版本 ：1.0													
* 作	者 ：黄 必庆
* 完成日期 ：2014-3-13
* 修改记录 ：
* 修改日期 ：															 
* 版   本  号 ：															 
* 修   改  人 ：															 
* 修改内容 ：															 
************************************************************************/
package com.example.androidsmack;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Registration;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity{
	private static final String TAG = "AndroidSmackApplication";
	private static String host = "jabber.org";//"172.21.4.120";
	private EditText etUsername;
	private EditText etPassword;
	private Button btnRegister;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			XMPPConnection.DEBUG_ENABLED = true;
			final ConnectionConfiguration connectionConfig = new ConnectionConfiguration(host, 5222, "openfire");
			XMPPConnection connection = new XMPPConnection(connectionConfig);
			//ProviderManager pm = ProviderManager.getInstance();
			try {
				connection.connect();
				Registration reg = new Registration();
				reg.setType(IQ.Type.SET);
				reg.setTo(connection.getServiceName());
				reg.setUsername("xanthodont");
				reg.setPassword("123456");
				reg.addAttribute("android", "geolo_createUser_android");
				System.out.println("reg:" + reg);
				PacketFilter filter = new AndFilter(new PacketIDFilter(reg
						.getPacketID()), new PacketTypeFilter(IQ.class));
				PacketCollector collector = connection.createPacketCollector(filter);
				connection.sendPacket(reg);

				IQ result = (IQ) collector.nextResult(SmackConfiguration.getPacketReplyTimeout());
				// Stop queuing results
				collector.cancel();// 停止请求results（是否成功的结果）

				if (result == null) {
					Log.i(TAG, "服务器没有返回结果");
				} else if (result.getType() == IQ.Type.ERROR) {
					if (result.getError().toString().equalsIgnoreCase(
							"conflict(409)")) {
						Log.i(TAG, "这个账号已经存在");
					} else {
						Log.i(TAG, "注册失败");
					}
				} else if (result.getType() == IQ.Type.RESULT) {
					Log.i(TAG, "恭喜你注册成功");
				}
				/*
				Log.i(TAG, "开始连接");
				connection.connect();
				connection.login("xanthodont", "123456");
				Log.i(TAG, "登陆成功");*/
			} catch (Exception e) {
				e.printStackTrace();
				connection.disconnect();
				Log.i(TAG, "登陆失败");
			}
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnRegister= (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
				
			}
		});
	}
}
