package com.fishbirddd.staffcommunication.exceptions;

import com.fishbirddd.staffcommunication.ChatType;

@SuppressWarnings("serial")
public class InvalidChatTypeException extends Exception {

	public InvalidChatTypeException(ChatType chatType) {
		
		super("The ChatType " + chatType.toString() + " is not allowed here!");
		
	}

}
