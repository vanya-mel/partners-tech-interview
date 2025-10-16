package cz.partners.tech.interview.service.impl;

import cz.partners.tech.interview.domain.UploadedFile;
import cz.partners.tech.interview.domain.enums.ProcessState;
import cz.partners.tech.interview.exception.UploadedFileNotFoundException;
import cz.partners.tech.interview.mapper.UploadedFileMapper;
import cz.partners.tech.interview.repository.UploadedFileRepository;
import cz.partners.tech.interview.service.UploadedFileService;
import cz.partners.tech.interview.utils.UploadedFileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadedFileServiceImpl implements UploadedFileService {

    private final UploadedFileRepository uploadedFileRepository;

    @Override
    public UploadedFile saveFileInformation(final MultipartFile file, final String storedFileId) {
        final UploadedFile uploadedFile = UploadedFileMapper.maptToUploadedFile(file);

        UploadedFileMapper.decorateUploadedFileWithProcessState(uploadedFile, ProcessState.UPLOADED);
        UploadedFileMapper.decorateUploadedFileWithFileId(uploadedFile, storedFileId);

        return uploadedFileRepository.save(uploadedFile);
    }

    @Override
    public UploadedFile getUploadedFile(final String uploadedFileId) {
        return uploadedFileRepository.findById(uploadedFileId)
                .orElseThrow(() -> {
                    final String errorMessage = UploadedFileUtils.uploadedFileWithIdNotFoundErrorMessage(uploadedFileId);
                    return new UploadedFileNotFoundException(errorMessage);
                });
    }

    @Override
    public UploadedFile getUploadedFile(final String uploadedFileId, final ProcessState processState) {
        return uploadedFileRepository.findByIdAndProcessState(uploadedFileId, processState)
                .orElseThrow(() -> {
                    final String errorMessage = UploadedFileUtils.uploadedFileWithIdAndStateNotFoundErrorMessage(uploadedFileId, processState);
                    return new UploadedFileNotFoundException(errorMessage);
                });
    }

    @Override
    public void updateUploadedFileProcessState(final String uploadedFileId, final ProcessState processState) {
        final UploadedFile uploadedFile = getUploadedFile(uploadedFileId);

        UploadedFileMapper.decorateUploadedFileWithProcessState(uploadedFile, processState);

        uploadedFileRepository.save(uploadedFile);
    }
}
