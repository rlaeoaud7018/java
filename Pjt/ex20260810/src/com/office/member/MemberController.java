package com.office.member;

import java.util.Scanner;

public class MemberController {

   public void execute() {
      System.out.println("[MemberController] execute()");
      
      boolean systemFlag = true;
      
      String memId;
      String memPw;
      String memMail;
      
      MemberDto memberDto = null;
      MemberService memberService = null;
      Scanner scanner = new Scanner(System.in);
      
      while (systemFlag) {
         System.out.println("1.SIGN-UP    2.SIGN-IN    3.MODIFY    4.DELETE    99.SYSTEM-OUT");
         System.out.println("Please MENU NUMBER!!");
         
         int selectedMenuNumber = scanner.nextInt();
         scanner.nextLine();
         
         switch (selectedMenuNumber) {
         case 1:            // sign-up
            System.out.println("Please new member ID!!");
            memId = scanner.nextLine();
            
            System.out.println("Please new member PW!!");
            memPw = scanner.nextLine();
            
            System.out.println("Please new member MAIL!!");
            memMail = scanner.nextLine();
            
            memberDto = new MemberDto(memId, memPw, memMail);
            
            memberService = new MemberService();
            memberService.doSignUp(memberDto);
            
            break;

         case 2:            // sign-in
            System.out.println("Please member ID!!");
            memId = scanner.nextLine();
            
            System.out.println("Please member PW!!");
            memPw = scanner.nextLine();
            
            memberDto = new MemberDto(memId, memPw, null);
            
            memberService = new MemberService();
            memberService.doSignIn(memberDto);
            
            break;
            
         case 3:            // modify
      
            break;
      
         case 4:            // delete
      
            break;
      
         case 99:         // system-out
            systemFlag = false;
            System.out.println("Good bye~");
            break;
         }
         
      }
      
      scanner.close();
      
   }
   
}
