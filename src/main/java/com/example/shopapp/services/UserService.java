package com.example.shopapp.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.shopapp.dtos.UserDTO;
import com.example.shopapp.exceptions.DataNotFoundException;
import com.example.shopapp.models.Role;
import com.example.shopapp.models.User;
import com.example.shopapp.repositories.RoleRepository;
import com.example.shopapp.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    
    private final RoleRepository roleRepository;
    @Override
    public User createUser(UserDTO userDTO) throws DataNotFoundException {
        String phoneNumber = userDTO.getPhoneNumber();
        // Kiem tra xem sdt da ton tai chua
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        // Convert from userDTO => user
        User newUser = new User().builder()
                       .fullName(userDTO.getFullName())
                       .phoneNumber(userDTO.getPhoneNumber())
                       .password(userDTO.getPassword())
                       .address(userDTO.getAddress())
                       .dateOfBirth(userDTO.getDateOfBirth())
                       .facebookAccountId(userDTO.getFacebookAccountId())
                       .google_account_id(userDTO.getGoogleAccountId())
                       .build();
        Role role = roleRepository.findById(userDTO.getRoleId())
                    .orElseThrow(() -> new DataNotFoundException("Role not found"));
        newUser.setRole(role);
        if(userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0) {
            String password = userDTO.getPassword();
            // String encodePassword = passwordEncoder.endcode(password);
            // Se noi trong phan spring security
            // newUser.setPassword(encodePassword);
        }
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) {
       // Doan này liên quan nhiều đến security, sẽ làm trong phần security
       return null;
    }
    
}
