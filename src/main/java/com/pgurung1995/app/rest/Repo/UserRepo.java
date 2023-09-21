package com.pgurung1995.app.rest.Repo;

import com.pgurung1995.app.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User, Long> {


}
