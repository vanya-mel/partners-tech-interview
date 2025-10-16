package cz.partners.tech.interview.domain.enums;

public enum ProcessState {
    /**
     * File was successfully uploaded and saved to the database for further processing.
     */
    UPLOADED,
    /**
     * File is currently in processing, scanning file.
     */
    IN_PROGRESS,
    /**
     * File was scanned and does not contain any virus.
     */
    FINISHED_OK,
    /**
     * File was scanned and some virus was detected.
     */
    FINISHED_NOK;
}
