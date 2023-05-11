package musely.takehome.service;

import musely.takehome.dal.model.User;
import musely.takehome.dao.impl.UserDaoImpl;
import musely.takehome.entity.UserEntity;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import museky.takehome.dao.UserDao;


public class UserService
{

    private static Map<Long,User> db = new HashMap<>();
    UserDao userDao = new UserDaoImpl();
    
    ObjectMapper objectMapper =  new ObjectMapper();
    
    public User insert(User client) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(client.getId());
//        userEntity.setName(client.getName());
//        userEntity.setLastname(client.getLastname());
//        userEntity.setEmail(client.getEmail());
        
        
        userDao.save(objectMapper.convertValue(client, UserEntity.class));
        return getById(client.getId());
    }

    
    public User getById(Long id) {
    	UserEntity userEntity = userDao.get(id);
        return objectMapper.convertValue(userEntity, User.class);
    }


    public List<User> getAll() {
    	List<UserEntity> userlistEntities = userDao.getAll();
        return userlistEntities.stream().map(e-> objectMapper.convertValue(e, User.class)).collect(Collectors.toList());
    }
    
    public boolean exists(Long id) {
        return db.containsKey(id);
    }
    
    public void saveList(List<User> userList){
    	 userDao.saveAll(userList.stream().map(user -> objectMapper.convertValue(user, UserEntity.class)).collect(Collectors.toList()));
    }
    
    public User delete(Long id) {
    	
    	return objectMapper.convertValue(userDao.delete(id), User.class);
    }
    
    public void update(User client) {
    	userDao.update(objectMapper.convertValue(client, UserEntity.class));
    }

}