package br.com.bookstore.bookstore.client.services;

import br.com.bookstore.bookstore.client.Client;

import java.util.List;

@FunctionalInterface
public interface GetAllClienteAppService {
    List<Client> findAll();
}
