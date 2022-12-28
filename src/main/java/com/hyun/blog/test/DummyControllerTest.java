package com.hyun.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyun.blog.model.RoleType;
import com.hyun.blog.model.User;
import com.hyun.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
	
	@Autowired	// 의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id ) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {			// Exception 사용 가능 - 모든 exception 처리
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. id : " + id;
	}
	
	// save 함수는 id를 전달하지 않으면 insert, id를 전달했을때 해당 id에 대한 데이터가 있으면 update, 해당 id에 대한 데이터가 없으면 insert 해줌
	// password, email 수정
	@Transactional	// 함수 종료시 자동 commit이 됨    // save 하지 않아도 됨
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requsetUser){	// json 데이터를 요청 => Java Object(MessageConverter의 Jackson 라이브러리가 변환해서 받아줌
		System.out.println("id : " + id);
		System.out.println("password : " + requsetUser.getPassword());
		System.out.println("email : " + requsetUser.getEmail());

		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requsetUser.getPassword());
		user.setEmail(requsetUser.getEmail());

		// userRepository.save(user);
		
		// 더티 체킹
		return user;
	}
	
	
	// http://localhost:8000/blog/dummy/users
	@GetMapping("dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 두건의 데이터 리턴받기
	@GetMapping("dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC)Pageable pageable){
		Page<User> pagingUsers = userRepository.findAll(pageable);
		
		List<User> users = pagingUsers.getContent();		// 아래 출력되는 것 제외 content만 출력
		return users;
	}
	
	
	// {id} 주소로 파라메터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// 현재 id가 3까지 있는데, user/4를 찾으면 DB에서 못찾아서 user가 null이 됨 - return null이 되므로 문제 생김
		// Optional로 User 객체 감싸서 가져오면 null인지 아닌지 판단해서 return
		
//		// 람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저는 없습니다.");
//		});
		
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		return user;
	}

	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청) 
	@PostMapping("/dummy/join")
	public String join(User user) {	// key=value (약속된 규칙)
		System.out.println("id :" + user.getId());
		System.out.println("username :" + user.getUsername());
		System.out.println("password :" + user.getPassword());
		System.out.println("email :" + user.getEmail());
		System.out.println("role :" + user.getRole());
		System.out.println("createDate :" + user.getCreateDate());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
