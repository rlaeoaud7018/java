package com.office.dw;

// Ctrl + Shift + O
import java.util.HashMap;

public class MemberDao {

//   ArrayList<MemberDto> memberDB = new ArrayList<MemberDto>();

	HashMap<String, MemberDto> memberDB = new HashMap<String, MemberDto>();

	public void insertNewMember(MemberDto memberDto) {
		System.out.println("[MemberDao] insertNewMember()");

//      memberDB.add(memberDto);
		memberDB.put(memberDto.memId, memberDto);

	}

	public MemberDto selectMemberDtoByMemId(String memId) {
		System.out.println("[MemberDao] selectMemberDtoByMemId()");

//      for (int i =0; i < memberDB.size(); i++) {
//         MemberDto dto = memberDB.get(i);
//         if (dto.memId.equals(memId)) {
//            return dto;
//         }
//      }
//      
//      return null;

		MemberDto memberDto = memberDB.get(memId);
		return memberDto;

	}

	public void updateMemberForMailAndPhone(MemberDto memberDto) {
		System.out.println("[MemberDao] updateMemberForMailAndPhone()");

//      for (int i = 0; i < memberDB.size(); i++) {
//         MemberDto dto = memberDB.get(i);
//         if (dto.memId.equals(memberDto.memId)) {
//            dto.memMail = memberDto.memMail;
//            dto.memPhone = memberDto.memPhone;
//             
//            System.out.println(dto.memMail);
//            System.out.println(dto.memPhone);
//            
//            break;
//            
//         }
//      }

		memberDB.put(memberDto.memId, memberDto);

	}

	public void deleteMemberByMemId(String memId) {
		System.out.println("[MemberDao] deleteMemberByMemId()");

//      for (int i = 0; i < memberDB.size(); i++) {
//         MemberDto dto = memberDB.get(i);
//         if (dto.memId.equals(memId)) {
//            memberDB.remove(i);
//            
//            System.out.println("memberDB.size(): " + memberDB.size());
//         }
//      }

		memberDB.remove(memId);

	}

}
