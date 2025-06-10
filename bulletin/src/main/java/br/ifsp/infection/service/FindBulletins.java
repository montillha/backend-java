package br.ifsp.infection.service;

import br.ifsp.infection.dto.BulletinMapper;
import br.ifsp.infection.model.Bulletin;
import br.ifsp.infection.persistence.SqliteBulletinDao;

import java.util.List;

public class FindBulletins {
    private final SqliteBulletinDao sqliteBulletinDao;

    public FindBulletins(SqliteBulletinDao sqliteBulletinDao) {
        this.sqliteBulletinDao = sqliteBulletinDao;
    }
    public List<Bulletin> findAll(){
        return sqliteBulletinDao.findAll().stream().map(BulletinMapper::fromDTO).toList();
    }

}
