package com.revature.bankingapp.DAO;

import com.revature.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Stereotype annotation from Spring core that marks this class as a Spring Bean (class that spring manages)
// And specifically this class is used for db interactions.
public interface ToDoDao extends JpaRepository<ToDo, Integer> {

    List<ToDo> findAllByIsCompleted(boolean isCompleted);
}
