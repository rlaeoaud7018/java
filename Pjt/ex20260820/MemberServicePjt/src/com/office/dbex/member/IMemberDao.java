package com.office.dbex.member;

public interface IMemberDao {

	final String URL = "jdbc:mysql://localhost:3306/db_member";
	final String USER = "root";
	final String PASSWORD = "1234";
	
	public int insertNewMember(MemberDto memberDto);

	public MemberDto selectMemberByMemId(String id); 

	public int updateMember(MemberDto memberDto);
	
	public int deleteMemberByMemId(String memId); 
	
}
