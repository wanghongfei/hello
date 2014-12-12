package cn.fh.hello.common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

public class CredentialUtils {
	
	/**
	 * 从指定session中得到Credential
	 * @param session 当前session
	 * @return 如果用户未登陆,返回null
	 */
	public static Credential getCredential(HttpSession session) {
		Credential credential = (Credential) session.getAttribute(Credential.CREDENTIAL_CONTEXT_ATTRIBUTE);
		
		return credential;
	}
	
	/**
	 * 将用户Credential放入当前session中去.
	 * <p> 如果当前session中存在一个Credential，则扔RuntimeException
	 * @param session 当前session
	 * @param credential
	 * @return
	 */
	public static void createCredential(HttpSession session, Credential credential) {
/*		if (null != session.getAttribute(Credential.CREDENTIAL_CONTEXT_ATTRIBUTE)) {
			throw new RuntimeException("用户已登陆，不得重复设置Credential!");
		}*/

		session.setAttribute(Credential.CREDENTIAL_CONTEXT_ATTRIBUTE, credential);
	}
	
	/**
	 * 得到SHA-1 hash码
	 * @param psd
	 * @return
	 */
	public static String sha(String psd) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(psd.getBytes());
			byte[] bytes = md.digest();
			
			StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < bytes.length ; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(sha("111111"));
	}
}
