package com.tj.sqsdemo;

public class MessageObj {

    private final long id;
    private final String msgContent;

	public MessageObj(long id, String msgContent) {
        this.id = id;
        this.msgContent = msgContent;
	}

    public long getId(){
        return id;
    }

    public String getContent(){
        return msgContent;
    }


}
