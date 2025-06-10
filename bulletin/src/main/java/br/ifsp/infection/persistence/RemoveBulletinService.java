package br.ifsp.infection.persistence;

import java.util.NoSuchElementException;
import java.util.Objects;

public class RemoveBulletinService {
    private final SqliteBulletinDao sqliteBulletinDao;

    public RemoveBulletinService(SqliteBulletinDao sqliteBulletinDao) {
        this.sqliteBulletinDao = sqliteBulletinDao;
    }
    public void remove(Integer id){
        Objects.requireNonNull(id,"o id não pode ser nulo");
        if(sqliteBulletinDao.existsById(id).isEmpty())
            throw new NoSuchElementException(String.format("Entidade de id: %d não existe",id));
        sqliteBulletinDao.delete(id);

    }
}
