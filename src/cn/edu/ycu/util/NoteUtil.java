package cn.edu.ycu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class NoteUtil {
	public static final String DELETE_N = "delete_N";
	public static final String DELETE_Y = "delete_Y";
	public static final String SHARE_N = "share_N";
	public static final String SHARE_Y = "share_Y";
	public static String md5(String msg){
		if(msg == null){
			return "";
		}
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] input = msg.getBytes();
			byte[] output = md.digest(input);
			return Base64.encodeBase64String(output);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	public static void main(String[] args){
		System.out.println(md5("0000"));
	}
}
