/*
 * Created on 16 Mar 2017 ( Time 18:41:56 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package net.kumasi.storytime.web.common;

public enum MessageType {
	
	SUCCESS,
	INFO,
	WARNING,
	DANGER;
	
	public String getCss() {
		return name().toLowerCase();
	}
	
}