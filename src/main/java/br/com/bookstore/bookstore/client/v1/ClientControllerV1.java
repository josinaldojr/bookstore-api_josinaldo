package br.com.bookstore.bookstore.client.v1;

import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.client.ClientDTO;
import br.com.bookstore.bookstore.client.services.DeleteClientService;
import br.com.bookstore.bookstore.client.services.ListClientAppService;
import br.com.bookstore.bookstore.client.services.GetClientAppService;
import br.com.bookstore.bookstore.client.services.ListPageClientService;
import br.com.bookstore.bookstore.client.services.SaveClientService;
import br.com.bookstore.bookstore.client.services.UpdateClientService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/client")
public class ClientControllerV1 {

    private final GetClientAppService getClientAppService;
    private final ListClientAppService listClientAppService;
    private final ListPageClientService listPageClientService;
    private final SaveClientService saveClientService;
    private final UpdateClientService updateClientService;
    private final DeleteClientService deleteClientService;

    @GetMapping(value = "/{id}") //list client by id
    public ClientDTO find(@PathVariable Long id) {
        return ClientDTO.from(getClientAppService.findById(id));
    }

    @GetMapping //list all client
    public List<ClientDTO> findAll() {
        return ClientDTO.fromAll(listClientAppService.findAll());
    }

    @GetMapping(path = {"/"})
    public Page<ClientDTO> findPage(@ParameterObject Pageable pageable){ return ClientDTO.fromPage(listPageClientService.findPage(pageable));}

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping //create client
    public void insert(@Valid @RequestBody ClientDTO clientDTO) {
        saveClientService.insert(Client.to(clientDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}") //replace client by id
    public void update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Long id ) {
        updateClientService.update(clientDTO, id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}") //delete client
    public void delete(@PathVariable Long id) {
        deleteClientService.delete(id);
    }
}
