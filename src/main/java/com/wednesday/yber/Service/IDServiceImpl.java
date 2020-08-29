package com.wednesday.yber.Service;

import com.wednesday.yber.model.ID;
import com.wednesday.yber.repository.IDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IDServiceImpl implements IDService {

    private final IDRepository idRepository;

    @Override
    public ID getIDByPhoneNumber(String phoneNumber) {
        return idRepository.getIDByUser_PhoneNumberContaining(phoneNumber);
    }
}
