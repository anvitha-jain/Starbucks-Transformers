package com.project.mysystemproject.exception;

public class CardNotFoundException extends Exception {

private Long cardno;

public CardNotFoundException(Long cardno) {
        super(String.format("Card is not found with number : '%s'", cardno));
        }

}