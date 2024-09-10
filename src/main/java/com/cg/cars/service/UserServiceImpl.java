package com.cg.cars.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cars.dao.IUserRepository;
import com.cg.cars.entities.User;
import com.cg.cars.exception.UserNotFoundException;
import com.cg.cars.exception.UserServiceException;
import com.cg.cars.model.UserDTO;
import com.cg.cars.utils.UserUtil;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userrepo;

	@Override
	public UserDTO addUser(User user) throws UserServiceException {
		Optional<User> userTemp = userrepo.findById(user.getUserId());
		if (userTemp.isEmpty())
				{
			user = userrepo.save(user);
			return UserUtil.convertToUserDto(user);

		} else {
			throw new UserServiceException("User already exists ");
		}

	}

	/*
	 * Description : This method gets User by userID
	 * Input Parameter : User Object 
	 * Return Value : UserDTO Object 
	 * Exception : UserServiceException
	 */
	@Override
	public UserDTO getId(long userId) throws UserServiceException {
		Optional<User> getUserTemp = userrepo.findById(userId);
		if (getUserTemp.isEmpty()) {
			throw new UserServiceException("User does not exist");
		} else {
			User getUserId = userrepo.findById(userId).orElse(null);
			return UserUtil.convertToUserDto(getUserId);
		}

	}

	/*
	 * Description : This method Updates User Details
	 * Input Parameter : User Object 
	 * Return Value : UserDTO Object 
	 * Exception : UserServiceException
	 */

	@Override
	public UserDTO updateUser(User user) {
		Optional<User> user1 = userrepo.findById(user.getUserId());
		if (!user1.isEmpty()) {

			User updateUser = userrepo.save(user);
			return UserUtil.convertToUserDto(updateUser);

		} else {
			throw new UserServiceException("User already exists ");
		}

	}
	
	/*
	 * Description : This method deletes User
	 * Input Parameter : User Object 
	 * Return Value : UserDTO Object 
	 * Exception : UserNotFoundException
	 */

	@Override
	public UserDTO deleteUser(long userId) throws UserNotFoundException {
		Optional<User> user1 = userrepo.findById(userId);
		if (user1.isEmpty())
			throw new UserServiceException("No user found");
		else {
			userrepo.deleteById(userId);
			if(user1.isPresent())
			{
				return UserUtil.convertToUserDto(user1.get());
			}
			else
			{
				throw new UserNotFoundException("Payment is not present ");
			}
            

		}
			
		

	}


	
	 

	public boolean checkUser(long userId, String username, String password) throws UserNotFoundException {
		boolean flag = false;
		User user = userrepo.findById(userId).orElse(null);
		if (user == null)
			throw new UserNotFoundException("Invalid User Name");
		else if (user.getPassword().equals(password))
			flag = true;
		else
			throw new UserNotFoundException("Password does not Match");
		return flag;

	}
	
	public static boolean validateUserId(User user) {
		boolean flag = true;
		Long userId = user.getUserId();
		UserServiceImpl service = new UserServiceImpl();
		if (userId == null|| userId < 0 || !service.userrepo.existsById(userId))
			flag = false;
		return flag;
	}
	
	public static void validateUsername(User user) {
		String userName = user.getUsername();
		if (userName == null||user.getUsername().length() < 3 || user.getUsername().length() > 20) 
			throw new UserServiceException("Invalid Username");
	}
	
	public static void validatePassword(User user) {
		String password = user.getPassword();
		if (password == null||user.getPassword().length() < 3 || user.getPassword().length() > 20) 
			throw new UserServiceException("Password not valid");
	}
  
}
