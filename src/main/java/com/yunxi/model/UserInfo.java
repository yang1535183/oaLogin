package com.yunxi.model;

public class UserInfo {
    private Integer uid;

    private String username;

    private String passwd;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", username=" + username + ", passwd=" + passwd + "]";
	}
    
}