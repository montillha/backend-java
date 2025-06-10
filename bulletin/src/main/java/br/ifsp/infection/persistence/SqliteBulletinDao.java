package br.ifsp.infection.persistence;

import br.ifsp.infection.connection.ConnectionFactory;
import br.ifsp.infection.dto.BulletinDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteBulletinDao implements BulletinDAO<BulletinDTO,Integer>{
    @Override
    public Integer insert(BulletinDTO bulletin) {
        String sql = " INSERT INTO bulletins (city, state, infected, deaths, icu_ratio, date) VALUES (?,?,?,?,?,?)" ;
        try (var statement= ConnectionFactory.getPreparedStatment(sql)){

            statement.setString(1, bulletin.city());
            statement.setString(2, bulletin.state());
            statement.setInt(3,bulletin.infected());
            statement.setInt(4,bulletin.deaths());
            statement.setDouble(5,bulletin.icuRatio());
            statement.setString(6,bulletin.date());
            statement.executeUpdate();
            ResultSet rs =statement.getGeneratedKeys();
            if(rs.next()){
                int generatedKey = rs.getInt(1);
                return generatedKey;
            }else{
                throw new SQLException("Erro ao obter id");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

    @Override
    public void delete(Integer id) {
        String sql = " DELETE FROM bulletins WHERE id=? ";
        try(var statement = ConnectionFactory.getPreparedStatment(sql)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(BulletinDTO bulletin) {
        String sql = "UPDATE bulletins SET city = ?, state = ?, infected = ?, deaths = ?, icu_ratio = ?, date= ? WHERE ID=? ";
        try(var statement = ConnectionFactory.getPreparedStatment(sql)){
            statement.setString(1, bulletin.city());
            statement.setString(2, bulletin.state());
            statement.setInt(3,bulletin.infected());
            statement.setInt(4,bulletin.deaths());
            statement.setDouble(5,bulletin.icuRatio());
            statement.setString(6,bulletin.date());
            statement.setInt(7,bulletin.id());
            statement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Optional<BulletinDTO> existsById(Integer id) {
        String sql = "SELECT * FROM bulletins WHERE id=?";
        try(var statement = ConnectionFactory.getPreparedStatment(sql)){
            statement.setInt(1,id);
            ResultSet  rs= statement.executeQuery();
            if(rs.next()){
                return Optional.of(new BulletinDTO(
                        rs.getInt("id"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getInt("infected"),
                        rs.getInt("deaths"),
                        rs.getDouble("icu_ratio"),
                        rs.getString("date")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<BulletinDTO> findAll() {
        String sql = "SELECT * FROM bulletins";
        try(var statement = ConnectionFactory.getPreparedStatment(sql)){
            ResultSet rs = statement.executeQuery();
            List<BulletinDTO> bulletins = new ArrayList<>();
            while(rs.next()){
                BulletinDTO newBulletin= new BulletinDTO(
                        rs.getInt("id"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getInt("infected"),
                        rs.getInt("deaths"),
                        rs.getDouble("icu_ratio"),
                        rs.getString("date")
                );
                bulletins.add(newBulletin);
            }
            return bulletins;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return List.of();
    }
}
