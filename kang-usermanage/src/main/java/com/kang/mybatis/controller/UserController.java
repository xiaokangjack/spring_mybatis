package com.kang.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kang.mybatis.bean.EasyUIResult;
import com.kang.mybatis.pojo.User;
import com.kang.mybatis.service.NewUserService;

@RequestMapping("user")
@Controller
public class UserController {

    // @Autowired
    // private UserService userService;

    @Autowired
    private NewUserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIResult queryUserList(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        return this.userService.queryUserList(page, rows);
    }
    
    //查询数据
    @RequestMapping(value="{userId}",method=RequestMethod.GET)
    public ResponseEntity<User> quseryUserById(@PathVariable("userId") Long userId){
    	try {
			User user=this.userService.queryUserById(userId);
			if(null==user){
				//返回404资源不存在
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			//返回200，成功
			//return ResponseEntity.status(HttpStatus.OK).body(user);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//查询出错，返回500
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    //新增数据
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> saveUser(User user){
    	try {
			this.userService.saveUser(user);;
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//查询出错，返回500
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    //更新数据
    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(User user){
    	try {
			this.userService.updateUser(user);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//查询出错，返回500
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    //删除数据
    @RequestMapping(method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestParam(value="id",defaultValue="0") Long id){
    	try {
    		if(id.intValue()==0){
    			//参数有误
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    		}
    		this.userService.deleteUserById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//查询出错，返回500
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
