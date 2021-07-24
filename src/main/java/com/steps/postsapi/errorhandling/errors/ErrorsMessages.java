package com.steps.postsapi.errorhandling.errors;

public class ErrorsMessages {
    public final static String userDetailsConflictMsg = "First name and/or last name are not matched to user with id <%d>";
    public static String missingRequiredParameterMsg = "Request body is missing mandatory parameter <%s>";
    public static String userNotExist = "User with id <%d> does not exists";
}
