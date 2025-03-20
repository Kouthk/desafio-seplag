package com.example.desafioseletivoseplag.providers.services;

public interface CrudService<T, ID> extends CreateService<T>, UpdateService<T, ID>, DeleteService<ID>, FindService<T, ID> {

}
