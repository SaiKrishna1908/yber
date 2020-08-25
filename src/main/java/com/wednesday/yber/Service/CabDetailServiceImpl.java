package com.wednesday.yber.Service;

import com.wednesday.yber.model.Cab;
import com.wednesday.yber.model.CabDetails;
import com.wednesday.yber.repository.CabDetailsRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CabDetailServiceImpl implements CabDetailService {

    private final CabDetailsRepository cabDetailsRepository;

    private Cab getCabFromDetails(CabDetails cabDetails){
        return cabDetails.getCab();
    }

    @Override
    public CabDetails getCabDetails(Cab cab) {
        return cab.getCabDetails();
    }

}
