package com.errabi.simplenote.utils;

import org.springframework.http.HttpStatus;
public final class SimpleNoteConst {
    private SimpleNoteConst() {
    }
    // HTTP Status Codes
    public static final HttpStatus STATUS_NOT_FOUND = HttpStatus.NOT_FOUND;
    public static final HttpStatus STATUS_CONFLICT = HttpStatus.CONFLICT;
    public static final HttpStatus STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST;
    public static final HttpStatus STATUS_INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;

    // Error codes and descriptions
    public static final String LABEL_NOTE_DISSOCIATION_ERROR_CODE = "10002";
    public static final String LABEL_NOTE_DISSOCIATION_ERROR_DESCRIPTION = "Error dissociating note from label";

    public static final String NOTE_NOT_ASSOCIATED_WITH_LABEL_ERROR_CODE = "10001";
    public static final String NOTE_NOT_ASSOCIATED_WITH_LABEL_ERROR_DESCRIPTION = "Note is not associated with the provided label";

    public static final String LABEL_NOTE_ASSOCIATION_ERROR_CODE = "10000";
    public static final String LABEL_NOTE_ASSOCIATION_ERROR_DESCRIPTION = "Error associating note with label";

    public static final String NOTE_NOT_FOUND_ERROR_CODE = "99999";
    public static final String NOTE_NOT_FOUND_ERROR_DESCRIPTION = "Note not found";

    public static final String USER_NOT_FOUND_ERROR_CODE = "99998";
    public static final String USER_NOT_FOUND_ERROR_DESCRIPTION = "User not found";

    public static final String LABEL_NOT_FOUND_ERROR_CODE = "99997";
    public static final String LABEL_NOT_FOUND_ERROR_DESCRIPTION = "Label not found";
    public static final String LABEL_NOTE_FOUND_ERROR_LOG = "Label not found with ID {}";

    public static final String USER_ALREADY_EXISTS_ERROR_CODE = "99996";
    public static final String USER_ALREADY_EXISTS_ERROR_DESCRIPTION = "User already exists";

    public static final String LABEL_SAVE_ERROR_CODE = "99995";
    public static final String LABEL_SAVE_ERROR_DESCRIPTION = "Data Integrity Violation while saving the label";

    public static final String LABEL_UNEXPECTED_ERROR_CODE = "99994";
    public static final String LABEL_UNEXPECTED_ERROR_DESCRIPTION = "Unexpected error occurred while saving the label";

    public static final String LABEL_FETCH_ALL_ERROR_CODE = "99993";
    public static final String LABEL_FETCH_ALL_ERROR_DESCRIPTION = "Unexpected error occurred while fetching all labels";

    public static final String LABELS_NOT_AVAILABLE_ERROR_CODE = "99992";
    public static final String LABELS_NOT_AVAILABLE_ERROR_DESCRIPTION = "No labels found in the system";

    public static final String LABEL_UPDATE_ERROR_CODE = "99991";
    public static final String LABEL_UPDATE_ERROR_DESCRIPTION = "Error updating the label";

    public static final String LABEL_DELETE_ERROR_CODE = "99990";
    public static final String LABEL_DELETE_ERROR_DESCRIPTION = "Error deleting the label";
    public static final String BEAN_VALIDATION_ERROR_CODE="90000";
}