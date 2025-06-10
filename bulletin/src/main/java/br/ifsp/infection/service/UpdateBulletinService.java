package br.ifsp.infection.service;

import br.ifsp.infection.dto.BulletinDTO;
import br.ifsp.infection.dto.BulletinMapper;
import br.ifsp.infection.model.Bulletin;
import br.ifsp.infection.persistence.SqliteBulletinDao;

import java.util.NoSuchElementException;

public class UpdateBulletinService {
    private final SqliteBulletinDao bulletinDaoClass;

    public UpdateBulletinService(SqliteBulletinDao bulletinDaoClass) {
        this.bulletinDaoClass = bulletinDaoClass;
    }
    public void update(Bulletin bulletin){
        BulletinDTO bulletinDTO = BulletinMapper.toDTO(bulletin);
        if(bulletinDaoClass.existsById(bulletin.getId()).isEmpty())
            throw new NoSuchElementException("Entidade não existe");
        bulletinDaoClass.update(bulletinDTO);

    }
}
