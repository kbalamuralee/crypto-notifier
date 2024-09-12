package com.bitgo.crypto_notifier.notification.domain.service.mocks;

import org.springframework.stereotype.Service;

import com.bitgo.crypto_notifier.notification.domain.service.IBtcService;

@Service
public class MockBtcService implements IBtcService {
    @Override
    public String getBtcData(String dataPointKeys) {
        return "DUMMY_DATA";
    }
}
