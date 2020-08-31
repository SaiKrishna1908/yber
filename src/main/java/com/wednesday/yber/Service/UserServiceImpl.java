package com.wednesday.yber.Service;


import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.wednesday.yber.api.v1.domain.UserDTO;
import com.wednesday.yber.exceptions.UserAlreadyExistsException;
import com.wednesday.yber.mapper.UserDTOMapper;
import com.wednesday.yber.mapper.UserMapper;
import com.wednesday.yber.model.ID;
import com.wednesday.yber.model.User;
import com.wednesday.yber.repository.IDRepository;
import com.wednesday.yber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserDTOMapper userDTOMapper = UserDTOMapper.INSTANCE;
    private final IDRepository idRepository;

    @Override
    public User findByPhoneNumber(String phoneNumber) {

        return userRepository.findByPhoneNumber(phoneNumber).orElseGet(null);
    }

    @Override
    public JOpenCageLatLng getUserLocation(Double Latitude, Double Longitude) {

        JOpenCageLatLng latLng = new JOpenCageLatLng();
        latLng.setLat(Latitude);
        latLng.setLng(Longitude);
        return latLng;

    }

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = userDTOMapper.UserDTOToUser(userDTO);

        try{


             // check if user already exists

            Optional<User> presentUser  = userRepository.findByPhoneNumber(
                     user.getPhoneNumber()
            );

            if(presentUser.isPresent())
                throw new UserAlreadyExistsException("User Already Present");

            ID userId= new ID();

            userId.setUser(user);

            ID savedUserId = idRepository.save(userId);


            user.setId(savedUserId.getId());

            User savedUser = userRepository.save(user);
            userId.setUser(savedUser);

            idRepository.save(userId);
          return userMapper.UserToUserDTO(savedUser);
        }
        catch (UserAlreadyExistsException e){
            throw new UserAlreadyExistsException("User Already Present");
        }
        catch (Exception e){
            log.debug(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error creating user");
        }

    }

    @Override
    public UserDTO getUserById(Long id) {



        Optional<User> optionalUser = Optional.of(idRepository.findById(id).get().getUser());

        if(optionalUser.isPresent()){
            UserDTO resultUser = userMapper.UserToUserDTO(optionalUser.get());


            return resultUser;
        }

        return null;
    }

    @Override
    public Long getIdByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).get().getId();
    }
}
