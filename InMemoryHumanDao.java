package mate.academy.dao;

import mate.academy.di.Component;
import mate.academy.model.Human;
import mate.academy.model.Model;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryHumanDao implements Dao {

    private static final List<Human> inMemoryStorage = new ArrayList<>();

    @Override
    public void save(Model human) {
        inMemoryStorage.add((Human) human);
    }

    @Override
    public Model get() {
        return inMemoryStorage.get(0);
    }
}
