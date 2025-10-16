package cz.partners.tech.interview.payload.response;

import cz.partners.tech.interview.domain.enums.ProcessState;

public enum FileProcessState {
    /**
     * File processing/scanning is in progress.
     */
    IN_PROGRESS,
    /**
     * File processing/scanning was finished and file is OK.
     */
    OK,
    /**
     * File processing/scanning was finished and virus was detected.
     */
    NOK;

    public static FileProcessState from(final ProcessState processState) {
        return switch (processState) {
            case UPLOADED, IN_PROGRESS -> IN_PROGRESS;
            case FINISHED_OK -> OK;
            case FINISHED_NOK -> NOK;
        };
    }
}
