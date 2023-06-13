package com.example.warehouse.controller;

import com.example.warehouse.entity.Supplier;
import com.example.warehouse.entity.User;
import com.example.warehouse.service.SupplierService;
import com.example.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SupplierService supplierService;

    //通过ID获取用户
    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.getById(userId);
    }

    //通过名字获取用户
    @GetMapping("/usersName/{userName}")
    public User getUserByName(@PathVariable String userName) {
        return userService.getByUserName(userName);
    }

    //通过状态获取用户
    @GetMapping("/users/type/{type}")
    public List<User> getUserByType(@PathVariable String type) {
        return userService.getUserByType(type);
    }

    //获取所有用户
    @GetMapping("/users/")
    public List<User> getAllUsers() {

        return userService.list();
    }

    //添加用户
    @PostMapping("/users/")
    public boolean addUser(@RequestBody User user) {

        User user1 = userService.getByUserName(user.getUserName());

        if (user1 != null){
           return false;
        }

        return userService.save(user);
    }

    //用户登录
    @PostMapping("/users/login")
    public boolean loginUser(@RequestBody User user) {
        System.out.println("hi hi hi hi hi hi");
        System.out.println(user);
        User storedUser = userService.getByUserName(user.getUserName());
        return storedUser != null && storedUser.getPassword().equals(user.getPassword()) && storedUser.getType().equals(user.getType());
    }

    //更新用户
    @PutMapping("/users/")
    public boolean updateUser(@RequestBody User user) {
        return userService.updateById(user);
    }

    //删除用户
    @DeleteMapping("/users/{userId}")
    public boolean deleteUser(@PathVariable int userId) {
        return userService.removeById(userId);
    }

    //通过ID获取供应商
    @GetMapping("/suppliers/{supplierId}")
    public Supplier getSupplierById(@PathVariable int supplierId) {
        return supplierService.getById(supplierId);
    }


    //通过名字获取供应商
    @GetMapping("/suppliersName/{supplierName}")
    public Supplier getSupplierByName(@PathVariable String supplierName) {
        return supplierService.getSupplierByName(supplierName);
    }

    //获取所有供应商
    @GetMapping("/suppliers/")
    public List<Supplier> getAllSuppliers() {
        return supplierService.list();
    }

    //添加供应商
    @PostMapping("/suppliers/")
    public boolean addSupplier(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    //更新供应商
    @PutMapping("/suppliers/")
    public boolean updateSupplier(@RequestBody Supplier supplier) {
        return supplierService.updateById(supplier);
    }

    //删除供应商
    @DeleteMapping("/suppliers/{supplierId}")
    public boolean deleteSupplier(@PathVariable int supplierId) {
        return supplierService.removeById(supplierId);
    }
}
