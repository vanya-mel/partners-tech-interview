package cz.partners.tech.interview.service.impl;

import com.mongodb.client.gridfs.model.GridFSFile;
import cz.partners.tech.interview.exception.FileStorageException;
import cz.partners.tech.interview.service.FileStoreService;
import cz.partners.tech.interview.utils.UploadedFileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStoreServiceImpl implements FileStoreService {

    private final GridFsTemplate gridFsTemplate;

    @Override
    public ObjectId storeFile(final MultipartFile file) {
        try {
            return gridFsTemplate.store(
                    file.getInputStream(),
                    file.getOriginalFilename(),
                    file.getContentType()
            );
        } catch (Exception e) {
            log.error(UploadedFileUtils.FILE_NOT_SAVED_ERROR_MESSAGE, e);
            throw new FileStorageException(UploadedFileUtils.FILE_NOT_SAVED_ERROR_MESSAGE, e);
        }
    }

    @Override
    public GridFsResource loadFile(final String fileId) {
        final Query findByIdQuery = Query.query(Criteria.where(UploadedFileUtils.ID_FIELD_KEY).is(fileId));
        final GridFSFile gridFSFile = gridFsTemplate.findOne(findByIdQuery);
        return gridFsTemplate.getResource(gridFSFile);
    }
}
