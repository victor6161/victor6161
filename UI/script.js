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
	
	if(evtObj.type==='click' && evtObj.target.classList.contains('delete')){
		deleteMessage(evtObj.target);
	}
}

function deleteMessage(element){
	
	var index;
    index=message.findIndex(function(item) {
		return item.id == element.id;
	});
    
	message.splice(index, 1);
    element.parentElement.removeChild(element);
    document.getElementsByClassName('message')[index];
	
	/* var textDelete=document.getElementsByClassName('message')[index]; */
	/* textDelete.parentElement.removeChild(textDelete); */
	
    saveMessages(message);
	
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
	var tableItem=document.createElement('table');
	var tr=document.createElement('tr');
	
	var tdMessage=document.createElement('td');
	tdMessage.classList.add('message');
	tdMessage.appendChild(document.createTextNode(login+':'+text));
	
	var tdEdit=document.createElement('td');
	var Edit=document.createElement('div');  
	Edit.classList.add('edit');
	tdEdit.appendChild(Edit);

	var tdDelete=document.createElement('td');
	var Delete=document.createElement('div');  
	Delete.classList.add('delete');
	tdDelete.appendChild(Delete);
	
	tr.appendChild(tdMessage);
	tr.appendChild(tdEdit);
	tr.appendChild(tdDelete);


	tableItem.appendChild(tr);

	
	return tableItem;
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
		id: '' + uniqueId(),
		edit:false,
		del:false
	};
}

/* запустить cmd из git bash */