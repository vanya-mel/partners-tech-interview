package cz.partners.tech.interview.service.impl;

import cz.partners.tech.interview.exception.ClamAVNotAvailableException;
import cz.partners.tech.interview.service.VirusScanService;
import cz.partners.tech.interview.utils.UploadedFileUtils;
import fi.solita.clamav.ClamAVClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class VirusScanServiceImpl implements VirusScanService {

    private final ClamAVClient clamavClient;

    @Override
    public boolean scan(final InputStream fileInputStream) throws IOException {
        if (!clamavClient.ping()) {
            throw new ClamAVNotAvailableException(UploadedFileUtils.CLAM_AV_NOT_AVAILABLE_ERROR_MESSAGE);
        }

        byte[] scanResult = clamavClient.scan(fileInputStream);

        return ClamAVClient.isCleanReply(scanResult);
    }
}
