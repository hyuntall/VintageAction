package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}


	//테트스 데이터 삽입
	@Bean
	public CommandLineRunner runner(MemberRepository memberRepository) throws Exception {
		return (args) -> {
			IntStream.rangeClosed(1, 10).forEach(index -> memberRepository.save(Member.builder()
					.memberId("admin"+index)
					.memberName("관리자"+index)
					.memberPassword("admin123"+index)
					.build())
			);
		};
	}

}
