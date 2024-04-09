// Call the dataTables jQuery plugin

$(document).ready(function() {
	loadPyUsers();
	$('#pyusers').DataTable();
});

async function loadPyUsers() {

	const request = await fetch('api/pyusers', {
		method: 'GET',
		headers: getHeaders()
	});
	const pyUsers = await request.json();

	let listHtml = "	";

	for (let pyUser of pyUsers) {

		let buttonDelete = '<a href="#" onclick="deleteUser(' + pyUser.pyId + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

		let num = pyUser.pyNumber == null ? ' - ' : pyUser.pyNumber;

		let pyUserHtml = '<tr><td>' + pyUser.pyId + '</td><td>' + pyUser.pyName + '</td><td>'
			+ pyUser.pyNit + '</td><td>' + num
			+ ' </td><td>' + buttonDelete + '</td><td></td></tr>';
		listHtml += pyUserHtml;
	};

	document.querySelector('#pyusers tbody').innerHTML = listHtml;
};

function getHeaders() {
	return {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
		      'Authorization': localStorage.getItem("token")
	};
}


async function deleteUser(pyId) {
	
	if (!confirm('¿Desea eliminar el Usuario '+ pyId +' ?')){
		return;
	}
	
 const request = await fetch('api/pyusers/' + pyId, {
    method: 'DELETE',
    headers: getHeaders()
  });
  location.reload();	
}

