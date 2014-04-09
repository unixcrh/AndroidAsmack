/************************************************************************
* 版权所有 (C)2014,	深圳市康佳集团股份有限公司。						 
*																		 
* 文件名称 ：AndroidSmackApplication.java										 
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
package com;

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

import android.app.Application;
import android.util.Log;

public class AndroidSmackApplication extends Application{
	 
	
	static {
		
		
	}
}
