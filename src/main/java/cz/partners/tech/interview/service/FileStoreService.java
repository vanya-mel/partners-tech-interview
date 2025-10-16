package cz.partners.tech.interview.service;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStoreService {

    ObjectId storeFile(MultipartFile file);

    GridFsResource loadFile(String fileId);
}
