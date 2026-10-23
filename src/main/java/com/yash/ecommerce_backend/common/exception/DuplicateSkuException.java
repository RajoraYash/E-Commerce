package com.yash.ecommerce_backend.common.exception;

public class DuplicateSkuException extends RuntimeException {
    public DuplicateSkuException(String message){
        super(message);
    }

}
