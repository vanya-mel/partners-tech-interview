package cz.partners.tech.interview.service.impl;

import cz.partners.tech.interview.domain.UploadedFile;
import cz.partners.tech.interview.domain.enums.ProcessState;
import cz.partners.tech.interview.mapper.ResponseMapper;
import cz.partners.tech.interview.mapper.UploadedFileMapper;
import cz.partners.tech.interview.messaging.dto.UploadedFileMessage;
import cz.partners.tech.interview.messaging.producer.RabbitProducer;
import cz.partners.tech.interview.payload.response.FileCheckResultResponse;
import cz.partners.tech.interview.service.FileStoreService;
import cz.partners.tech.interview.service.FileUploadService;
import cz.partners.tech.interview.service.UploadedFileService;
import cz.partners.tech.interview.service.VirusScanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final FileStoreService fileStoreService;
    private final VirusScanService virusScanService;
    private final UploadedFileService uploadedFileService;
    private final RabbitProducer<UploadedFileMessage> rabbitProducer;

    @Override
    @Transactional // TODO if there'll be some time -> Set-up transaction manager to run this in transactional mode
    public String uploadFile(final MultipartFile file) {
        final ObjectId storedFileId = fileStoreService.storeFile(file);
        final UploadedFile savedUploadedFile = uploadedFileService.saveFileInformation(file, storedFileId.toString());
        final String savedUploadedFileId = savedUploadedFile.getId();

        final UploadedFileMessage uploadedFileMessage = new UploadedFileMessage(savedUploadedFileId);
        rabbitProducer.sendMessage(uploadedFileMessage);

        return savedUploadedFileId;
    }

    @Override
    public FileCheckResultResponse checkFileProcessResult(final String uploadedFileId) {
        final UploadedFile uploadedFile = uploadedFileService.getUploadedFile(uploadedFileId);

        return ResponseMapper.mapToFileCheckResult(uploadedFile);
    }

    @Override
    public void processUploadedFile(final String uploadedFileId) throws IOException {
        final ProcessState inProgress = ProcessState.IN_PROGRESS;
        uploadedFileService.updateUploadedFileProcessState(uploadedFileId, inProgress);

        final UploadedFile uploadedFile = uploadedFileService.getUploadedFile(uploadedFileId, inProgress);
        final GridFsResource gridFsResource = fileStoreService.loadFile(uploadedFile.getFileId());

        final boolean scanResult = virusScanService.scan(gridFsResource.getInputStream());

        final ProcessState finishedProcessState = UploadedFileMapper.mapToProcessState(scanResult);

        uploadedFileService.updateUploadedFileProcessState(uploadedFileId, finishedProcessState);
        log.info("Uploaded file {} processed, the result is {}.", uploadedFileId, finishedProcessState);
    }
}
