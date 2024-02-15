package com.revature.bankingapp.controller;

import com.revature.bankingapp.service.AccountService;
import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/users/{userId}/todos")
    public ResponseEntity<ToDo> addToDo(@PathVariable Integer userId, @RequestBody ToDo toDo){
        return ResponseEntity.ok(toDoService.addToDo(toDo, userId));
    }

    @GetMapping("todos/{toDoId}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable Integer toDoId) {
        return ResponseEntity.ok(toDoService.getToDoById(toDoId));
    }

    @PutMapping("todos/{toDoId}")
    public ResponseEntity<ToDo> updateToDoById(@PathVariable Integer toDoId, @RequestBody ToDo toDo) {
        return ResponseEntity.ok(toDoService.updateToDoById(toDoId, toDo));
    }

    @PutMapping("todos/{toDoId}")
    public ResponseEntity<ToDo> updateToDoById(@PathVariable Integer toDoId, @RequestBody ToDo toDo) {
        return ResponseEntity.ok(toDoService.updateToDoById(toDoId, toDo));
    }

    @PutMapping("todos/{toDoId}")
    public ResponseEntity<ToDo> updateToDoById(@PathVariable Integer toDoId, @RequestBody ToDo toDo) {
        return ResponseEntity.ok(toDoService.updateToDoById(toDoId, toDo));
    }

    @DeleteMapping("todos/{toDoId}")
    public ResponseEntity<?> deleteToDoById(@PathVariable Integer toDoId) {
        toDoService.deleteToDoById(toDoId);
        return ResponseEntity.ok().build();
    }







}
