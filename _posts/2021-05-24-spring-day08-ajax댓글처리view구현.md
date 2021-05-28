---

layout: post
title:  "스프링 - Ajax 댓글처리 View 구현"
date: 2021-05-24 09:00:21 +0900
categories: JAVA Spring
---



# 스프링(Spring) - Ajax 댓글 처리  모듈화



#### reply.js 



replyService 

```javascript
/**
	Javascript reply ajax Module
 */
// replyService 는 JSON 타입이다. 
var replyService = (function(){

	// 댓글 추가
	function add(reply, callback, error){
		console.log("add reply........."); 
		
		// 자바에서의 메소드는 저장공간이라는 것
		$.ajax({
			type: "post",
			url: "/replies/new",
			data: JSON.stringify(reply), // data는 내가 전달할 데이터이다.
			// 내가 전달할 데이터의 타입이 무엇인가. 안에 내장된 내용의 타입을 보내는 것. 
			contentType: "application/json; charset=utf-8",
			success: function(result){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	
	
	return {add: add};
})();
```

a라는 결과로 어떻게 할 지 정하지 않았기 때문에, 이 함수를 사용한 쪽에서 활용하는 것으로 목적을 둔다.

그리하여 callback 함수를 넣고 리턴한다. 오류가 날 수 있으므로 error를 전달한다. 

필요에 따라 사용하되, 순서를 지켜야 한다.





댓글 목록

```javascript
	// 댓글 목록
	// 받을 때는 json으로 받는다.
	function getList(param, callback, error){
		console.log("get List........");
		
		var bno = param.bno;
		var page = param.page || 1;
		
//		$.getJSON("", function(){}.fail(function(){}) // 구조
		
		// xml이므로  .json 확장자로 바꾼다.
		$.getJSON("/replies/pages/"+ bno + "/"+ page +".json",
			function(data){
				if(callback){callback(data);}
			})
			.fail(function(xhr, status, err){
				if(error){
					error(err);
				}	
			})
		}

	return {add: add, getList: getList};
})();
	
```



댓글 삭제 

```javascript
	// 댓글 삭제
	function remove(rno, callback, error){
		console.log("remove...........");
		$.ajax({
			type: "delete",
			url: "/replies/" + rno,
			success: function(result){
				if(callback){callback(result);}
			},
			error: function(xhr, status, err){
				if(error){error(err);}
			}
		});
	}

return {add: add, getList: getList, remove: remove};
})();
```





댓글 수정

```javascript
	// 댓글 수정
	function modify(reply, callback, error){
		console.log("modify : "+reply.rno);
		
		$.ajax({
			type: "PUT",
			url: "/replies/" + reply.rno,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success: function(result, status, xhr){
				if(callback){callback(result);}
			},
			error: function(xhr, status, err){
				if(error){error(err);}
			} 
		});
	}
	
		
	return {add: add, getList: getList, remove: remove, modify: modify};
})();
```



댓글 조회

```javascript
	// 댓글 조회
	function get(rno, callback, error){
		$.get("/replies/"+ rno + ".json", function(result){if(callback){callback(result);}})
		.fail(function(xhr, status, err){if(error){error(err);}
		})
	}
	
		
	return {add: add, getList: getList, remove: remove, modify: modify, get: get};
})();
```









view 페이지에서 댓글을 뿌려줄 뷰 부분을 수정한다.



get.jsp 수정

```jsp
<ul class="icons">
  <li>
    <span class="icon solid fa-envelope"></span>
    <strong>댓글</strong>
  </li>
</ul>
<a style="width:100%" href="javascript:void(0)" class="button primary small register">댓글 등록</a>
<div class="fields register-form" style="display:none">
  <div class="field">
    <h4>작성자</h4>
    <input type="text" name="replyer" placeholder="Replyer">
  </div>
  <div class="field">
    <h4>댓글</h4>
    <textarea rows="6" name="reply" placeholder="Reply" style="resize:none;"></textarea>
  </div>
  <div class="field regBtn">
    <a href="javascript:void(0)" class="button primary small finish">등록</a>
    <a href="javascript:void(0)" class="button primary small cancel">취소</a>
  </div>
</div>
<ul class="replies">
</ul>
<div class="paging" style="text-align:center;">
</div>
```

