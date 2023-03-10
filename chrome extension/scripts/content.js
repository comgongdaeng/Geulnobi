function selectText() {
  let selectionText = "";
  if (document.getSelection) {
    selectionText = document.getSelection();
  } else if (document.selection) {
    selectionText = document.selection.createRange().text;
  }
  return selectionText;
}
//일단 눈에 띄게 초록색 큰 글씨로 div 태그 생성 
const $dragged = document.createElement("div");
$dragged.style.fontSize = "30px";
$dragged.style.color = "green";



document.onmouseup = function (e) {
  //추후 구현할 모달( or 논-모달??) 위치를 위해 좌표 가져오기
  const mouseX = e.clientX;
  const mouseY = e.clientY; // 나중에 기회 되면 변수로 바꾸기
  /*$dragged.style.left = mouseX +'px';
  $dragged.style.right = mouseY +'px';
  
  console.log(mouseX, mouseY, selectText().toString())*/
  const drag = selectText();
  $dragged.innerHTML = drag;
  (async () => {
    const response = await chrome.runtime.sendMessage({greeting: "hello", text: drag});
    console.log(response.farewell);
    /**백그라운드 스크립트로 갔다 오는 동작 확인
     * (백엔드에서 단어, 문장 구분을 하지 않는 경우)
     * future work:
     *  content script에서 판별한 뒤
     *  속성을 달리 해서 백그라운드로 전달해주면 될 듯!
     * 
     */
  }) ();
  const $heading = document.querySelector("h1");
  ($heading).insertAdjacentElement("afterend", $dragged);
}