package mate.academy.dao;

import mate.academy.model.Model;

public interface Dao {
    void save(Model model);

    Model get();
}
