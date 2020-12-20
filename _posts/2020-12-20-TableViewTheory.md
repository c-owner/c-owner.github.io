# 테이블 뷰 구현 이론 

Step 1. 테이블 뷰 배치

Step 2. 프로토타입 셀 디자인, 셀 아이덴티파이어 지정

Step 3. 데이터 소스, 델리게이트 연결

Step 4. 데이터 소스 구현

Step 5. 델리게이트 구현



Table View == 🤪

- 테이블 뷰는 객체이기 때문에 데이터 소스에 구현 되어있는 메소드를 호출하는 방식

- return 방식으로 대답

  ``` swift
  override func tableView(_ tableView: UITableView,
                         numberOfRowsInSection section: Int) -> Int{
    return Memo.dummyMemoList.count
  }
  ```

  ```swift
   override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
          let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
  
          // Configure the cell...
          let target = Memo.dummyMemoList[indexPath.row]
          cell.textLabel?.text = target.content
          cell.detailTextLabel?.text = target.insertDate.description
          
          return cell
      }
  ```

  몇번째 셀인지는 어떻게 판단하는가?

  ```swift
  indexPath:
  ```

  indexPath파라미터는 인덱스 목록에서 특정 셀 위치의 목록을 표현할 때 사용한다.

- dequeueReusableCell 메소드로 호출하는데, 우리가 지정한 "cell" 셀 아이덴티파이어이다.

  위 Cell을 cell이라는 상수에 지정해주는 것이다. 

  - indexPath.row 속성에서 접근하면 몇번째 셀인지 확인할 수 있다.
  - 실제 데이터를 셀이 추가되어있는 레이블에 표시되어있다.



만약, 이런 이벤트를 처리하고 싶다면 델리게이트를 연결하고 필요 메소드를 구현해야한다.

데이터 소스는 **필수**지만, 델리게이터는 필수가 아니기 때문에 구현하지 않아도 괜찮다.

