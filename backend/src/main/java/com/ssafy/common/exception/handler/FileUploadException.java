package com.ssafy.common.exception.handler;

import com.ssafy.common.model.response.BaseResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * 파일 업로드시 파일 크기에 대한 예외처리
 */

@ControllerAdvice
public class FileUploadException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<? extends BaseResponseBody> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(417).body(BaseResponseBody.of(417, "파일의 크기가 너무 큽니다!"));

    }
}
