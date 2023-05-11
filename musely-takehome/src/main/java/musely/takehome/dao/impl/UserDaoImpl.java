package musely.takehome.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import lombok.experimental.var;
import museky.takehome.dao.UserDao;
import musely.takehome.dal.model.User;
import musely.takehome.entity.UserEntity;

public class UserDaoImpl implements UserDao {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

	@PersistenceContext
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public void save(UserEntity userEntity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(userEntity);
			entityManager.getTransaction().commit();
		} catch (Exception e) {

			throw new EntityExistsException("User already exists");
		}

	}

	@Override
	public UserEntity get(Long id) {
		entityManager.getTransaction().begin();
		UserEntity userEntity = entityManager.find(UserEntity.class, id);
		entityManager.getTransaction().commit();
		return userEntity;
	}

	@Override
	public UserEntity delete(Long id) {
		entityManager.getTransaction().begin();
		UserEntity userEntity = entityManager.find(UserEntity.class, id);
		if (userEntity == null)
			throw new EntityNotFoundException("User Not Found!!");
		entityManager.remove(userEntity);
		entityManager.getTransaction().commit();

		return userEntity;
	}

	@Override
	public void update(UserEntity userEntity) {
		entityManager.getTransaction().begin();
		UserEntity userEntityData = entityManager.find(UserEntity.class, userEntity.getId());
		if (userEntityData == null)
			throw new EntityNotFoundException("User Not Found!!");
		if (userEntityData != null) {
			entityManager.merge(userEntity);
		}

		entityManager.getTransaction().commit();

	}

	@Override
	public List<UserEntity> getAll() {
		entityManager.getTransaction().begin();
		TypedQuery<UserEntity> query = entityManager.createQuery("SELECT e FROM UserEntity e", UserEntity.class);
		return query.getResultList();
	}

	@Override
	public void saveAll(List<UserEntity> userList) {
		try {
			entityManager.getTransaction().begin();
			userList.stream().forEach(user -> entityManager.persist(user));
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new EntityExistsException("User already exists");
		}
	}

}
