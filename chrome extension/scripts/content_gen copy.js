const selected = [,];

function selectText() {
  let selectionText = "";
  if (document.getSelection) {
    selectionText = document.getSelection();


  } else if (document.selection) {
    selectionText = document.selection.createRange().text;
  }
  return selectionText;
}

const tooltip = document.createElement("span");
tooltip.id = "tooltip";


//문장을 드래그한 경우
const guide = document.createElement("p");
guide.innerHTML = "<strong> 추천 문장 </strong>";
const dragged = document.createElement("p");
dragged.textContent = "머신에서 값을 받아온다";
const another = document.createElement("button");
another.innerText = "다른 문장  추천 받기";

const apply = document.createElement("button");
apply.innerText = "적용하기"

tooltip.append(guide, dragged, another, apply);
apply.style.alignItems = "center";

tooltip.style.display = 'none'; // 처음에는 보이지 않도록 함
tooltip.style.position = 'absolute';
//tooltip.style.backgroundColor = '#333';
tooltip.style.color = '#fff';
//tooltip.style.padding = '5px';


//툴팁 이벤트
document.addEventListener("mouseup", function () {
  const selection = window.getSelection()
  const first = selection.getRangeAt(0);
  const parentElement = first.commonAncestorContainer.parentElement;
  console.log(parentElement.tagName);
  const selec_text = selection.toString();

  if (selec_text) {
    dragged.innerText = selec_text;
    // 요소에 마우스 진입시 툴팁 보이기
    if(selec_text.trim().length!==0)
    tooltip.style.display = 'block';
    else tooltip.style.display = 'none';
    /*parentElement.addEventListener('mouseenter', () => {
      tooltip.style.display = 'block';
  });*/

    // 드래그 시작 위치로 툴팁 위치 지정
    parentElement.addEventListener('mousedown', (event) => {
      //tooltip.style.left = event.clientX + 'px';
      //tooltip.style.top = event.clientY + 'px';
      tooltip.style.left = event.pageX - 30 + 'px';
      tooltip.style.top = event.pageY - 30 + 'px';
    });

    // 툴팁을 요소에 추가
    parentElement.appendChild(tooltip);
  }else tooltip.style.display = 'none';
/*
  if(!selection) {
    tooltip.style.display = 'none';
  }*/
});
/*
if(tooltip.style.display == 'block') {
  document.addEventListener('click', () => {
    tooltip.style.display = 'none';
  });
}*/
/*
  // 클릭이 일어나면 툴팁 숨기기(나중에는 클릭 이벤트 좀 더 섬세하기 분기하여 작성)
  document.addEventListener('click', () => {
    if(tooltip.style.display === 'block')
      tooltip.style.display = 'none';
    });



//$dragged.style.fontSize = "30px";
//$dragged.style.color = "green";
//let selectionText = "";
//$dragged.id = "#tooltip-element"
/*
if (document.getSelection) {
  selectionText = document.getSelection();
  const parentElement = selectionText.anchorNode.parentElement;
  const parentId = parentElement.id;
  const tooltipElement = document.querySelector(parentId);
  console.log(parentId)

  
}*/
//const drag = selectText();

// 부모 요소의 ID 가져오기



//document.onmouseup = function (e) {
  //추후 구현할 모달( or 논-모달??) 위치를 위해 좌표 가져오기
  //const mouseX = e.clientX;
  //const mouseY = e.clientY; // 나중에 기회 되면 변수로 바꾸기
  /**
  const drag = selectText();
  const parentElement = drag.anchorNode.parentElement;
  // 부모 요소의 ID 가져오기
  const parentId = parentElement.id;
  
  const tooltipElement = document.querySelector(parentId);

  console.log(mouseX, mouseY, selectText().toString())
  
  $dragged.textContent = drag;
  (async () => {
    const response = await chrome.runtime.sendMessage({greeting: "hello", text: drag});
    console.log(response.farewell); */
  /**백그라운드 스크립트로 갔다 오는 동작 확인
   * (백엔드에서 단어, 문장 구분을 하지 않는 경우)
   * future work:
   *  content script에서 판별한 뒤
   *  속성을 달리 해서 백그라운드로 전달해주면 될 듯!
   * 
   
}) ();
open;
const $heading = document.querySelector("h1");
($heading).insertAdjacentElement("afterend", $dragged);*/
//}