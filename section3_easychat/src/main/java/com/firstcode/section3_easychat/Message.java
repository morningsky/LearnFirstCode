package com.firstcode.section3_easychat;

/**
 * Created by sky on 2015/8/20.
 */
public class Message {
    public static final int TYPE_RECEIVED = 1;
    public static final int TYPE_SEND = 2;
    private String content;
    private int type;

    public Message(String content,int type)
    {
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
