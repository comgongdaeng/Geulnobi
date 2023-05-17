//const $editableDiv = document.querySelector('[contenteditable="true"]');

document.addEventListener('DOMContentLoaded', function () {
  const $editableDiv = document.getElementsByTagName('textarea');
  console.log('돌아간다');
  $editableDiv.addEventListener('input', function (event) {
    // 사용자가 입력한 텍스트 처리 로직

    // 예시: 오타가 발생한 단어에 대해 밑줄을 그어주는 CSS 스타일 적용
    const errorWord = 'hello'; // 예시로 'hello'라는 단어가 오타가 있다고 가정
    const inputValue = event.target.value;
    if (inputValue.includes(errorWord)) {
      const correctedValue = inputValue.replace(errorWord, `<span class="error">${errorWord}</span>`);
      event.target.innerHTML = correctedValue;
    }
  });
});


