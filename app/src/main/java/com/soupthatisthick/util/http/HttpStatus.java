package com.soupthatisthick.util.http;

/**
 * Created by Owner on 6/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public enum HttpStatus
{
    CONTINUE(100),
    SWITCHING_PROTOCOLS(101),
    PROCESSING(102),
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NON_AUTH_INFO(203),
    NO_CONTENT(204),
    RESET_CONTENT(205),
    PARTIAL_CONTENT(206),
    MULTI_STATUS(207),
    ALREADY_REPORTED(208),
    IM_USED(226),
    MULTIPLE_CHOICES(300),
    MOVED_PERMANENTLY(301),
    FOUND(302),
    SEE_OTHER(303),
    NOT_MODIFIED(304),
    USE_PROXY(305),
    SWITCH_PROXY(306),
    TEMPORARY_REDIRECT(307),
    PERMANENT_REDIRECT(308),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    PAYMENT_REQUIRED(402),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    NOT_ACCEPTABLE(406),
    PROXY_AUTH_REQUIRED(407),
    REQUEST_TIMEOUT(408),
    CONFLICT(409),
    GONE(410),
    LENGTH_REQUIRED(411),
    PRECONDITION_FAILED(412),
    PAYLOAD_TO_LARGE(413),
    URI_TOO_LARGE(414),
    UNSUPPORTED_MEDIA_TYPE(415),
    RANGE_NOT_SATISFIABLE(416),
    EXPECTATION_FAILED(417),
    IM_A_TEAPOT(418),
    MISDIRECTED_REQUEST(421),
    LOCKED(423),
    FAILED_DEPENDENCY(424),
    UPGRADE_REQUIRED(426),
    PRECONDITION_REQUIRED(428),
    TOO_MANY_REQUESTS(429),
    REQUEST_HEADER_FIELDS_TOO_LARGE(341),
    UNAVAILABLE_FOR_LEGAL_REASONS(451),
    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504),
    HTTP_VERSION_NOT_SUPPORTED(505),
    VARIANT_ALSO_NEGOTIATES(506),
    INSUFFICIENT_STORAGE(507),
    LOOP_DETECTED(508),
    NOT_EXTENDED(510),
    NETWORK_AUTH_REQUIRED(511)
    ;

    private int code;

    private HttpStatus(int code)
    {
        this.code = code;
    }

    /**
     * Determines the enum value given the integer code
     * @param code
     * @return
     */
    public static final HttpStatus parse(int code) {
        for(HttpStatus httpStatus : values()) {
            if (httpStatus.getCode() == code)
            {
                return httpStatus;
            }
        }
        throw new RuntimeException("Failed to determine HTTP status code for value " + code);
    }

    /**
     * Returns the integer code for the value
     * @return
     */
    public int getCode() {
        return code;
    }
}
