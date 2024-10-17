package com.example.pinterest;

public class InboxMessage {
    private String sender;
    private String preview;
    private int imageResource; // Field for the image resource

    public InboxMessage(String sender, String preview, int imageResource) {
        this.sender = sender;
        this.preview = preview;
        this.imageResource = imageResource;
    }

    public String getSender() {
        return sender;
    }

    public String getPreview() {
        return preview;
    }

    public int getImageResource() {
        return imageResource;
    }
}
