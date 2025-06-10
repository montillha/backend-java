package br.ifsp.infection.service;

import br.ifsp.infection.dto.BulletinDTO;
import br.ifsp.infection.dto.BulletinMapper;
import br.ifsp.infection.exception.EntityAlreadyExistsException;
import br.ifsp.infection.model.Bulletin;
import br.ifsp.infection.persistence.SqliteBulletinDao;

public class RegisterBulletinService {
    private final SqliteBulletinDao bulletinDAO;

    public RegisterBulletinService(SqliteBulletinDao bulletinDAO) {
        this.bulletinDAO = bulletinDAO;
    }


    public int register(Bulletin bulletin){

        if(bulletin==null){
            throw new IllegalArgumentException("bulletin não pode ser nulo");
        }
        if(bulletin.getCity() == null ||bulletin.getCity().isBlank())
            throw  new IllegalArgumentException("cidade não pode ser nula ou vazia");
        if(bulletin.getState() == null )
            throw  new IllegalArgumentException("estado não pode ser nulo");

        if(bulletin.getDeaths()<0 ||bulletin.getInfected()<0)
            throw  new IllegalArgumentException("n° de infectados ou mortos não pode ser nula ou vazia");

        if(bulletin.getDate() == null)
            throw  new IllegalArgumentException("data não pode ser nula ");


        BulletinDTO bulletinDTO = BulletinMapper.toDTO(bulletin);
        if(bulletinDAO.existsById(bulletin.getId()).isPresent())
            throw new EntityAlreadyExistsException(String.format("Entidade de id: %d já existe",bulletinDTO.id()));

        return bulletinDAO.insert(bulletinDTO);

    }
}
