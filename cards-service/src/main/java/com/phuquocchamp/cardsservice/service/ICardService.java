package com.phuquocchamp.cardsservice.service;

import com.phuquocchamp.cardsservice.dto.CardDto;
import org.springframework.stereotype.Service;

public interface ICardService {
    void createCard(String mobileNumber);


    CardDto fetchCard(String mobileNumber);


    boolean updateCard(CardDto cardsDto);


    boolean deleteCard(String mobileNumber);
}
