package app.repositories;

import app.entities.UserEntity;
import app.jpa_.BaseRepository;

public class UserRepository extends BaseRepository<UserEntity> {
	public UserRepository() {
		super(UserEntity.class);
	}
}
