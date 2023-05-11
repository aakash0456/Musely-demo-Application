package museky.takehome.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import musely.takehome.dal.model.User;
import musely.takehome.entity.UserEntity;

public interface UserDao {
	public void save(UserEntity userEntity);
	public UserEntity get(Long id);
	public UserEntity delete(Long id);
	public void update(UserEntity userEntity);
	public List<UserEntity> getAll();
	public void saveAll(List<UserEntity> userList);
	
}
