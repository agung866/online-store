package com.example.demo.usecase;

import com.example.demo.config.properties.AppProperties;
import com.example.demo.config.properties.ErrProperties;
import com.example.demo.exception.CommonException;
import com.example.demo.model.PurchaseModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.response.GenericRs;
import com.example.demo.model.response.ResponseInfo;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserUsecase {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppProperties appProperties;

    @Autowired
    ErrProperties err;

    public ResponseInfo userRegis(UserModel user){
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        try {
            userRepository.insertUser(user);
            responseInfo.setSuccess();
        }catch (Exception e){
            responseInfo.setException(e);

        }
        return responseInfo;
    }

    public ResponseInfo login(String username, String password){
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        try {
            List<UserModel> user=userRepository.getAllDataUser(username);

            if(user.isEmpty()){
                throw  new CommonException(err.getDATA_NOT_FOUND());
            }
            if (!username.equalsIgnoreCase(user.get(0).getUsername())){
                throw new CommonException(err.getUSER_NOT_FOUND());
            }
            if(!password.equalsIgnoreCase(user.get(0).getPassword())){
                throw new CommonException(err.getPASSWORD_NOTMATCH());
            }
            userRepository.updateLastLogin(user.get(0).getIdUser());
            responseInfo.setSuccess();
        }catch (Exception e){
            responseInfo.setException(e);

        }
        return responseInfo;
    }
}

