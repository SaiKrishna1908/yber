package com.wednesday.yber.Service;

import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.CabDetails;
import com.wednesday.yber.repository.CabDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CabDetailServiceImpl implements CabDetailService {

    private final CabDetailsRepository cabDetailsRepository;


    @Override
    public CabDetails getCabDetails(Cab cab) {
        return cab.getCabDetails();
    }

}
