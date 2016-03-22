
var message=[];
var messageRead=[];

function run(){
	 var appContainer = document.getElementsByClassName('todos')[0];
	 appContainer.addEventListener('click', delegateEvent);
       messageRead=loadMessages();
	   

  message.push.apply(message,messageRead) ; 
   for(var i=0; i<messageRead.length; i++) {
		 addTodo(messageRead[i].author,messageRead[i].description); 	
}

    
}

function loadMessages() {
	if(typeof(Storage) == "undefined") {
		alert('localStorage is not accessible');
		return;
	}

	var item = localStorage.getItem("TODOs taskList");

	return item && JSON.parse(item);
}


function delegateEvent(evtObj) {	
	if(evtObj.type==='click' && evtObj.target.classList.contains('send')){
		var login=document.getElementsByName ('login')[0];
		var todoText=document.getElementsByClassName ('inputText')[0];
		if(login.value!=''){
		onAddButtonClick(login.value,todoText.value);
		}
	}
	if(evtObj.type==='click' && evtObj.target.classList.contains('login_button')){
	var login=document.getElementsByName ('login')[0];
	if(login.value!=''){
		onAddButtonClick(login.value,'NEW User! ');
	}
	}
}

function onAddButtonClick(login,todoText){
	
	message.push(newMessage(login,todoText));  
	addTodo(login,todoText);
	todoText.value='';
	saveMessages(message);
}

function addTodo(login,value){
	if(!value){
		return;
	}
	var item=createText(login,value);
	var items=document.getElementsByClassName("message-history")[0];
	items.appendChild(item);
}

function createText(login,text){
	
	var divItem=document.createElement('div');
	divItem.classList.add('message');
	divItem.appendChild(document.createTextNode(login+':'+text));
	return divItem;
}

function saveMessages(listToSave) {
	if(typeof(Storage) == "undefined") {
		alert('localStorage is not accessible');
		return;
	}
	localStorage.setItem("TODOs taskList", JSON.stringify(listToSave));
}

function uniqueId() {
	var date = Date.now();
	var random = Math.random() * Math.random();

	return Math.floor(date * random);
}

function newMessage(author,text) {
	return {
		author: author,
		description:text,	
		id: '' + uniqueId()
	};
}


/* запустить cmd из git bash */



//массив сообщение messages push
//1 сохранять в массиве сообщение
//2 попытаться зайти каждую минуту на сайт гугл пока оставить так
//3 написать знак редактир и удаление при кот сообщение удаляется с массива или редактир

