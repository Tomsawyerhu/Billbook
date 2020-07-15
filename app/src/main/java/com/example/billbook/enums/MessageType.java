package com.example.billbook.enums;

public enum MessageType {
    UNKNOWN(-1),SAVE(0),UPDATE(1),BACK(2),CAL(3);
    int type;

    MessageType(int i) {
        this.type=i;
    }

    public int getType(){
        return this.type;
    }

    public static MessageType from(int i){
        for(MessageType messageType:MessageType.values()){
            if(messageType.getType()==i){
                return messageType;
            }
        }
        //default
        return UNKNOWN;
    }
}
