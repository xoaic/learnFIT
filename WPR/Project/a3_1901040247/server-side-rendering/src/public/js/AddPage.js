const formGroup = document.querySelector('.form-adding')
const addAnsBox = formGroup.querySelector('.text-right')
const addAnsBtn = addAnsBox.querySelector('.text-right button')
addAnsBtn.addEventListener('click', addNewAnswer)
const saveQuesBtn = document.querySelector('.save-new-ques')
saveQuesBtn.addEventListener('click', saveNewQuestion)

let count = 2
function addNewAnswer() {
    addAnswer(count)
    count++
    const ansNum = formGroup.querySelectorAll('.answer')
    ansNum.forEach(ans => {
        const removeAnswerBtn = ans.querySelector('button')
        removeAnswerBtn.addEventListener('click', () => {ans.remove()})
    })
}

function addAnswer(index) {
    const ansBox = document.createElement('div')
    const inpText = document.createElement('input')
    const correctAnsBox = document.createElement('div')
    const correctAnsInp = document.createElement('input')
    const correctAnsLab = document.createElement('label')
    correctAnsLab.textContent = `correct`
    const btn = document.createElement('button')
    btn.innerHTML = `<i class="fas fa-times"></i> Remove`
    
    setAttributes(ansBox, {'class': 'answer'})
    setAttributes(inpText, {'type': 'text', 'name': 'answers', 'value': ''})
    setAttributes(correctAnsInp, {'type': 'radio', 'name': 'correctAnswer', 'value': index, 'id': `answer${index}`})
    setAttributes(correctAnsLab, {'for': `answer${index}`})
    setAttributes(btn, {'class': 'btn btn-orange', 'type': 'button'})
    
    ansBox.appendChild(inpText)
    ansBox.appendChild(correctAnsBox)
    ansBox.appendChild(btn)
    correctAnsBox.appendChild(correctAnsInp)
    correctAnsBox.appendChild(correctAnsLab)
    formGroup.insertBefore(ansBox, addAnsBox)
}

function saveNewQuestion() {
    const formUpdate = document.querySelector('#form-update')
    formUpdate.submit()
}

function setAttributes(ele, atts) {
    for (var key in atts) {
        ele.setAttribute(key, atts[key])
    }
}