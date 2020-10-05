package com.appledroideirl.appuntomarcafreelancer.interactor.usuario;

public interface GenerateTokenCallback {
    void onTokenSuccess(String token);

    void ontokenError(String message);
}
