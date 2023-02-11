|html|역할|
|--|--|
|1. index.html | 메인화면(로그인)|
|2. signUp.html | 회원가입 화면|
||index.html에서 '회원가입하기' 클릭 시 이동.|
|3. google.html | gmail 입력 화면||
||index.html에서 '구글 아이콘' 클릭 시 이동.||
|4. gSignIn.html | gmail로 로그인을 위한 비밀번호 입력|||
||(3. google.html에서 입력한 gmail로 가입 이력이 있을 때 이동)||||
|5. gYesOrNo.html | gmail을 통한 가입 의사를 묻는 화면|
||(3. google.html에서 입력한 gmail로 가입 이력이 없을 때 이동)|
|6. gSignUp.html | gmail로 회원가입을 위한 비밀번호 입력|
||(3. gYesOrNo.html에서 yes 선택 시 이동)|


gYesOrNo.html을 제외한 모든 파일에 있는 fetch('URL', { ... } ) 함수에서 'URL' 부분에 백엔드 API의 URL을 넣는다고
생각하고 작성한 코드입니다.