![image-20210524101927769](/Users/corner-macmini/Library/Application Support/typora-user-images/image-20210524101927769.png)





get.jsp에 script 부분

등록과 취소 이벤트 추가

```javascript
<script>
     $(document).ready(function(){

          $(".register").on("click", function(e){
            e.preventDefault();
            $(".register-form").show();
            $(this).hide();
        });


      $(".cancel").on("click", function(e){
            e.preventDefault();
            $(".register-form").hide();
            $(".register").show();
        });

     });
</script>
```



![img](https://blog.kakaocdn.net/dn/5UEpg/btq5vFxlceW/yEAFgzOcyj9mA0hpMyqFdK/img.png)





 script에 댓글 등록 추가 

```javascript
var bno = "${board.bno}";

    	// 등록버튼을 눌렀을 때
    	$(".finish").on("click", function(e){
    		e.preventDefault();
    		// 필요한 데이터: 댓글 작성자, 댓글
    		var replyer = $("input[name='replyer']").val();
    		var reply = $("textarea[name='reply']").val();
    		
    		if(replyer == "" || reply == "" ) {return;}
    		
    		replyService.add({bno: bno, reply:reply, replyer:replyer}, function(result){
    			alert(result);
    			$("input[name='replyer']").val("Replyer");
    			$("textarea[name='reply']").val("Reply");
        		$(".register-form").hide();
        		$(".register").show();
    			pageNum = 1;
    			showList(pageNum);
   			});
    	});

```

추가 확인

![img](https://blog.kakaocdn.net/dn/x7u1q/btq5Da39h4X/ZhRHpQXnR16rrcxfDELwLk/img.png)

![img](https://blog.kakaocdn.net/dn/0Kx13/btq5vOvfcgH/UdSVur3B8JBuIu2LGY7n80/img.png)







showList() 와 showReplyPage() 설계 

```javascript
    	function showReplyPage(){
    		var startNum
    		var endNum
    		var realEnd
    		
    		
    	}
    	
    	// 현재 페이지가 무엇인지 알아야하므로 페이지 번호를 받아와야 한다.
    	function showList(){
    		replyService.getList();
    	}
```



---



showList와 showReplyPage 

```javascript
	   	 var bno = "${board.bno}";
			 var replyUL = $(".replies");
	   	 var pageNum = 1;
	   	 
	   	 showList(1);
    	function showReplyPage(replyCnt){
    		
    		var str = "";
    		var endNum = Math.ceil(pageNum / 10.0 ) * 10;
    		var startNum = endNum - 9;
    		var realEnd = Math.ceil(replyCnt / 10.0);
    		
    		if(endNum > realEnd ){
    			endNum = realEnd;
    		}
    		
    		var prev = startNum != 1;
    		var next = endNum * 10 < replyCnt;
    		
    		if(matchMedia("screen and (max-width:918px)").matches){
    			if(pageNum != 1 ) {
    				str += "<a class='changePage' href='"+ (pageNum - 1) +"'><code>&lt;</code></a>"
    			}
    				str += "<code>"+ pageNum + "</code>";	
    			if(pageNum != realEnd) {
    				str += "<a class='changePage' href='"+ (pageNum + 1) +"'><code>&gt;</code></a>"
    			}
    		} else {
    			if(prev){
    				str += "<a class='changePage' href='"+ (startNum - 1) +"'><code>&lt;</code>"
    			}
    			for(let i = startNum; i <= endNum; i++ ){
	   				if(pageNum == i){
	    				str += "<code>" + i +"</code>";
	    				continue;
	    			}
	   				str += "<a class='changePage' href='"+ i +"'><code>" + i + "</code></a>"
	   			}
    			if(next){
    				str += "<a class='changePage' href='"+ (endNum + 1) +"'><code>&gt;</code></a>"
    			}
    		}
    		replyPaging.html(str); // DOM
    	}
    	
    	// 위임 
		$(".paging").on("click", "a.changePage", function(e){
			e.preventDefault();
			pageNum = parseInt($(this).attr("href"));
			console.log(pageNum)
			showList(pageNum);
		});  	
    	
    	// 현재 페이지가 무엇인지 알아야하므로 페이지 번호를 받아와야 한다.
    	function showList(page){
    		replyService.getList({bno:bno, page: page||1}, function(replyCnt, list){
    			console.log("replyCnt : " + replyCnt);
    			console.log("list : " + list);
				     			
    			var str = "";
    			if(list == null || list.length == 0 ){
    				if(pageNum > 1 ) { // 2페이지에서 하나 남은 댓글을 삭제하면 1페이지로 가야되는데 2페이지로 유지되면서 등록된 댓글이 없다고 나온다.
    					pageNum -= 1; // 내 페이지를 1개 감소 시키고 
    					showList(pageNum); // 다시 그리기.
    				}
    				replyUL.html("등록된 댓글이 없습니다.");
    				return;
    			}
    			
    			for(let i = 0, len = list.length || 0; i < len; i++){
    				str += "<li data-rno='"+ list[i].rno +"'>";
    				// data-rno 라는 옵션을 통해서 파일첨부 때 이용할 수 있다.
    				str += "<strong>" + list[i].replyer + "</strong>";
    				str += "<p class='reply"+ list[i].rno +"'>" + list[i].reply + "</p>";
    				// 모든 p태그의 reply라는 클래스 반복문을 돌리기 번거로우니, RNO를 붙여 연결을 시킨다.
    				str += "<div style='text-align:right;'>";
    				str += "<a class='modify' href='"+ list[i].rno +"'>수정</a>";
    				str += "<a class='finish' href='"+ list[i].rno +"' style='display:none;'>수정완료</a>";
    				str += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    				str += "<a class='remove' href='"+ list[i].rno +"'>삭제</a>";
    				str += "</div><div class='line'></div></li>";
    			}
    			replyUL.html(str);
    			showReplyPage(replyCnt);
    			
    		});
    	}
```

- matchMedia는 반응형 함수이다.



내가 데이터베이스에 추가해야할 데이터를 모듈로 보낸다.

모듈로 가서 해당 함수로 실행될 때 전달을 하고 컨트롤러에서 db조회를 하고, 결과가 

모듈 ajax success로 콜백하게 된다.

이것을 가지고 get.jsp에서 콜백 함수를 매개변수로 받는 것이다.





ReplyMapper.xml

```xml
	<select id="getTotal" resultType="_int">
		SELECT COUNT(RNO) FROM TBL_REPLY WHERE BNO = #{bno}
	</select>
```



ReplyMapper.java

```java
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
	public int getTotal(Long bno);
```



ReplyServiceImple.java

```java
	@Override
	public ReplyPageDTO getListWithPaging(Criteria cri, Long bno) {
		log.info("get Reply List of a Board" + bno);
		//댓글을 구현하기 위해 필요한 두 개의 요소를 ReplyPageDTO에 전달한다.
		return new ReplyPageDTO(mapper.getTotal(bno), mapper.getListWithPaging(cri, bno));
	}
```



ReplyPageDTO.java 추가

```java
package com.koreait.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/*
 * 댓글 목록을 구현할 때 페이징처리도 해야 한다.
 * 페이징 처리를 하기 위해서는 해당 게시글의 전체 개수가 필요하므로
 * ReplyPageDTO객체를 선언하여 두 가지 데이터를 필드로 구현해 놓는다.
 * */
public class ReplyPageDTO {
	private int replyCnt;//해당 게시글의 총 댓글 수
	private List<ReplyVO> list;//해당 게시글에서 페이지에 맞는 댓글 목록(amount는 10이다)
}
```



ReplyService.Interface 수정 

```java
public ReplyPageDTO getListWithPaging(Criteria cri, Long bno);
```



ReplyController 수정

```java
	//게시글 댓글 전체 조회
	@GetMapping(value="/pages/{bno}/{page}", produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	//서비스에 있는 getListWithPaging은 댓글 목록과 댓글 전체 개수 두 개를 갖고 있는 ReplyPageDTO 타입이다.  
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("bno") Long bno, @PathVariable("page") int page){
		log.info("getList..........");
		Criteria cri = new Criteria(page, 10);
		log.info(cri);
		
		return new ResponseEntity<ReplyPageDTO>(service.getListWithPaging(cri, bno), HttpStatus.OK);
	}
```





reply.js 수정 

```javascript
function getList(param, callback, error){
		console.log("get List........");
		
		var bno = param.bno;
		var page = param.page || 1;
		
//		$.getJSON("", function(){}.fail(function(){}) // 구조
		
		// xml이므로  .json 확장자로 바꾼다.
		$.getJSON("/replies/pages/"+ bno + "/"+ page +".json",
			function(data){
				if(callback){callback(data.replyCnt, data.list);}
			})
			.fail(function(xhr, status, err){
				if(error){
					error(err);
				}	
			})
		}
```

 

**테스트**

![img](https://blog.kakaocdn.net/dn/b1n8TQ/btq5APlGZP5/zlBPYVsM7fkrfLAOqHl0i0/img.png)

![img](https://blog.kakaocdn.net/dn/cf0GJo/btq5zG32cGi/pkFzrQLxkTUaiqouCMQtdk/img.png)

잘 나오는것을 확인할 수 있다.



---



## 댓글 수정과 삭제 



javascript

```javascript
    	// 댓글 삭제 
    	// 삭제 버튼 클릭 시 해당 댓글 번호를 가져와 삭제하기
		  $(".replies").on("click", "a.remove", function(e){ // 이벤트 위임
			 e.preventDefault();
			 var rnoValue = $(this).attr("href");
			 
			 replyService.remove(rnoValue, function(result){
				 alert(result);
				 showList(pageNum); // 삭제를 했으면 다시 보여주도록 하기.(새로고침 없이) 
			 });
		  });
    	
    	var check = false; // 다른 댓글 수정 못하게 (동시에) 플래그를 만들어야 한다.
    	
    	// 댓글 수정
    	$(".replies").on("click", "a.modify", function(e){ // 이벤트 위임
    		// 1. 수정완료 버튼
    		// 2. p태그를 textarea로 변경 (기존 p태그의 내용을 textarea로 옮겨야 한다.)
    		e.preventDefault();
    	
    		if(check){alert("수정중인 댓글이 있습니다."); return;}
    		
    		var rnoValue = $(this).attr("href");
    		var reply = $(".reply" + rnoValue); // p태그
    		
    		reply.html("<textarea class='"+ rnoValue +"'>"+ reply.text() + "</textarea>"); // textarea로 변경
    		$(this).hide(); // 수정 버튼 숨김
    		
    		var finishs = $(".finish");
    		for(let i = 0; i < finishs.length; i++) {
    			if($(finishs[i]).attr("href") == rnoValue ){
    				$(finishs[i]).show(); // 수정완료 버튼 
    				check = true;
    				break;
    			}
    		}
    	});
    	// 수정완료
    	$(".replies").on("click", "a.finish", function(e){
    		e.preventDefault();
    		
    		var rnoValue = $(this).attr("href");
    		var newReply = $("." + rnoValue).val(); //사용자가 랜더링(브라우저 해석) 다 되고나서 가져와야하기 때문이다.
    		
    		if(newReply == "" ) {return;}
    		
    		replyService.modify({rno:rnoValue, reply:newReply}, function(result){
    			alert(result);
    			check = false;
    			showList(pageNum);
    		});
    	});
```



#### 테스트



![img](https://blog.kakaocdn.net/dn/uZP5n/btq5Fdl2UCx/cLpdsS3cxX2IMg2HSKfO6k/img.png)

![img](https://blog.kakaocdn.net/dn/R6Po4/btq5uIVEdAT/KRPvXbsCO12WfvKnORxbk1/img.png)

![img](https://blog.kakaocdn.net/dn/bnK8eM/btq5vOaXDEM/BzWNfZr8DytgrGUhomJGz0/img.png)

