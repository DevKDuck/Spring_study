package hello.hello_spring;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    void clearStore(){
        memoryMemberRepository.clearStore();
    }



    @Test
    void save(){
        Member member = new Member();
        member.setName("DevkDuck1");
        memoryMemberRepository.save(member);

        Member result = memoryMemberRepository.findById(member.getId()).get();
//        assertThat(member).isEqualTo(null);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member = new Member();
        member.setName("DevkDuck1");
        memoryMemberRepository.save(member);

        Member member2 = new Member();
        member2.setName("DevkDuck2");
        memoryMemberRepository.save(member2);

        Member result = memoryMemberRepository.findByName("DevkDuck1").get();
//        Member result = memoryMemberRepository.findByName("DevkDuck2").get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member = new Member();
        member.setName("DevkDuck1");
        memoryMemberRepository.save(member);

        Member member2 = new Member();
        member2.setName("DevkDuck2");
        memoryMemberRepository.save(member2);

        assertThat(memoryMemberRepository.findAll()).size().isEqualTo(2);
//        assertThat(memoryMemberRepository.findAll()).size().isEqualTo(3);
    }
}
