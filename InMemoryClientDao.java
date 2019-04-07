package mate.academy.dao;

import mate.academy.di.Component;
import mate.academy.model.Client;
import mate.academy.model.Model;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryClientDao implements Dao {

    private static final List<Client> inMemoryStorage = new ArrayList<>();

    @Override
    public void save(Model client) {
        inMemoryStorage.add((Client) client);
    }

    @Override
    public Model get() {
        return inMemoryStorage.get(0);
    }
}
