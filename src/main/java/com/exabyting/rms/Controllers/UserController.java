package com.exabyting.rms.Controllers;

import com.exabyting.rms.DTOs.UserDto;
import com.exabyting.rms.Entities.Helper.Role;
import com.exabyting.rms.Exception.ResourceNotFound;
import com.exabyting.rms.Services.UserServices;
import com.exabyting.rms.Utils.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    private UserServices userServices;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public ResponseEntity<?> create(@RequestBody UserDto user){

        System.out.println("hello");
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        UserDto userDto = userServices.create(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> allusers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ){

        PageableResponse<UserDto> alluser = userServices.alluser(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(alluser,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> byId(@PathVariable Integer id ) throws ResourceNotFound {
        return new ResponseEntity<>(userServices.byId(id),HttpStatus.OK);
    }

    @GetMapping("/roles/{roleName}")
    public ResponseEntity<?> byRole(
            @PathVariable String roleName,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir                        ){

        try {
            Role role = Role.valueOf(roleName.toUpperCase());
            return new ResponseEntity<>(userServices.byRole(pageNumber,pageSize,sortBy,sortDir,role),HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role name");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws ResourceNotFound {

        userServices.deleteUser(id);
        return new ResponseEntity<>("deleted Successfully ",HttpStatus.OK);
    }


}
