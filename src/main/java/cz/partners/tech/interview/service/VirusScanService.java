package cz.partners.tech.interview.service;

import java.io.IOException;
import java.io.InputStream;

public interface VirusScanService {

    boolean scan(InputStream fileInputStream) throws IOException;
}
