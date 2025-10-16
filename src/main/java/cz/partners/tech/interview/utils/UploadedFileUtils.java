package cz.partners.tech.interview.utils;

import cz.partners.tech.interview.domain.enums.ProcessState;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UploadedFileUtils {

    public static final String ID_FIELD_KEY = "_id";
    public static final String VALIDATION_ERROR_MESSAGE = "Validation failed!";
    public static final String FILE_NOT_SAVED_ERROR_MESSAGE = "Error storing file to MongoDB.";
    public static final String CLAM_AV_NOT_AVAILABLE_ERROR_MESSAGE = "Clam AV Not Available";

    private static final String UPLOADED_FILE_WITH_ID_NOT_FOUND_ERROR_MESSAGE = "The UploadedFIle with id [%s] does not exist.";
    private static final String UPLOADED_FILE_WITH_ID_AND_STATE_NOT_FOUND_ERROR_MESSAGE =
            "The UploadedFIle with id [%s] and state [%s] does not exist.";

    public String uploadedFileWithIdNotFoundErrorMessage(final String uploadedFileId) {
        return String.format(UPLOADED_FILE_WITH_ID_NOT_FOUND_ERROR_MESSAGE, uploadedFileId);
    }

    public String uploadedFileWithIdAndStateNotFoundErrorMessage(final String uploadedFileId, final ProcessState processState) {
        return String.format(UPLOADED_FILE_WITH_ID_AND_STATE_NOT_FOUND_ERROR_MESSAGE, uploadedFileId, processState);
    }
}
