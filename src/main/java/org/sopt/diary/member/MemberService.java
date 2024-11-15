package org.sopt.diary.member;

import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public MemberEntity signupMember(String username, String password, String nickname) {
        if (memberRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 유저입니다.");
        }
        return memberRepository.save(new MemberEntity(username, password, nickname));
    }
    public MemberEntity loginMember(String username, String password) {
        return memberRepository.findByUsername(username)
                .filter(member->member.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 정보를 입력하였습니다."));
    }
}
