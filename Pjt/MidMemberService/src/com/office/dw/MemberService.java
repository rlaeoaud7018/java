package com.office.dw;

public class MemberService {

	MemberDao memberDao = new MemberDao();
	
	// sign-up
	public void doSignUp(MemberDto memberDto) {
		System.out.println("[MemberService] doSignUp()");
		
//		MemberDao memberDao = new MemberDao();
		memberDao.insertNewMember(memberDto);
		
	}

	// sign-in (인증: Autentication, 인가: Athurization)
	public boolean doSignIn(MemberDto memberDto) {   // id, pw
		System.out.println("[MemberService] doSignIn()");
		
//		MemberDao memberDao = new MemberDao();
		MemberDto selectedMemberDto = memberDao.selectMemberDtoByMemId(memberDto.memId);
		
		if (selectedMemberDto != null) {
			if (selectedMemberDto.memPw.equals(memberDto.memPw)) {
				System.out.println("[MemberService] SIGN-IN SUCCESS!!");
				return true;
				
			} else {
				System.out.println("[MemberService] SIGN-IN FAIL!!");
				return false;
			}
			
		} else {
			System.out.println("[MemberService] SIGN-IN FAIL!!");
			return false;
		}
		
	}

	public void modify(MemberDto memberDto) {
		System.out.println("[MemberService] modify()");
		
		// 인증 ID, PW
		boolean resultForAutentication = verifyByMemIdAndMemPw(memberDto.memId, memberDto.memPw);
		
		// 업데이트 MAIL, PHONE
		if (resultForAutentication) {
			// 업데이트 실시!
			memberDao.updateMemberForMailAndPhone(memberDto);		
			
		}
		
	}

	public void delete(MemberDto memberDto) {
		System.out.println("[MemberService] delete()");
		
		// 인증 ID, PW
		boolean resultForAutentication = verifyByMemIdAndMemPw(memberDto.memId, memberDto.memPw);
		
		if (resultForAutentication) {
			memberDao.deleteMemberByMemId(memberDto.memId);
		}
		
		
	}
	
	public boolean verifyByMemIdAndMemPw(String id, String pw) {
		System.out.println("[MemberService] verifyByMemIdAndMemPw()");
		
		return doSignIn(new MemberDto(id, pw, null, null));
		
	}

}












