var message=[];

function run(){
	alert("sfd");
	var appContainer = document.getElementsByClassName('todos')[0];
	appContainer.addEventListener('click', delegateEvent);
}

function delegateEvent(evtObj) {	
	if(evtObj.type==='click' && evtObj.target.classList.contains('send')){
		onAddButtonClick();
	}
}

function onAddButtonClick(){
	
    var todoText=document.getElementsByClassName ('inputText')[0];
	message.push(todoText);
	addTodo(todoText.value);
	todoText.value='';
}

function addTodo(value){
	if(!value){
		return;
	}
	var item=createText(value);
	var items=document.getElementsByClassName("message-history")[0];
	items.appendChild(item);
}

function createText(text){
	
		var divItem=document.createElement('div');
		divItem.classList.add('message');
		divItem.appendChild(document.createTextNode("you:"+text));
	    return divItem;
}


/*function someFunc(){
	var someVar=document.getElementsByClassName("inputText").value;
	
	var div=document.createElement("div");
	div.innerHTML="You:"+someVar;
	document.getElementsByClassName("message").appendChild(div);
	
}*/

/*
function run(){
	var button=document.getElementsByClassName('send')[0];
	button.addEventListener('click',delegateEvent)	
}

function delegateEvent(evtObj){
	if(evtObj.type==='click' && evtObj.target.classList.contains('send'){
		onAddButtonClick(evtObj);
	}
	
}

function onAddButtonClick(){
	var todoText=document.getElementsByClassName('inputText');
	alert(todoText);
}

*/









/*
var messages=[];

(function(){

	var buttons=document.getElementsByClassName('send');
		alert("fdsdfs");
		for(var i=0;len=buttons.length;i<len;i++)
	buttons[i].addEventListener('click',readText,false);
	
	var readText=function(){
		var text=document.getElementsByClassName('inputText');
		alert(text);
		message.push();
		
	}
	
})();
*/


     //var messages=[]
	//var message=document.getElementsByClassName('message')[0]
    //messages.push('message')

//массив сообщение messages push
//1 сохранять в массиве сообщение
//2 попытаться зайти каждую минуту на сайт гугл пока оставить так
//3 написать знак редактир и удаление при кот сообщение удаляется с массива или редактир

